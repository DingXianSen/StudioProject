package com.example.lesson40_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ipaynow.plugin.api.IpaynowPlugin;
import com.ipaynow.plugin.manager.route.dto.ResponseParams;
import com.ipaynow.plugin.manager.route.impl.ReceivePayResult;
import com.ipaynow.plugin.utils.PreSignMessageUtil;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, ReceivePayResult {
    //    签名签的信息工具类
    private PreSignMessageUtil preSignMessageUtil;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createOrder() {
        if (preSignMessageUtil != null) return;
        preSignMessageUtil = new PreSignMessageUtil();
        preSignMessageUtil.appId = "1457316587114441";
//        preSignMessageUtil.mhtLimitPay = "no_credit";//是否支持信用卡(不支持no_credit)
        preSignMessageUtil.mhtCurrencyType = "156";//货币类型
        preSignMessageUtil.mhtCharset = "UTF-8";//编码方式
        preSignMessageUtil.mhtOrderAmt = "10";//订单金额
        preSignMessageUtil.mhtOrderDetail = "的飞洒地方克里斯多夫就";//订单详情
        preSignMessageUtil.mhtOrderName = "香蕉";//商品名
        preSignMessageUtil.mhtOrderNo = "LY1002039485";//订单编号
//        preSignMessageUtil.mhtOrderTimeOut = "3600";//订单超时时间，秒60-3600
        preSignMessageUtil.mhtOrderType = "01";//消费类型，固定01普通消费
        preSignMessageUtil.notifyUrl = "http://www.toolsmi.com";//支付结果服务器通知地址
        //以下可以没有
        preSignMessageUtil.mhtReserved = "要比较生的香蕉";//商户保留域，可作为商品备注
        preSignMessageUtil.consumerId = "1";//用户在商户服务器中的用户编号
        preSignMessageUtil.consumerName = "瘦死的骆驼";//用户在商户服务器中的用户名
    }

    @Override
    public void onClick(View view) {
        createOrder();
        //设置订单时间
        preSignMessageUtil.mhtOrderStartTime = sdf.format(new Date());
        //设置支付渠道
        preSignMessageUtil.payChannelType = (String) view.getTag();
        //生成签名前订单信息字符串
        String preSignMessage = preSignMessageUtil.generatePreSignMessage();
        preSignMessage = preSignMessage + "&" + MD5.md5("c3bb4Yqcw6Zs3xgB9ro21qVzxkzfn4Dx");
        String sgin = MD5.md5(preSignMessage);
        String message = preSignMessage + "&mhtSignature=" + sgin + "&mhtSignType=MD5";
        //如果商户保留域中包含特殊字符，则需要编码以下特殊字符内容
        if (!TextUtils.isEmpty(preSignMessageUtil.mhtReserved)
                && message.contains(preSignMessageUtil.mhtReserved)) {
            message = message.replace(preSignMessageUtil.mhtReserved, URLEncoder.encode(preSignMessageUtil.mhtReserved));
        }

        //初始化插件
        IpaynowPlugin.getInstance().init(this);
        //设置不检查环境，即使未安装客户端应用也显示网关按钮
        IpaynowPlugin.getInstance().unCkeckEnvironment();
        //设置结果回调的receiver
        IpaynowPlugin.getInstance().setCallResultReceiver(this);
        //支付
        IpaynowPlugin.getInstance().pay(message);
    }

    @Override
    public void onIpaynowTransResult(ResponseParams responseParams) {
        String respCode = responseParams.respCode;
        String errorCode = responseParams.errorCode;
        String errorMsg = responseParams.respMsg;
        StringBuilder temp = new StringBuilder();
        if (respCode.equals("00")) {
            temp.append("交易状态:成功");
        } else if (respCode.equals("02")) {
            temp.append("交易状态:取消");
        } else if (respCode.equals("01")) {
            temp.append("交易状态:失败").append("\n").append("错误码:").append(errorCode).append("原因:" + errorMsg);
        } else if (respCode.equals("03")) {
            temp.append("交易状态:未知").append("\n").append("原因:" + errorMsg);
        } else {
            temp.append("respCode=").append(respCode).append("\n").append("respMsg=").append(errorMsg);
        }
        Log.e("aaaa", "========" + temp.toString());
        Toast.makeText(this, "onIpaynowTransResult:" + temp.toString(), Toast.LENGTH_LONG).show();
    }
}