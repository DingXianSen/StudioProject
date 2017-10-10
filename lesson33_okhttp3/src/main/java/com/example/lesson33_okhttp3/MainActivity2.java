package com.example.lesson33_okhttp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*****************************有了Http之后************************************************/

                OkHttpClient client = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
//        地址，默认以get方式进行访问
                builder.url("http://121.40.138.33/qqz/index.php/Home/upload/upfile.html");
//        builder.get();//这是明确指定那种访问方式

                RequestBody body = null;
////        单纯的就是表单数据上传
//        body=new FormBody.Builder()
//                .addEncoded("name","胡八一")
//                .add("age","未知")
//                .build();

//        多类型数据上传,也就是文件上传
                File file = new File("/sdcard/global/062809575220.png");
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
                body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)//第一件事设置类型
                        .addFormDataPart("type", "image")
                        .addFormDataPart("uid", "703")
                        .addFormDataPart("address", "北京市八宝山万达广场")
                        .addFormDataPart("duration", "0")
                        .addFormDataPart("upload", "062809575220.png", fileBody)//添加图片上传，2图片名，3文件体
                        .build();

                builder.post(body);//根据上传的是表单还是文件判断参数

                Call call = client.newCall(builder.build());

//        异步请求
                call.enqueue(callback);

//        同步请求
//        Response response = call.execute();

                /*****************************有了Http之后************************************************/

//        Http.get("http://121.40.138.33/qqz/index.php/Home/upload/upfile.html",callback);

       /* Map<String,Object> params=new HashMap<>();
        params.put("type","image");
        params.put("uid","703");
        params.put("address","北京市八宝山万达广场");
        params.put("duration","0");
        params.put("upload",new File("/sdcard/global/062809575220.png"));//上传的图片文件
        Http.post("http://121.40.138.33/qqz/index.php/Home/upload/upfile.html", params, new JSONCallback<List<String>>() {
            @Override
            public void onSuccess(Call call, List<String> result) {

            }
        });*/
            }
        });
    }

    private Callback callback = new Callback() {
        @Override//没网络的时候
        public void onFailure(Call call, IOException e) {
//            这里边不可以直接操作UI
        }

        @Override// 不管是500还是404都算是有相应
        public void onResponse(Call call, Response response) throws IOException {
            /*if (response.code()==200) {//响应是否成功
                ResponseBody responseBody = response.body();
                responseBody.string();
            }*/
            response.toString();
        }
    };
}
