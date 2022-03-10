package com.tengfei.fairy.oom.singleIntense;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/3/10   7:35 PM
 * @ Version :
 */
public class SecondOOMActivity extends BaseActivity {
    private Button mBtn;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_secondoom;
    }

    @Override
    protected void initGui() {
        mBtn=findViewById(R.id.btn_tothird);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleInstance instance = SingleInstance.getInstance(SecondOOMActivity.this);
                instance.say();

                Intent intent = new Intent(SecondOOMActivity.this, LastActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
