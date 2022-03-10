package com.tengfei.fairy.oom.singleIntense;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

/**
 * @ Description :单例模式导致OOM分析
 * @ Author 李腾飞
 * @ Time 2022/3/10   7:26 PM
 * @ Version :
 */
public  class SingleOOMActivity extends BaseActivity  implements View.OnClickListener{
    Button button;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_singeloom;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_singel:
                Intent intent=new Intent(SingleOOMActivity.this,SecondOOMActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    @Override
    protected void initGui() {
        button=findViewById(R.id.btn_singel);
        button.setOnClickListener(this);

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

}
