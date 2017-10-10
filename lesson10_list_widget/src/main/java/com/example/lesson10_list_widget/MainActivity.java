package com.example.lesson10_list_widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Struct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//    private ListView listView;//如果想使用listView直接吧gridView换过来就可以
    private GridView gridView;//注意的是，gridView没有设置头和设置脚
    private ImageView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView= (GridView) findViewById(R.id.grid_view);

//        listView = (ListView) findViewById(R.id.list_view);

        emptyView = (ImageView) findViewById(R.id.empty_view);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,new String[]{"aaa","bbb","ccc","ddd","eee","fff","ggg","aaa","bbb","ccc","ddd","eee","fff","ggg"});

//        SimpleAdapter adapter=new SimpleAdapter(this,getData(),android.R.layout.simple_list_item_2,
//                new String[]{"name","phone"},
//                new int[]{android.R.id.text1,android.R.id.text2});
//          自定义
//        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.layout_item,
//                new String[]{"images", "phone", "cb1", "cb2"},
//                new int[]{R.id.images, R.id.textwoshi, R.id.cb1, R.id.cb2});


//        视图绑定器
//        adapter.setViewBinder(viewBinder);


        MyAdapter adapter=new MyAdapter(this,getStudent());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(listener);
//        添加头
//        ImageView image = new ImageView(this);
//        image.setImageResource(R.mipmap.ic_launcher);
//        listView.addHeaderView(image);

//      设置无数据时的视图
        gridView.setEmptyView(emptyView);
    }

    private List<Student> getStudent() {
        List<Student> students=new LinkedList<>();
        for (int i=0;i<20;i++){
            students.add(new Student(i,"name"+i,"19000000000"+i));
        }
        return students;
    }

    private SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder() {
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            switch (view.getId()) {
                case android.R.id.text1:
                    ((TextView) view).setText("姓名：" + textRepresentation);
//                   或者可以，直接return true 或者false
                    break;
                case android.R.id.text2:
                    ((TextView) view).setText("电话：" + textRepresentation);
                    break;
            }
            return false;
        }
    };

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override//参数2：被点击的子项视图 参数3：被点击项在适配器数据中的位置 参数4：被点击的id
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            listView.getSelectedItem();不常用，返回的是object
//            if (position > listView.getHeaderViewsCount() - 1) {
                String str = gridView.getAdapter().getItem(position).toString();//你要获取第几项的内容
                Toast.makeText(MainActivity.this, "=====str" + str + "====position" + position, Toast.LENGTH_SHORT).show();
            }
//        }
    };

    //    public List<HashMap<String,String>> getData() {
//        List<HashMap<String,String>> data=new LinkedList<>();
//        for (int i=0;i<20;i++){
//            HashMap<String,String>map=new HashMap<>();
//            map.put("name","张三"+i);
//            map.put("phone","50000000000"+i);
//            data.add(map);
//        }
//        return data;
//    }

//    自定义布局，注意上边的   private SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder() {方法，返回为false才能显示
    public List<HashMap<String, Object>> getData() {
            List<HashMap<String, Object>> data = new LinkedList<>();
            for (int i = 0; i < 20; i++) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("images", R.mipmap.ic_launcher);//可以直接写名字，但是这个不会自动匹配，需要在上边，姓名那，匹配image， -----int id=getResources().getIdentifier(textRepresentation,"mimpap",getPackageName());----------(ImageView)
                map.put("phone", "50000000000" + i);
                map.put("cb1","woshi"+i);
                map.put("cb2",i%3==0);
                data.add(map);
            }
            return data;
    }
}
