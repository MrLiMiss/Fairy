package com.tengfei.fairy.customView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;

import java.awt.font.TextAttribute;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :自定义view 页面
 * @ Author 李腾飞
 * @ Time 2021/6/29   09:44
 * @ Version :
 */
public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.tv_Logo)
    public TextView tv_Logo;
    @BindView(R.id.btn_sign)
    public Button btnSign;
    @BindView(R.id.btn_cropPicyure)
    public Button btnCropPicture;
    @BindView(R.id.iv_back)
    LinearLayout ll_back;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initGui() {
        tv_Logo.setText("自定义VIEW");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v!=null){
                    finish();
                }
            }
        });

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_my_view,R.id.btn_sign,R.id.btn_cropPicyure})
    void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_my_view:
                IntentUtils.toMyViewActivity(activity);
                break;
            case R.id.btn_sign:
                IntentUtils.toSignActivity(activity);
                break;
            case R.id.btn_cropPicyure:
                break;
            default:
                break;
        }

    }
}
