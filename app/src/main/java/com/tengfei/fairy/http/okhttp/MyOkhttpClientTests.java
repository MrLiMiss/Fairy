package com.tengfei.fairy.http.okhttp;

import android.content.Context;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.tengfei.fairy.test.reflect.Father;
import com.tengfei.fairy.utils.Logs;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * HTTP是现代应用常用的一种交换数据和媒体的网络方式，高效地使用HTTP能让资源加载更快，节省带宽。
 * OkHttpClient是一个高效的HTTP客户端，它有以下默认特性：
 * 1、支持HTTP/2，允许所有同一个主机地址的请求共享同一个socket连接
 * 2、连接池减少请求延时
 * 3、透明的GZIP压缩减少响应数据的大小
 * 4、缓存响应内容，避免一些完全重复的请求
 * 5、当网络出现问题的时候OkHttp依然坚守自己的职责，它会自动恢复一般的连接问题，如果你的服务有多个IP地址，
 * 当第一个IP请求失败时，OkHttp会交替尝试你配置的其他IP，OkHttp使用现代TLS技术(SNI, ALPN)初始化新的连接，当握手失败时会回退到TLS 1.0。
 *
 * @ Description :OkhttpClient测试
 * @ Author 李腾飞
 * @ Time 2020-11-10   13:15
 * @ Version :
 */
public class MyOkhttpClientTests {
    public static String TAG = "MyOkhttpClientTests";


    public static void main(String[] args) throws Exception {
        //1、异步get
//        asynchronousGet();
        //2、同步get
//        synchronizeGet();
        //3、post 发送 String
//        myPostString();
        // 4、post发送文件
//        myPostFile();
        //5、post发送表单
//        myPostForm();
        //6、 post方式提交分块请求
//        postMultipartBody();
        //7、 get 发送请求 添加过滤器 监听 请求发送 请求接收 时间
        get2();
//        System.out.println("Daughter,fatherName:"+fatherName);
    }


    /**
     * 1、异步 GET请求
     */
    public static void asynchronousGet() {
        String url = "http://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG + " asynchronousGet " + "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(TAG + " asynchronousGet " + response.body().string());
            }
        });
    }


    /**
     * 2、同步 GET请求
     */
    public static void synchronizeGet() {
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG + " synchronizeGet " + "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(TAG + " synchronizeGet " + "onResponse: " + response.body().string());
            }
        });
    }


    /**
     * 3、 post 发送 String
     */
    public static void myPostString() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "I am Jdqm.";
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG + " onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(TAG + " " + response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(TAG + " " + headers.name(i) + ":" + headers.value(i));
                }
                System.out.println(TAG + " onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 4、 post 发送文件
     */
    public static void myPostFile() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File("test.md");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, file))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG + " onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(TAG + headers.name(i) + ":" + headers.value(i));
                }
                System.out.println(TAG + " onResponse: " + response.body().string());
            }
        });

    }


    /**
     * 5、post 发送表单
     */
    public static void myPostForm() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(TAG + " onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                System.out.println(TAG + " onResponse: " + response.body().string());
            }
        });
    }


    /**
     * 6、POST方式提交分块请求(测试请求失败)
     *
     * MultipartBody 可以构建复杂的请求体，与HTML文件上传形式兼容。多块请求体中每块请求都是一个请求体，
     * 可以定义自己的请求头。这些请求头可以用来描述这块请求，例如它的 Content-Disposition 。
     * 如果 Content-Length 和 Content-Type 可用的话，他们会被自动添加到请求头中。
     */
    private static void postMultipartBody() {

        final String IMGUR_CLIENT_ID = "...";
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        OkHttpClient client = new OkHttpClient();

        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        MultipartBody body = new MultipartBody.Builder("AaB03x")
                .setType(MultipartBody.FORM)
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(
                        Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());

            }

        });
    }

    /**
     * okhttp3  get 请求  （添加LoggingInterceptor 全局拦截器）
     */
    public static void  get2(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print(TAG+"  onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    System.out.print(TAG+"  onResponse: " + response.body().string());
                    body.close();
                }
            }
        });
    }


}
