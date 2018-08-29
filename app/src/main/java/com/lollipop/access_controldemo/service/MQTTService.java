/**
 * Copyright © 2014 All rights reserved.
 *
 * @Title: MQTTService.java
 * @Prject: CabletechAppStore
 * @Package: com.cabletech.appstore.service
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014�?�?1�?下午4:28:27
 * @version: V1.0
 */
package com.lollipop.access_controldemo.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.lollipop.access_controldemo.Constant;
import com.lollipop.access_controldemo.R;
import com.lollipop.access_controldemo.fragment.MyFragment;
import com.lollipop.access_controldemo.ui.MainActivity;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @ClassName: MQTTServer
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月15日 上午11:02:52
 */
public class MQTTService extends Service {


    private MqttClient mqttClient;
    int notificationID = 1;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public void connect() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            //mqttClient.setCallback(new PushCallback());
            options.setConnectionTimeout(10);
            // 设置会话心跳时间
            options.setKeepAliveInterval(20);
            mqttClient.connect(options);
            mqttClient.subscribe(Constant.CLIENT_TOPIC);
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            Log.i("Log", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 消息推送
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        try {
            mqttClient = new MqttClient(Constant.MQTT_SERVERURL, Constant.CLIENT_ID,
                    new MemoryPersistence());
            connect();

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    // TODO Auto-generated method stub
                    if(topicName.equals("HelloWorld")){
                        /**
                         * 消息推送方法
                         * displayNotification()
                         */
                        displayNotification(message.toString());
                        Log.i("Message",message.toString());
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // TODO Auto-generated method stub

                }

                public void connectionLost(Throwable cause) {
                    // 连接丢失后，一般在这里面进行重连
                    System.out.println("连接断开，可以做重连");
                    connect();
                }

            });

        } catch (MqttException e) {
            // TODO Auto-generated catch block
            Log.i("Log", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            mqttClient.disconnect(0);
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            Log.i("Log", e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * 显示通知
     * @author:俞泽峰
     */
    private void displayNotification(String content) {
        Intent i = new Intent(this, MyFragment.class);
        i.putExtra("notificationID", notificationID);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, i, 0);
        NotificationManager nm = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, this.getPackageName())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("消息提醒")
                .setContentText("内容:"+content)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .addAction(R.mipmap.ic_launcher, "Notzuonotdied",
                        pendingIntent);
        assert nm != null;
        nm.notify(notificationID, notifBuilder.build());
    }
}
