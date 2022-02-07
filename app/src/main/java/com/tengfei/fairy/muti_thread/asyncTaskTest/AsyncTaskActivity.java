package com.tengfei.fairy.muti_thread.asyncTaskTest;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :AsyncTask 相关测试
 * @ Author 李腾飞
 * @ Time 2020/12/10   11:12
 * @ Version :
 */
public class AsyncTaskActivity extends BaseMvpActivity<BasePresenter> {
    private static final String TAG = AsyncTaskActivity.class.getSimpleName();
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.btn_start)
    Button mButton;
    @BindView(R.id.tv_result)
    TextView textView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_asynctask;
    }

    @OnClick({R.id.btn_start})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Logs.d(TAG, "");
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(1000);
                break;
            default:
                break;
        }

    }

    public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        /**
         * 异步任务：AsyncTask<Params, Progress, Result>
         * 1.Params:UI线程传过来的参数。
         * 2.Progress:发布进度的类型。
         * 3.Result:返回结果的类型。耗时操作doInBackground的返回结果传给执行之后的参数类型。
         * <p>
         * 执行流程：
         * 1.onPreExecute():执行后台耗时操作之前被调用，用于用于一些初始化操作。
         * 2.doInBackground()-->onProgressUpdate()     doInBackground（）调用onProgressUpdate()方法 实时更新耗时任务进度
         * 3.onPostExecute();后台耗时操作完成后，UI线程调用该方法，完成耗时操作结果传递给UI线程
         */




        @Override
        protected void onProgressUpdate(Integer... values)//执行操作中，发布进度后
        {
            mProgressBar.setProgress(values[0]);//每次更新进度条
        }


        @Override
        protected void onPreExecute()//执行耗时操作之前处理UI线程事件
        {
            mProgressBar.setVisibility(View.VISIBLE);//点击之后，下载执行之前，设置进度条可见
            textView.setText("开始执行异步线程~");
        }

        @Override
        protected String doInBackground(Integer... params)//执行耗时操作
        {
            DelayOperator dop = new DelayOperator();
            int i = 0;
            for (i = 10; i <= 100; i += 10) {
                dop.delay();
                publishProgress(i);
            }
            return i + params[0].intValue() + "";
        }

        @Override
        protected void onPostExecute(String result)//执行耗时操作之后处理UI线程事件
        {
            //在此方法执行main线程操作
            mProgressBar.setVisibility(View.GONE);//下载完成后，隐藏进度条
            textView.setText(result);
        }
    }

    public class DelayOperator {
        //延时操作,用来模拟下载
        public void delay() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
