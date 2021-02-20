package com.tengfei.fairy.activityLificycle;

import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :activity 相关内容测试
 * @ Author 李腾飞
 * @ Time 2021/2/20   11:11
 * @ Version :
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.btn_screen)
    Button btn_screen;
    @BindView(R.id.btn_jumplifecycle)
    Button btn_jumplifecycle;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initGui() {

    }

    @OnClick({R.id.btn_jumplifecycle, R.id.btn_screen})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_screen://横竖屏切换
                IntentUtils.toConfigChenagedActivity(this.activity);
                break;
            case R.id.btn_jumplifecycle:
                IntentUtils.toLifeCycleActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
