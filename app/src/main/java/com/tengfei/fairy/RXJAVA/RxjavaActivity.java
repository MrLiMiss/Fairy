package com.tengfei.fairy.RXJAVA;

import android.util.Log;
import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.Logs;


import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ Description :Rxjava demo
 *
 *      RxJava 是一个 基于事件流、实现异步操作的库
 *
 * @ Author 李腾飞
 * @ Time 2022/4/15   4:07 PM
 * @ Version :
 */
public class RxjavaActivity extends BaseActivity {
    public String TAG = RxjavaActivity.class.getSimpleName();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rxjava;
    }

    @OnClick({R.id.btn_divide, R.id.btn_rxjava_link,R.id.btn_operate_thread})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_divide://分别创建调用
                rxJavaDivide();
                break;
            case R.id.btn_rxjava_link://链式调用
                rxJavaLinked();
                break;
            case R.id.btn_operate_thread://线程调度
                operateThread();
            default:
                break;
        }
    }

    /**
     * 线程调度测试
     */
    private void operateThread() {
        // 步骤1：创建被观察者 Observable & 发送事件
        // 在主线程创建被观察者 Observable 对象
        // 所以生产事件的线程是：主线程

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, " 被观察者 Observable的工作线程是: " + Thread.currentThread().getName());
                // 打印验证
                emitter.onNext(1);
                emitter.onComplete();
            }
        });

        // 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 在主线程创建观察者 Observer 对象
        // 所以接收 & 响应事件的线程是：主线程
        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
                Log.d(TAG, " 观察者 Observer的工作线程是: " + Thread.currentThread().getName());
                // 打印验证

            }
            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
            }
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        // 步骤3：通过订阅（subscribe）连接观察者和被观察者（默认都在主线程执行）
//        observable.subscribe(observer);


        // <-- 使用说明 -->
        // Observable.subscribeOn（Schedulers.Thread）：指定被观察者 发送事件的线程（传入RxJava内置的线程类型）
        // Observable.observeOn（Schedulers.Thread）：指定观察者 接收 & 响应事件的线程（传入RxJava内置的线程类型）

        //  <-- 实例使用 -->
        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribeOn(Schedulers.newThread()) // 1. 指定被观察者 生产事件的线程
                  .subscribeOn(AndroidSchedulers.mainThread()) // 第二次指定被观察者线程 = 主线程
                .observeOn(AndroidSchedulers.mainThread())  // 2. 指定观察者 接收 & 响应事件的线程
                .doOnNext(new Consumer<Integer>() { // 生产事件
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "第1次观察者Observer的工作线程是： " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread()) // 第二次指定观察者线程 = 新的工作线程
                .doOnNext(new Consumer<Integer>() { // 生产事件
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "第2次观察者Observer的工作线程是： " + Thread.currentThread().getName());
                    }
                })
                .subscribe(observer); // 3. 最后再通过订阅（subscribe）连接观察者和被观察者


        //注意：
        //1、若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效，
        //2、若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换


    }

    /**
     * rxjava 链式调用
     */
    private void rxJavaLinked() {
        // 1. 创建被观察者 & 生产事件
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logs.d(TAG, "链式=开始采用subscribe连接");
            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.d(TAG, "对Next事件" + o.toString() + "作出响应");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logs.d(TAG, "链式-对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Logs.d(TAG, "链式-对Complete事件作出响应");
            }
        });

    }

    /**
     * rxjava 分别创建 被观察者 观察者 订阅事件
     */
    private void rxJavaDivide() {
        //步骤1：创建被观察者 Observable & 生产事件
        //  1. 创建被观察者 Observable 对象
        // 即 顾客入饭店 - 坐下餐桌 - 点菜
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        // 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 即 开厨房 - 确定对应菜式
        Observer observer = new Observer() {

            // 1. 定义Disposable类变量
            private Disposable mDisposable;

            // 通过复写对应方法来 响应 被观察者

            // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logs.d(TAG, "开始采用subscribe连接");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Object o) {
                if ((Integer)o==2) {
                    mDisposable.dispose();
                    // 设置在接收到第二个事件后切断观察者和被观察者的连接
                    Log.d(TAG, "已经切断了连接：" + mDisposable.isDisposed());
                }
                Logs.d(TAG, "对Next事件" + o.toString() + "作出响应111");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logs.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        // 即 顾客找到服务员 - 点菜 - 服务员下单到厨房 - 厨房烹调
        observable.subscribe(observer);

    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
