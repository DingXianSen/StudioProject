//package com.example.weather;
//
///**
// * Created by 怪蜀黍 on 2016/11/16.
// */
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.StringReader;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URL;
//import java.util.List;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpResponse;
//import org.apache.;import org.apache.;import org.apache.;import org.apache.;import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//
//import android.app.Activity;
//import android.app.ListActivity;
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class aaa extends ListActivity {
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState); /*setContentView(R.layout.main); String str = getWeatherAsString(); Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();*/
//        setListAdapter(new MyAdpter(MainActivity.this));
//    }
//
//    private static class MyAdpter extends BaseAdapter {
//        List<Weather> list;
//        LayoutInflater inflater;
//
//        public MyAdpter(Context context) {
//            list = readXml();
//            inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int arg0) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int arg0) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            InputStream in = null;
//            Weather w = list.get(position);
//            String icon = w.getIcon();
//            String iconUrl = "" + icon;
//            try {
//                URL url = new URL(iconUrl);
//                try {
//                    in = url.openStream();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.list_item, null);
//                holder = new ViewHolder();
//                holder.icon = (ImageView) convertView.findViewById(R.id.icon_imageView1);
//                holder.week_tv = (TextView) convertView.findViewById(R.id.week_textView1);
//                holder.condition_tv = (TextView) convertView.findViewById(ndition_textView2);
//                holder.low_tv = (TextView) convertView.findViewById(R.id.low_textView3);
//                holder.high_tv = (TextView) convertView.findViewById(R.id.high_textView4);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.icon.setImageBitmap(BitmapFactory.decodeStream(in));
//            holder.week_tv.setText(w.getWeek());
//            holder.condition_tv.setText(w.getCondition());
//            holder.low_tv.setText(w.getLow() + "");
//            holder.high_tv.setText(w.getHight() + "");
//            return convertView;
//        }
//
//        static class ViewHolder {
//            ImageView icon;
//            TextView week_tv;
//            TextView condition_tv;
//            TextView low_tv;
//            TextView high_tv;
//        }
//    }
//
//    static List<Weather> readXml() {
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        try {
//            SAXParser parser = factory.newSAXParser();
//            InputSource is = null;
//            is = new InputSource(new StringReader(getWeatherAsString()));
//            MyHandler dh = new MyHandler();
//            try {
//                parser.parse(is, dh);
//                return dh.list();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//        return null;
//    } // 得到天气情况的xml public static String getWeatherAsString() { String strUrl = ";weather=Beijing"; HttpGet getRequest = new HttpGet(strUrl); DefaultHttpClient client = new DefaultHttpClient(); try { HttpResponse response = client.execute(getRequest); if (response.getStatusLine().getStatusCode() == 200) { HttpEntity entity = response.getEntity(); return EntityUtils.toString(entity); } } catch (ClientProtocolException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } return null; } }
//}
