package com.tengfei.fairy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.DialogHelper;

/**
 * @ Description :登录页面
 * @ Author 李腾飞
 * @ Time 2020-08-05   13:47
 * @ Version :
 */
public class LoginActivity extends Activity  implements View.OnClickListener{

    private EditText account;
    private Button bt_login;
    private DialogHelper dialogHelper;
    private Button btn_regester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        account = findViewById(R.id.et_account);
        bt_login = findViewById(R.id.btn_login);
        btn_regester = findViewById(R.id.btn_regester);
        btn_regester.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dialogHelper = new DialogHelper(LoginActivity.this);
        switch (view.getId()){
            case R.id.btn_login:
                if(account.getText().length()==0||account.getText().equals("")){
                    dialogHelper.alert("温馨提示", "密码输入为空，请重新输入！", "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    },"取消", null);
                }else if (account.getText().toString().equals("123321")){
                   Toast.makeText(this,"密码正确",Toast.LENGTH_LONG);
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {

                    dialogHelper.alert("温馨提示", "输入密码错误！"+account.getText()+"请重新输入。", "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    },"取消", null);
                }
                break;
            case R.id.btn_regester:
                dialogHelper.alert("温馨提示", "注册功能暂未开放，敬请期待", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                },"取消",null);
                break;
            default:
                break;
        }
    }
}
