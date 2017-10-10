package com.example.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private String URL_PROVINCE = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx/getSupportProvince";
    private String URL_CITY = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx/getSupportCity";
    private String URL_WEATHER = " http://www.webxml.com.cn/webservices/weatherwebservice.asmx/getWeatherbyCityName";
//    http://www.webxml.com.cn/webservices/weatherwebservice.asmx/getWeatherbyCityName
//    http://wthrcdn.etouch.cn/weather_mini?citykey=101010100
    private List provinceList, cityList;

    private Spinner sp1, sp2;
    private TextView tv, tv0, tv1, tv2, tv3, tv4, tv5, tv6;
    private ImageView[] iv = new ImageView[6];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        tv = (TextView) findViewById(R.id.tv);
        tv0 = (TextView) findViewById(R.id.tv0);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        iv[0] = (ImageView) findViewById(R.id.iv1);
        iv[1] = (ImageView) findViewById(R.id.iv2);
        iv[2] = (ImageView) findViewById(R.id.iv3);
        iv[3] = (ImageView) findViewById(R.id.iv4);
        iv[4] = (ImageView) findViewById(R.id.iv5);
        iv[5] = (ImageView) findViewById(R.id.iv6);
        new MyDoGetProvince().execute(URL_PROVINCE);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("===", "pos=" + position + ",id=" + id);
                if (provinceList != null && provinceList.size() > 0) {
                    String url = URL_CITY + "?byProvinceName=" + provinceList.get(position);
                    new MyDoGetCity().execute(url);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (cityList != null && cityList.size() > 0) {
                    String city = cityList.get(position).toString().substring(0, 2);
                    Log.d("===city", "" + city);
                    String url = URL_WEATHER + "?theCityName=" + city;
                    new MyDoGetWeather().execute(url);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public class MyDoGetProvince extends AsyncTask<String, Void, List> {

        @Override
        protected List doInBackground(String... params) {
            return HttpUtil.doGetXml(params[0]);
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            if (list != null && list.size() > 0) {
                provinceList = list;
                ArrayAdapter aa = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, provinceList);
                sp1.setAdapter(aa);
            }

        }
    }


    public class MyDoGetCity extends AsyncTask<String, Void, List> {

        @Override
        protected List doInBackground(String... params) {
            return HttpUtil.doGetXml(params[0]);
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            if (list != null && list.size() > 0) {
                cityList = list;
                ArrayAdapter aa = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, cityList);
                sp2.setAdapter(aa);
            }

        }
    }


    public class MyDoGetWeather extends AsyncTask<String, Void, List> {

        @Override
        protected List doInBackground(String... params) {
            return HttpUtil.doGetXml(params[0]);
        }

        @Override
        protected void onPostExecute(List list) {
            int[] img = new int[32];
//            img[0] = R.mipmap.a;
//            img[1] = R.mipmap.b;
//            img[2] = R.mipmap.c;
//            img[3] = R.mipmap.d;
//            img[4] = R.mipmap.e;
//            img[5] = R.mipmap.f;
//            img[6] = R.mipmap.g;
//            img[7] = R.mipmap.h;
//            img[8] = R.mipmap.i;
//            img[9] = R.mipmap.j;
//            img[10] = R.mipmap.k;
//            img[11] = R.mipmap.l;
//            img[12] = R.mipmap.m;
//            img[13] = R.mipmap.n;
//            img[14] = R.mipmap.o;
//            img[15] = R.mipmap.p;
//            img[16] = R.mipmap.q;
//            img[17] = R.mipmap.r;
//            img[18] = R.mipmap.s;
//            img[19] = R.mipmap.t;
//            img[20] = R.mipmap.u;
//            img[21] = R.mipmap.v;
//            img[22] = R.mipmap.w;
//            img[23] = R.mipmap.x;
//            img[24] = R.mipmap.y;
//            img[25] = R.mipmap.z;
//            img[26] = R.mipmap.aa;
//            img[27] = R.mipmap.bb;
//            img[28] = R.mipmap.cc;
//            img[29] = R.mipmap.dd;
//            img[30] = R.mipmap.ee;
//            img[31] = R.mipmap.ff;
            super.onPostExecute(list);
            if (list != null && list.size() > 0) {
                StringBuilder sbd = new StringBuilder();
                Log.d("===listsize", "" + list.size());
                int n = 0;
                for (int i = 0; i < list.size(); i++) {
                    String wea = list.get(i).toString();
                    if (wea.contains("gif")) {
                        int index = wea.indexOf(".");
                        Log.d("===index", "" + index);
                        int k = Integer.parseInt(wea.substring(0, index));
                        iv[n++].setImageResource(img[k]);
                    } else if (i == 10) {
                        tv.setText(wea);
                    } else if (i == 12) {
                        tv1.setText(wea);
                    } else if (i == 14) {
                        tv2.setText(wea);
                    } else if (i == 20) {
                        tv0.setText(wea);
                    } else if (i == 26) {
                        tv3.setText(wea);
                    } else if (i == 24) {
                        tv4.setText(wea);
                    } else if (i == 36) {
                        tv5.setText(wea);
                    } else if (i == 34) {
                        tv6.setText(wea);
                    }
                }
            }

        }
    }

}