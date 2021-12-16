package com.tengfei.fairy.httpBase.socket;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import butterknife.OnClick;

/**
 * @ Description :java 基础 socket相关
 * @ Author 李腾飞
 * @ Time 12/15/21   3:53 PM
 * @ Version :
 */
public class SocketTextActivity extends BaseMvpActivity<BasePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_socket;
    }

    @OnClick({R.id.btn_accept})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_accept:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            acceptServer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
        }
    }


    private void acceptServer() throws IOException {
        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("127.0.0.1", 8081);
        //2.获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        //获取客户端的IP地址
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        pw.write("客户端：~" + ip + "~ 接入服务器！！");
        pw.flush();
        socket.shutdownOutput();//关闭输出流
        socket.close();
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
