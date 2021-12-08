package com.tengfei.fairy.androidBase.konwledge;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

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
                break;
            case R.id.tv_pattern:
                detailPattern();
                break;
            case R.id.tv_throwable:
                detailThrowable();
                break;
        }
    }

    /**
     * Throwable：
     * 1.所有的异常都是从Throwable继承而来的，是所有异常的共同祖先。
     *
     * 2.Throwable有两个子类，Error和Exception。其中Error是错误，对于所有的编译时期的错误以及系统错误都是通过Error抛出的。
     * 这些错误表示故障发生于虚拟机自身、或者发生在虚拟机试图执行应用时，如Java虚拟机运行错误（Virtual MachineError）、类定义错误（NoClassDefFoundError）等。
     * 这些错误是不可查的，因为它们在应用程序的控制和处理能力之外，而且绝大多数是程序运行时不允许出现的状况。对于设计合理的应用程序来说，即使确实发生了错误，本质上也不应该试图去处理它所引起的异常状况。
     * 在 Java中，错误通过Error的子类描述。
     *
     * 3.Exception，是另外一个非常重要的异常子类。它规定的异常是程序本身可以处理的异常。异常和错误的区别是，异常是可以被处理的，而错误是没法处理的。
     *
     * 4.Checked Exception：可检查的异常，这是编码时非常常用的，所有checked exception都是需要在代码中处理的。
     * 它们的发生是可以预测的，正常的一种情况，可以合理的处理。比如IOException，或者一些自定义的异常。除了RuntimeException及其子类以外，都是checked exception。
     *
     * 5.Unchecked Exception：RuntimeException及其子类都是unchecked exception。比如NPE空指针异常，除数为0的算数异常ArithmeticException等等，这种异常是运行时发生，无法预先捕捉处理的。
     * Error也是unchecked exception，也是无法预先处理的。
     *
     */
    private void detailThrowable() {

    }

    /**
     * 正则表达式
     */
    private void detailPattern() {

    }


    /**
     * 1、Html.fromHtml Android 系统不支持字体大小和加粗样式，即不能通过默认string完成字体大小的哦哦控制
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
        mTvHtmlForHtml.setText(Html.fromHtml(String.format(getResouseString(R.string.type),"无知无畏")));

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
