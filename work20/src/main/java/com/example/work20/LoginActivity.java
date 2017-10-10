package com.example.work20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
//    定义返回码
    private static final int NOW_REGISTER=2;
    private static final int FORGET_PASSWORD=3;
    private EditText etPhone,etPassword;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone= (EditText) findViewById(R.id.et_phone);
        etPassword= (EditText) findViewById(R.id.et_password);
        queue= Volley.newRequestQueue(this);
    }

//    Login界面的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login://登录
//                获取登录信息
                String phone=etPhone.getText().toString();
                String password=etPassword.getText().toString();
                String url="http://toolsmi.com/Veeker/login?phone="+phone+"&pwd="+password+"&type=0";
                login(url,phone,password);
                break;
            case R.id.tv_nowregister://马上注册
                Intent intent_nowregister=new Intent(LoginActivity.this,RegiestActivity.class);
                startActivityForResult(intent_nowregister,NOW_REGISTER);
                break;
            case R.id.tv_forgetpassword://忘记密码
                Intent intent_frogetpassword=new Intent(LoginActivity.this,RegiestActivity.class);
                startActivityForResult(intent_frogetpassword,FORGET_PASSWORD);
                break;
        }
    }

    private void login(String url,String phone, String password) {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("aaaa","======================error"+jsonObject);
                try {
                    String error=jsonObject.getString("error");
                    Log.e("aaaa","======================error"+error);
                    if (error.equals("NO_ERROR")){
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        Toast.makeText(LoginActivity.this, "登陆成功，跳转到主界面", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }else if (error.equals("PHONE_HAS_REGISTERED")){
                        Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
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
}
