package com.tengfei.fairy.konwledge;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :   android 基础知识Activity
 * @ Author 李腾飞
 * @ Time 12/7/21   1:18 PM
 * @ Version :
 */
public class KnowledgeActivity extends BaseMvpActivity<BasePresenter> {
    @BindView(R.id.tv_html_forhtml1)
    TextView mTvHtmlForHtml1;
    @BindView(R.id.tv_html_forhtml2)
    TextView mTvHtmlForHtml2;

    public static String TAG = KnowledgeActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_konwledge;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.tv_html, R.id.tv_pattern, R.id.tv_throwable})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_html:
                detailHtml();
                Logs.d(TAG,"tv_html");
                break;
            case R.id.tv_pattern:
                break;
            case R.id.tv_throwable:
                break;
        }
    }

    /**
     * 测试Html.forHtml()
     */
    private void detailHtml() {
        String strHtml = "<font color=\"#EE2C2C\"size=\"40px\">字体变大,色值变化</font>"
                        + "<font color=\"#CD8500\"size=\"60px\">字体变大,色值又变化1</font>" + "</span>";
        mTvHtmlForHtml1.setText(Html.fromHtml(strHtml));

        String strHtml2 =
                "<font color='#4F94CD' size='40px'>我已经完成</font>" +
                        "<font color='#FF0000' size='80px'>80%</font>" +
                        "<font color='#4F94CD' size='40px'>的暑假作业</font>";
        mTvHtmlForHtml2.setText(Html.fromHtml(strHtml2));

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
