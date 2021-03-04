package com.tengfei.fairy.javaBase.http.HttpURLConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020/12/17   17:43
 * @ Version :
 */
class PostDemo {
    public static void main(String[] args) {
        String message="";
        try {
            URL url=new URL("http://119.29.175.247/wikewechat/Admin/Login/login.html");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setRequestProperty("Content-type","application/x-javascript->json");
            connection.connect();
            OutputStream outputStream=connection.getOutputStream();
            StringBuffer sb=new StringBuffer();
            sb.append("email=");
            sb.append("409947972@qq.com&");
            sb.append("password=");
            sb.append("1234&");
            sb.append("verify_code=");
            sb.append("4fJ8");
            String param=sb.toString();
            outputStream.write(param.getBytes());
            outputStream.flush();
            outputStream.close();
            InputStream inputStream=connection.getInputStream();
            byte[] data=new byte[1024];
            StringBuffer sb1=new StringBuffer();
            int length=0;
            while ((length=inputStream.read(data))!=-1){
                String s=new String(data, Charset.forName("utf-8"));
                sb1.append(s);
            }
            message=sb1.toString();
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }
}
