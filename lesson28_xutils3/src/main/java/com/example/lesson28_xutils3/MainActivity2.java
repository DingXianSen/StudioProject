package com.example.lesson28_xutils3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.HttpManager;
import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.UrlEncodedParamsBody;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//布局绑定
@ContentView(R.layout.activity_main)
public class MainActivity2 extends AppCompatActivity {
    //    控件绑定
    @ViewInject(R.id.image)
    private ImageView image;
    @ViewInject(R.id.text)
    private TextView text;
    @ViewInject(R.id.edit)
    private EditText edit;

    private ImageManager imageManager;
    private DbManager dbManager;
    private HttpManager httpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Xutils     基于HttpClient
//        BitmapUtil 图片操作
//        ViewUtil  视图注解绑定
//        DBUtil    数据库操作
//        HttpUtil  网络连接操作

//        Xutils3    基于OkHttp3
//        ImageManager 图片操作
//        ViewInjector   视图注解
//        DBManager     数据库操作
//        HttpManager   网络连接

//        首先进行初始化,在App中写了
        x.view().inject(this);

        imageManager = x.image();

        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName("my.db");//设置数据库的名称
        daoConfig.setDbVersion(1);//设置数据库的版本
        daoConfig.setAllowTransaction(true);//设置是否允许事物
//        daoConfig.setDbOpenListener();//设置数据库打开监听
//        daoConfig.setDbDir()//设置数据库存放的文件夹，一般不更改
//        daoConfig.setTableCreateListener();//设置表创建的监听
        dbManager = x.getDb(daoConfig);

        httpManager = x.http();//用于文件上传，下载等

    }

    //    之前的版本是@onClick

    /**
     * 点击事件
     *
     * @param v
     */
    @Event({R.id.load_image, R.id.load_text, R.id.insert, R.id.delete, R.id.update, R.id.select})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_image:
                ImageOptions io = new ImageOptions.Builder()
                        .setFadeIn(true)//图片淡入
                        .setIgnoreGif(false)//忽略gif图片
                        .setFailureDrawableId(R.mipmap.ic_launcher)//失败时的图片
                        .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中的占位图
                        .setSize(200, 200)//对图片大小重新设定,设置图片大小，防止图片过大造成内存溢出，常用
                        .build();
//                setPlaceholderScaleType();//占位图的缩放
//                .setSquare()//设置是否为方形
//                .setRadius()//设置圆角
//                .setCircular(true)//设置是否是圆形
//                .setImageScaleType()//缩放类型
//                https://www.baidu.com/img/bd_logo1.png
                imageManager.bind(image, "https://www.baidu.com/img/bd_logo1.png");//指定图片的地址
//                imageManager.bind(image, "assets://in.gif",io);
//                读取SDCard文件要有权限
                break;


            case R.id.insert:
                try {
                    dbManager.getDatabase().beginTransaction();
                    User user = new User();
                    user.setAge(34);
                    user.setName("where");
                    user.setPhone("120");
//                dbManager.saveBindingId()
//                dbManager.saveOrUpdate();//已经存在话，更新，否则保存
                    dbManager.save(user);
                    dbManager.getDatabase().setTransactionSuccessful();
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    dbManager.getDatabase().endTransaction();
                }
                break;

            case R.id.delete:


                try {
//                dbManager.delete(User.class);//删除所有
//                dbManager.deleteById(User.class,1);//按id删除
                    int len = dbManager.delete(User.class, WhereBuilder.b("age", ">", "90"));
                    T.show("共删除" + len + "个");
//                dbManager.delete(user);//删除指定的对象
                } catch (DbException e) {
                    e.printStackTrace();
                    T.show("删除失败");
                }
                break;
            case R.id.update:
//                dbManager.update(user,"age","name");//更新时指定对象的那些列
//                按条件更新指定列
                try {
                    int num = dbManager.update(User.class, WhereBuilder.b("age", ">", "30")
                            , new KeyValue("age", 100), new KeyValue("name", "张三"));
//                dbManager.saveOrUpdate(user);
                    T.show("更新了" + num + "列");
                } catch (DbException e) {
                    e.printStackTrace();
                    T.show("更新失败");
                }
                break;
            case R.id.select:
                try {
                    List<User> users = dbManager.findAll(User.class);
//                dbManager.findFirst(User.class);//查找第一个
//                dbManager.findById(User.class,1);//根据id查找
//                    按条件查询
//                    dbManager.selector(User.class)
//                            .where("age", ">=", 10)//查找age大于等于10
//                            .and("phone", "like", "180%")
//                            .or("name", "like", "wh%")
//                            .orderBy("age desc")
//                            .groupBy("id")
//                            .having(WhereBuilder.b("age", "<", "120").and("age", ">", "1"))
//                            .offset("0")
//                            .limit(10)
//                            .findFirst()
//                            .findAll();
                    for (User u : users) {
                        Log.e("aaaa", u.getId() + "=====================" + u.getName());
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.load_text:
                load();
                break;
        }
    }

    private void load() {
        RequestParams rp = new RequestParams("http://");//请求的地址，可以直接设定
//        rp.setSaveFilePath();//下载文件的储存位置
//        rp.setAutoResume();//下一次是否自动开始，断点续传
//        rp.setAutoRename();//自动重命名，在可以得到文件名的情况下
//        rp.setConnectTimeout(1000);//连接超时时间
//        rp.setCancelFast(true);//设置可否快速取消
//        rp.setMultipart(true);//文件上传时必须设置
//        rp.setRequestBody();//设置post上传的参数
//        第二个参数，回调
//        可通过返回值cancelable取消网络请求
        Callback.Cancelable cancelable = httpManager.get(rp, new Callback.CommonCallback<String>() {//如果下载文件的时候，String改为File
            @Override//访问成功
            public void onSuccess(String s) {
                T.show(s);
            }

            @Override//访问错误
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
            }

            @Override//取消访问
            public void onCancelled(CancelledException e) {

            }

            @Override//完成
            public void onFinished() {

            }
        });
//        cancelable.cancel();//取消网络请求

    }

    //    上传的是文件
    private void loadPostFile() {
        RequestParams rp = new RequestParams("http://");
        rp.setMultipart(true);//必须设置

        List<KeyValue> kvs = new ArrayList<>();
//        KeyValue keyValue = new KeyValue("photo", new FileBody(file, "image/jpg"));
//        kvs.add(keyValue);

        KeyValue keyValue1 = new KeyValue("name", "李四");
        kvs.add(keyValue1);

        RequestBody body = new MultipartBody(kvs, "utf-8");
        rp.setRequestBody(body);
    }

    private void loadPost(List<KeyValue> data) {//更新操作时的keyView
        RequestParams rp = new RequestParams("http://");
        //如果仅仅是表单的上传
//        仅上传普通的字段
        RequestBody body = null;
        try {
            body = new UrlEncodedParamsBody(data, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        rp.setRequestBody(body);
        httpManager.post(rp, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String s) {
                return false;
            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

//        文件下载进度条
        Callback.ProgressCallback<File> v = new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long l, long l1, boolean b) {

            }

            @Override
            public void onSuccess(File file) {

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        };
    }

    /**
     * 输入监听
     *
     * @param s
     */
    @Event(value = R.id.edit,
            type = TextWatcher.class,
            setter = "addTextChangedListener",
            method = "afterTextChanged")
    private void afterTextChanged(Editable s) {
        Log.e("aaaa", "==================" + s.length() + "==================");
    }

    /**
     * list选项长按事件
     */
//    @Event(value = R.id.list,
//    type = AdapterView.OnItemLongClickListener.class)
//    public boolean onItemLongClick(AdapterView<?> adapterView,View view,int i,long l){
//        return false;
//    }
}
