package com.tengfei.fairy.RXJAVA;

import android.util.Log;
import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
    private Integer i;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rxjava;
    }

    @OnClick({R.id.btn_divide, R.id.btn_rxjava_link,R.id.btn_function_thread,R.id.btn_operate_just,R.id.btn_operate_fromArray,R.id.btn_operate_fromIterable,R.id.btn_operate_defer,R.id.btn_operate_timer,R.id.btn_operate_interval,R.id.btn_operate_intervalRange
    ,R.id.btn_function_delay,R.id.btn_function_do,R.id.btn_function_onErrorReturn,R.id.btn_function_onErrorResumeNext,R.id.btn_function_retry,R.id.btn_function_retryutils,R.id.btn_function_retrywhen,R.id.btn_function_repeat,R.id.btn_function_repeatwhen
    ,R.id.tv_thread,R.id.tv_repeat,R.id.tv_net_error})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_divide://分别创建调用
                rxJavaDivide();
                break;
            case R.id.btn_rxjava_link://链式调用
                rxJavaLinked();
                break;
            case R.id.btn_function_thread://线程调度
                operateThread();
                break;
            case R.id.btn_function_delay:
                functionDelay();
                break;
            case R.id.btn_function_do://生命周期中调用
                functionDo();
                break;
            case R.id.btn_function_onErrorReturn://遇到错误时，发送1个特殊事件 & 正常终止
                functionOnErrorReturn();
                break;
            case R.id.btn_function_onErrorResumeNext://遇到错误时，发送1个新的Observable
                functionOnErrorResumeNet();
                break;
            case R.id.btn_function_retry://重试，即当出现错误时，让被观察者（Observable）重新发射数据
                functionRetry();
                break;
            case R.id.btn_function_retryutils://出现错误后，判断是否需要重新发送数据
                functionRetryUtils();
                break;
            case R.id.btn_function_retrywhen:
                functionReterWhen();
                break;
            case R.id.btn_function_repeat://无条件地、重复发送 被观察者事件
                functionRepeat();
                break;
            case R.id.btn_function_repeatwhen://有条件地、重复发送 被观察者事件
                functionRepeatWhen();
                break;
            case R.id.btn_operate_just:
                operateJust();//创建操作符 快速创建just（）
                break;
            case R.id.btn_operate_fromArray://创建操作符fromArray（）数组
                createFromArray();
                break;
            case R.id.btn_operate_fromIterable://创建操作符fromIterable（）链表
                createFromIterable();
                break;
            case R.id.btn_operate_defer://延迟操作： 直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
                createDefer();
                break;
            case R.id.btn_operate_timer:// 延迟指定时间操作
                createTimer();
                break;
            case R.id.btn_operate_interval://间隔指定时间操作
                createInterval();
                break;
            case R.id.btn_operate_intervalRange://每隔指定时间 就发送 事件
                createIntervalRange();
                break;
            case R.id.tv_thread://线程调度中使用
                operateThread();
                Logs.d(TAG,"线程切换");
                break;
            case R.id.tv_repeat://轮询
                reallRepet();
                break;
            case R.id.tv_net_error://网络错误重试
                netErrorRepet();
                break;

            default:
                break;
        }
    }

    /**
     * 网络错误 重试
     */
    private void netErrorRepet() {
    }

    /**
     *     项目应用轮询：https://www.jianshu.com/p/dbeaaa4afad5
     *   （有条件）网络请求轮询
     */
    private void reallRepet() {
        IntentUtils.toNetRepeat(activity);//网络轮询
    }

    /**
     *      有条件地、重复发送 被观察者事件
     *      将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable），以此决定是否重新订阅 & 发送原来的 Observable
     *
     *     1、 若新被观察者（Observable）返回1个Complete / Error事件，则不重新订阅 & 发送原来的 Observable
     *     2、 若新被观察者（Observable）返回其余事件时，则重新订阅 & 发送原来的 Observable
     *
     */
    private void functionRepeatWhen() {
        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        // return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        // return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应：" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    /**
     * 无条件地、重复发送 被观察者事件
     * 具备重载方法，可设置重复创建次数
     */
    private void functionRepeat() {
    }

    /**
     * 遇到错误时，将发生的错误传递给一个新的被观察者（Observable），
     * 并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
     */
    private void functionReterWhen() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 遇到error事件才会回调
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                        // 返回Observable<?> = 新的被观察者 Observable（任意类型）
                        // 此处有两种情况：
                        // 1. 若 新的被观察者 Observable发送的事件 = Error事件，那么 原始Observable则不重新发送事件：
                        // 2. 若 新的被观察者 Observable发送的事件 = Next事件 ，那么原始的Observable则重新发送事件：
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {

                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                                // 该异常错误信息可在观察者中的onError（）中获得
                                return Observable.error(new Throwable("retryWhen终止啦"));

                                // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                                // return Observable.just(1);
                            }
                        });

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应" + e.toString());
                        // 获取异常错误信息
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 出现错误后，判断是否需要重新发送数据
     * 若需要重新发送 & 持续遇到错误，则持续重试
     * 作用类似于retry（Predicate predicate）
     * 具体使用类似于retry（Predicate predicate），唯一区别：返回 true 则不重新发送数据事件。此处不作过多描述
     */
    private void functionRetryUtils() {
        Logs.d(TAG," RetryUtils() 作用类似于retry（Predicate predicate）");
    }

    /**
     *   重试，即当出现错误时，让被观察者（Observable）重新发射数据
     *   接收到 onError（）时，重新订阅 & 发送事件
     *   Throwable 和 Exception都可拦截
     *   <-- 1. retry（） -->
     *   // 作用：出现错误时，让被观察者重新发送数据
     *  // 注：若一直错误，则一直重新发送
     *
     *   <-- 2. retry（long time） -->
     *  // 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
     *  // 参数 = 重试次数
     *
     *  <-- 3. retry（Predicate predicate） -->
     *   // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
     *  // 参数 = 判断逻辑
     *
     *  <--  4. retry（new BiPredicate<Integer, Throwable>） -->
     *   // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
     *  // 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
     *
     *  <-- 5. retry（long time,Predicate predicate） -->
     *  // 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
     *   // 参数 = 设置重试次数 & 判断逻辑
     */
    private void functionRetry() {
//        <-- 1. retry（） -->
// 作用：出现错误时，让被观察者重新发送数据
// 注：若一直错误，则一直重新发送

                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new Exception("发生错误了"));
                        e.onNext(3);
                    }
                })
                        .retry() // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "对Complete事件作出响应");
                            }
                        });


//<-- 2. retry（long time） -->
// 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
// 参数 = 重试次数
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new Exception("发生错误了"));
                        e.onNext(3);
                    }
                })
                        .retry(3) // 设置重试次数 = 3次
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "对Complete事件作出响应");
                            }
                        });

//<-- 3. retry（Predicate predicate） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
// 参数 = 判断逻辑
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new Exception("发生错误了"));
                        e.onNext(3);
                    }
                })
                        // 拦截错误后，判断是否需要重新发送请求
                        .retry(new Predicate<Throwable>() {
                            @Override
                            public boolean test(@NonNull Throwable throwable) throws Exception {
                                // 捕获异常
                                Log.e(TAG, "retry错误: "+throwable.toString());

                                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                                return true;
                            }
                        })
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "对Complete事件作出响应");
                            }
                        });

//<--  4. retry（new BiPredicate<Integer, Throwable>） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
// 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new Exception("发生错误了"));
                        e.onNext(3);
                    }
                })

                        // 拦截错误后，判断是否需要重新发送请求
                        .retry(new BiPredicate<Integer, Throwable>() {
                            @Override
                            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                                // 捕获异常
                                Log.e(TAG, "异常错误 =  "+throwable.toString());

                                // 获取当前重试次数
                                Log.e(TAG, "当前重试次数 =  "+integer);

                                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                                return true;
                            }
                        })
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "对Complete事件作出响应");
                            }
                        });


//<-- 5. retry（long time,Predicate predicate） -->
// 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
// 参数 = 设置重试次数 & 判断逻辑
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onError(new Exception("发生错误了"));
                        e.onNext(3);
                    }
                })
                        // 拦截错误后，判断是否需要重新发送请求
                        .retry(3, new Predicate<Throwable>() {
                            @Override
                            public boolean test(@NonNull Throwable throwable) throws Exception {
                                // 捕获异常
                                Log.e(TAG, "retry错误: "+throwable.toString());

                                //返回false = 不重新重新发送数据 & 调用观察者的onError（）结束
                                //返回true = 重新发送请求（最多重新发送3次）
                                return true;
                            }
                        })
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer value) {
                                Log.d(TAG, "接收到了事件"+ value  );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "对Error事件作出响应");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "对Complete事件作出响应");
                            }
                        });
    }



    /**
     *  遇到错误时，发送1个新的Observable
     *  onErrorResumeNext（）拦截的错误 = Throwable；若需拦截Exception请用onExceptionResumeNext（）
     *  若onErrorResumeNext（）拦截的错误 = Exception，则会将错误传递给观察者的onError方法
     *
     */
    private void functionOnErrorResumeNet() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Throwable throwable) throws Exception {

                        // 1. 捕捉错误异常
                        Log.e(TAG, "在onErrorReturn处理了错误: "+throwable.toString() );

                        // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
                        return Observable.just(11,22);

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 遇到错误时，发送1个特殊事件 & 正常终止
     */
    private void functionOnErrorReturn() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable) throws Exception {
                        // 捕捉错误异常
                        Logs.e(TAG, "在onErrorReturn处理了错误: "+throwable.toString() );

                        return 666;
                        // 发生错误事件后，发送一个"666"事件，最终正常结束
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 延迟操作
     *       // 1. 指定延迟时间
     *      // 参数1 = 时间；参数2 = 时间单位
     *       delay(long delay,TimeUnit unit)
     *
     *       // 2. 指定延迟时间 & 调度器
     *      // 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器
     *       delay(long delay,TimeUnit unit,mScheduler scheduler)
     *
     *       // 3. 指定延迟时间  & 错误延迟
     *       // 错误延迟，即：若存在Error事件，则如常执行，执行后再抛出错误异常
     *       // 参数1 = 时间；参数2 = 时间单位；参数3 = 错误延迟参数
     *       delay(long delay,TimeUnit unit,boolean delayError)
     *
     *       // 4. 指定延迟时间 & 调度器 & 错误延迟
     *      // 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器；参数4 = 错误延迟参数
     *      delay(long delay,TimeUnit unit,mScheduler scheduler,boolean delayError): 指定延迟多长时间并添加调度器，错误通知可以设置是否延迟
     */
    private void functionDelay() {
        Observable.just(1, 2, 3)
                .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });

    }

    /**
     *    作用 :在某个事件的生命周期中调用
     *    类型:do（）操作符有很多个，具体如下
     *    1、Observerable()每次发送数据都会调用一次：doOnEach（）  onNext（） onError() onComplete()
     *    2、Next事件：执行next前调用：doOnNext（）。执行next（）后调用：doAfterNext（）
     *    3、发起事件完毕后调用：发生错误时调用：doOnError（）。正常发送时间完毕后：doAfterComplete（）。无论正常发送/异常终止：doAfterTerminate（）。最后执行：doFinally()
     *    4、订阅相关：观察者订阅时调用：doOnSubscribe（）。观察者取消订阅时调用：doOnUnSubscribe（）。
     */
    private void functionDo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Logs.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logs.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logs.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logs.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logs.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Logs.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logs.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logs.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
     * b. 作用类似于interval（），但可指定发送的数据的数量
     */
    private void createIntervalRange() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(3,10,2, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }

                });

    }

    /**
     *   1、快速创建1个被观察者对象（Observable）
     *   2、发送事件的特点：每隔指定时间 就发送 事件
     *   发送的事件序列 = 从0开始、无限递增1的的整数序列
     *
     */
    private void createInterval() {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(3,1,TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }

                });

// 注：interval默认在computation调度器上执行
// 也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)

    }

    /**
     *      延迟指定时间（long）操作
     *      本质 = 延迟指定时间后，调用一次 onNext(0)
     */
    private void createTimer() {

        // 该例子 = 延迟2s后，发送一个long类型数值
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }

                });

// 注：timer操作符默认运行在一个新线程上
// 也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)

    }

    /**
     *    延迟操作
     *     直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
     */
    public void createDefer() {
              // <-- 1. 第1次对i赋值 ->>
        i = 10;

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        //<-- 2. 第2次对i赋值 ->>
                i = 15;

       // <-- 3. 观察者开始订阅 ->>
                // 注：此时，才会调用defer（）创建被观察者对象（Observable）
                observable.subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到的整数是"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });

    }

    /**
     *   创建操作符fromIterable（）链表
     *
     */
    private void createFromIterable() {
        /*
         * 快速发送集合
         **/
// 1. 设置一个集合
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

// 2. 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }
                });




    }

    /**
     * 创建操作符 fromArray（）
     * 1、 快速创建1个被观察者对象（Observable）
     * 2、发送事件的特点：直接发送 传入的数组数据
     * 3、会将数组中的数据转换为Observable对象
     */
    private void createFromArray() {
        // 1. 设置需要传入的数组
        Integer[] items = { 0, 1, 2, 3, 4 };

        // 2. 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(items)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logs.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Logs.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logs.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Logs.d(TAG, "对Complete事件作出响应");
                    }

                });
    }



    /**
     * 创建操作符 快速创建 just（）
     * 1、快速创建1个被观察者对象（Observable）
     * 2、发送事件的特点：直接发送 传入的事件
     * 3、最多发送10个事件
     */
    private void operateJust() {

        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        Observable.just(1,2,3,4)
                // 至此，一个Observable对象创建完毕，以下步骤仅为展示一个完整demo，可以忽略
                // 2. 通过通过订阅（subscribe）连接观察者和被观察者
                // 3. 创建观察者 & 定义响应事件的行为
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logs.d(TAG, "开始采用subscribe连接");
            }
                    // 默认最先调用复写的 onSubscribe（）
            @Override
            public void onNext(@NonNull Integer integer) {
                Logs.d(TAG, "接收到了事件"+ integer  );
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logs.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Logs.d(TAG, "对Complete事件作出响应");
            }
        });

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
                Logs.d(TAG, "开始采用subscribe连接");
                Logs.d(TAG, " 观察者 Observer的工作线程是: " + Thread.currentThread().getName());
                // 打印验证

            }
            @Override
            public void onNext(Integer value) {
                Logs.d(TAG, "对Next事件"+ value +"作出响应"  );
            }
            @Override
            public void onError(Throwable e) {
                Logs.d(TAG, "对Error事件作出响应");
            }
            @Override
            public void onComplete() {
                Logs.d(TAG, "对Complete事件作出响应");
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
                        Logs.d(TAG, "第1次观察者Observer的工作线程是： " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread()) // 第二次指定观察者线程 = 新的工作线程
                .doOnNext(new Consumer<Integer>() { // 生产事件
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logs.d(TAG, "第2次观察者Observer的工作线程是： " + Thread.currentThread().getName());
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
