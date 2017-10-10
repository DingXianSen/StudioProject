package com.example.work10;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 怪蜀黍 on 2016/11/16.
 */

public class BActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_day;
    //                      城市          温度              风力      湿度          日出          建议
    private TextView day_city, day_temperature, day_wind, day_humidity, day_sunrise, advise;

    private String str;

    private LayoutInflater lif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_day = (Button) findViewById(R.id.btn_day);

        day_city = (TextView) findViewById(R.id.city);   //城市
        day_temperature = (TextView) findViewById(R.id.temperature); //温度
        day_wind = (TextView) findViewById(R.id.wind);   //风力
        day_humidity = (TextView) findViewById(R.id.humidity);   //湿度
        day_sunrise = (TextView) findViewById(R.id.sunrise); //日出
        advise = (TextView) findViewById(R.id.advise);   //建议

    }

    /**
     * 点击时间，点击是获取天气信息
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        new Getweather().execute("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100");
        Toast.makeText(this, "...................", Toast.LENGTH_SHORT).show();
    }

    class Getweather extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(params[0]);//先创建访问地址，这个地址是onClick点击时，传递的地址，也就是访问天气的地址
                conn = (HttpURLConnection) url.openConnection();//创建连接，因为url.openConnection()返回的是不带HTTP协议的，所以请值类型转换
                conn.setRequestMethod("GET");//打开连接之后，设置请求方式
//                获取结果码
                int code = conn.getResponseCode();
//                如果访问成功
                if (code == 200) {
                    InputStream is = conn.getInputStream();//获取内容返回输入流
//                    将读取到的字符串进行临时存储
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] b = new byte[1024];
                    int len;
                    int totalLength = conn.getContentLength();
                    while ((len = is.read(b)) != -1) {//len记录每次读取的长度
                        bos.write(b, 0, len);
//                        b=bos.toByteArray();//将返回内容创建成字符串
                        str = new String(bos.toByteArray());
                        return str;
                    }
                    Log.e("aaa", "=====" + str);
                    pareseJSONWithJSONObject(str);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 解析JSON
     *
     * @param str
     */
    private void pareseJSONWithJSONObject(String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                这里根据对应的
                String info_city = jsonObject.getString("city");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
/*Json数据
*
*
* {"desc":"OK","status":1000,"data":
* {"wendu":"3","ganmao":"将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。",* "forecast":
* [{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 11℃","type":"晴","low":"低温 2℃","date":"16日星期三"},
* {"fengxiang":"无持续风向","fengli":"微风级","high":"高温 7℃","type":"霾","low":"低温 5℃","date":"17日星期四"},
* {"fengxiang":"无持续风向","fengli":"微风级","high":"高温 9℃","type":"霾","low":"低温 7℃","date":"18日星期五"},
* {"fengxiang":"北风","fengli":"3-4级","high":"高温 13℃","type":"霾","low":"低温 3℃","date":"19日星期六"},
* {"fengxiang":"无持续风向","fengli":"微风级","high":"高温 6℃","type":"小雨","low":"低温 0℃","date":"20日星期天"}],
* "yesterday":{"fl":"微风","fx":"无持续风向","high":"高温 11℃","type":"晴","low":"低温 0℃","date":"15日星期二"},
* "aqi":"172","city":"北京"}}*/