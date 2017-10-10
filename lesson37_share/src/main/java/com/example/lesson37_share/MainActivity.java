package com.example.lesson37_share;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_key_share:
                share();
                break;
            case R.id.share_to_qq:
                shareToQQ();
                break;
        }
    }

    private void shareToQQ() {
//        根据平台名称获取对应的platform
//        WechatMoments.NAME
//        TencentWeibo.NAME
        Platform platform=ShareSDK.getPlatform(QQ.NAME);
        QQ.ShareParams sp=new QQ.ShareParams();
//        分享
        sp.setTitle("分享测试");
        sp.setTitleUrl("http://www.toolmsi.com");
        sp.setImageUrl("http://www.toolmsi.com/tomcat.png");
        sp.setSite("坑你网");
        sp.setSiteUrl("http://www.toolmsi.com");
        platform.share(sp);

    }

    private void share() {
        OnekeyShare oks = new OnekeyShare();
        oks.setText("分享测试");
        oks.setImageUrl("http://www.toolmsi.tomcat.png");
        oks.setTitle("这是一个无用的分享内容");
        oks.setTitleUrl("http://www.toolmsi.com");//点击分享内容跳转的地址
        oks.setSite("坑爹的网站");
        oks.setSiteUrl("http://www.toolmsi.com");
        oks.setUrl("http://www.toolmsi.com");
        oks.show(this);
    }
}