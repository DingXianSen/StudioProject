package com.example.lesson12_data_storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * Created by 怪蜀黍 on 2016/11/21.
 */

public class SDCardActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit;
    private TextView result;
    private CheckBox isAppend;
    //    要申请的权限
    private String permiss = "android.permission.WRITE_EXTERNAL_STORAGE";

    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rom);
        edit = (EditText) findViewById(R.id.edit);
        result = (TextView) findViewById(R.id.result);


        isAppend = (CheckBox) findViewById(R.id.append);
//        if(Build.VERSION.SDK_INT>=23){
//        或者，M 6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            检查是否有权限,检查自身的权限 参数2：要检查的权限，权限上边已经定义
            int result = PermissionChecker.checkSelfPermission(this, permiss);
//            已经获取 PermissionChecker.PERMISSION_GRANTED
            if (result != PermissionChecker.PERMISSION_GRANTED) {//如果没有权限，申请权限
//                参数1：要申请的权限的数组，参数2：请求码，同startActivityForResult
                requestPermissions(new String[]{permiss}, 10);
            }
        }


        image= (ImageView) findViewById(R.id.image);
//        参数1：应用整体的资源对象
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        创建图形矩阵
        Matrix matrix=new Matrix();
        matrix.postScale(2,2);//缩放，浮点数，x,y的缩放倍数，不能将缩放倍数写为0
        matrix.postRotate(180);//旋转，角度180°
        matrix.postSkew(0,0.5f);//倾斜，值表示x,y倾斜倍数
//        重新按照矩阵创建图片
//        1：被操作的原图片,0,0要操作的左上角,最后参数：图片过滤，颜色变好看
        bmp= Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
        image.setImageBitmap(bmp);

        //直接使用矩阵
//        直接给ImageView设置矩阵，要求scaleType为matrix
//        image.setImageResource(R.mipmap.ic_launcher);
//        image.setImageMatrix(matrix);
    }

    //重写方法，看有没有获取到权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
//            如果多个权限permissions[0]
//            参数1：如果权限没有被同意
            if (grantResults[0] != PermissionChecker.PERMISSION_GRANTED) {
                Toast.makeText(this, "文件操作权限是必须的，请同意此权限", Toast.LENGTH_SHORT).show();
                finish();//关闭界面
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                write();
                break;
            case R.id.read:
                read();
                break;
        }
    }

    //    读取
    private void read() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = Environment.getExternalStorageDirectory();
            File my = new File(file, "a/my.txt");
            if (my.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(my);
                    byte[] b = new byte[1024];
                    int len = fis.read(b);
                    result.setText(new String(b, 0, len));
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //    写入
    private void write() {
//        判断SDCard存不存在
//        获取SDCard状态，判断是否可以读写
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//SDCard可读写
//                new File("/mnt/sdcard/ttt.txt");//这是固定路径，这样不太好，
            try {
                File sdcardRoot = Environment.getExternalStorageDirectory();//获取内存扩展目录，既SDCard的根目录
//            创建文件
//            File my=new File("mnt/sdcard/my.txt");
//            File my=new File(sdcardRoot.getPath(),"my.txt");//如果放在a文件夹下，就是a/my.txt
                File my = new File(sdcardRoot, "a/my.txt");
//            new File(new URI("file://mnt/sdcard/a/my.txt"));//不常用
//            判断文件是否已存在
                if (my.exists()) {
                    //如果文件存在则删除  注意：这种方式只能删除文件或者空文件夹
                    my.delete();
                }
//            获取父级文件夹，是否存在
                if (!my.getParentFile().exists()) {
//                比如a/b/c/txt.txt   ,mkdir只创建c,mkdirs创建abc
//                my.getParentFile().mkdir和my.getParentFile().mkdirs();的区别();
                    //创建目录，若祖籍不存在，一起创建
                    my.getParentFile().mkdirs();
                }
//            新建文件,判断是否创建成功
                if (my.createNewFile()) {
                    //                创建文件输出流
                    FileOutputStream fos = new FileOutputStream(my, isAppend.isChecked() ? true : false);
                    String str = edit.getText().toString();
                    fos.write(str.getBytes());
                    fos.flush();//刷新
                    fos.close();//关闭
                } else {
                    Toast.makeText(this, "文件创建失败", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "SDCard不可读写", Toast.LENGTH_SHORT).show();
        }
    }
}
