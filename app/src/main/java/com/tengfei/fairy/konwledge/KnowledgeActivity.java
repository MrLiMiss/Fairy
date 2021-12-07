package com.tengfei.fairy.konwledge;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
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
    TextView mTvHtmlForHtml;
    @BindView(R.id.et_html_forhtml2)
    EditText mEtHtmlForHtml2;

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
     * 1、Html.fromHtml Android 不支持size标签的解析，即不能通过默认string完成字体大小的哦哦控制
     * 2、Html.fromHtml(String) 已过时
     * 3、html 格式的 String 如果存储在String.xml 文件中需要转义HTML标签，不然的话 经过Android处理后 所有的HTML标签都给过滤掉了(需要把所有的“<”用“&lt;”替换)。
     * 这种情况下可以使用XML的CDATA标记:
     *  <string name="anti_fraud_type_2"><Data><![CDATA[     <font color="#999999" size="30px">类型：</font><font color="#FF6D26" size="30px">%1$s</font>    ]]></Data></string>
     *
     * 测试Html.forHtml()
     *
     */
    private void detailHtml() {
        String htmlStr2 =
                "<span style='color:#EE30A7;font-size:20px'>Html" +
                        "<font color='#EE2C2C' size='40px'>字体变大,色值变化</font>"
                        +
                        "<font color='#CD8500' size='60px'>字体变大,色值变化1</font>" +
                        "</span>";

        String strHtml = "<font color=\"#EE2C2C\"size=\"40px\">字体变大,色值变化</font>"
                        + "<font color=\"#CD8500\"size=\"80px\">字体变大,色值也变化了</font>" + "</span>";
        mTvHtmlForHtml.setText(Html.fromHtml(htmlStr2));

        String strHtml2 =
                "<font color='#4F94CD' size='40px'>我已经完成</font>" +
                        "<font color='#FF0000' size='80px'>80%</font>" +
                        "<font color='#4F94CD' size='40px'>的暑假作业</font>";
        mEtHtmlForHtml2.setText(Html.fromHtml(strHtml2));

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
