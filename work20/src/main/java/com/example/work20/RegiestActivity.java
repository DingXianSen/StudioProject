package com.example.work20;

import android.content.Intent;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 注册界面
 */
public class RegiestActivity extends AppCompatActivity {
    private EditText re_Phone, re_Password, re_RepeatPassword, re_Nick;
    private Button re_bt_register;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_register);
        queue= Volley.newRequestQueue(this);

        re_Phone = (EditText) findViewById(R.id.et_phone);
        re_Password = (EditText) findViewById(R.id.et_password);
        re_RepeatPassword = (EditText) findViewById(R.id.et_repeatpassword);
        re_Nick = (EditText) findViewById(R.id.et_nick);

        re_bt_register = (Button) findViewById(R.id.bt_register);
        re_bt_register.setOnClickListener(listener);

    }

    //    注册的点击事件
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //获取要注册的信息
            String re_str_phone = re_Phone.getText().toString();//手机号
            String re_str_password = re_Password.getText().toString();//密码
            String re_str_repeatpassword = re_RepeatPassword.getText().toString();//确认密码
            String re_str_nick = re_Nick.getText().toString();//昵称


            String url = "http://toolsmi.com/Veeker/reg?phone=" + re_str_phone + "&pwd=" + re_str_password + "&nick=" + re_str_nick + "&type=0";//请求地址,拼接字符串
            Log.e("aaaa", "=============输出注册时拼接的url地址" + url);
            Log.e("aaaa", "=============queue = Volley.newRequestQueue(this);"+queue);
           JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject jsonObject) {
                   Log.e("aaaa","======================error"+jsonObject);
                   try {
                       String error=jsonObject.getString("error");
                       Log.e("aaaa","======================error"+error);
                       if (error.equals("NO_ERROR")){
                           Toast.makeText(RegiestActivity.this, "恭喜您，注册成功，跳转到登陆界面", Toast.LENGTH_SHORT).show();
                           finish();
                       }else if (error.equals("PHONE_HAS_REGISTERED")){
                           Toast.makeText(RegiestActivity.this, "该手机号已经注册", Toast.LENGTH_SHORT).show();
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                   Log.e("aaaa","============onErrorResponse==========error"+volleyError);
               }
           });
            queue.add(request);
        }
    };
}
