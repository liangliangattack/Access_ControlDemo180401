package com.lollipop.access_controldemo.ui;



import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lollipop.access_controldemo.Constant;
import com.lollipop.access_controldemo.R;
import com.lollipop.access_controldemo.fragment.CheckRecordFragment;
import com.lollipop.access_controldemo.fragment.LeaveRestFragment;
import com.lollipop.access_controldemo.fragment.MessageFragment;
import com.lollipop.access_controldemo.fragment.MyFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> mFragments;
    //四个布局
    private LinearLayout mTabMessage,mTabCheckRecord,mTabLeaveRest,mTabMy;

    //ImageButton
    private ImageButton ib_message,ib_checkRecord,ib_leaveRest,ib_my;

    //fragment
    private Fragment fm_message,fm_checkRecord,fm_leaveRest,fm_my;

    private File file;
    private Handler handler;
    public static Handler handler_mqtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        /**
         * mqtt的线程
         * @author:俞泽峰
         */
/*        handler_mqtt = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
//                textView.setText(textView.getText().toString() + "\n"
//                        + Constant.SERVER_ID + "：" + msg.obj);
                Log.i("handler_mqtt",Constant.SERVER_ID + "：" + msg.obj);
                //displayNotification(msg.obj.toString());
            }
        };*/
        initView();

        //初始化事件
        initEvents();
        setSelect(0);
    }



    private void initView(){
        mViewPager = findViewById(R.id.id_viewpager);

        mTabMessage = (LinearLayout)findViewById(R.id.id_mTabMessage);
        mTabCheckRecord = (LinearLayout)findViewById(R.id.id_mTabCheckRecord);
        mTabLeaveRest = (LinearLayout)findViewById(R.id.id_mTabLeaveRest);
        mTabMy = (LinearLayout)findViewById(R.id.id_mTabMy);

        mFragments = new ArrayList<Fragment>();
        fm_message = new MessageFragment();
        fm_checkRecord = new CheckRecordFragment();
        fm_leaveRest = new LeaveRestFragment();
        fm_my = new MyFragment();
        mFragments.add(fm_message);
        mFragments.add(fm_checkRecord);
        mFragments.add(fm_leaveRest);
        mFragments.add(fm_my);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        file = new File(Environment.getExternalStorageDirectory()+
                "/Android/data/com.lollipop.access_controldemo/cache","face.txt");
        Log.i("MainActivity","face.txt是否存在"+file);
        handler = new Handler();
        handler.post(runnable);
    }




    /**
     * 线程每5秒上传一次face.txt文件到服务端
     */
    Runnable runnable=new Runnable() {

        public void run() {
            Log.i("MainActivity", "runnable run");
            //uploadFile(file);
            //uploadImg();
            handler.postDelayed(runnable, 5000);
        }

    };

    /**
     * 上传文件file
     * @param file
     */
    private void uploadFile(File file) {
//        10.151.5.76(首选)
        String url = "http://10.151.9.36/dm/public/api/user/uploadImg";

            OkHttpUtils.post(url).params("file",file,"face.txt").execute(new AbsCallback<Object>() {


                @Override
                public Object parseNetworkResponse(Response response) throws Exception {
                    Log.i("akiraResponse",response.toString());
                    return response;
                }

                @Override
                public void onResponse(boolean isFromCache, Object o, Request request, @Nullable Response response) {
                    Log.i("66666666",o.toString());
                    Toast.makeText(getApplicationContext(),"连接成功",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(isFromCache, call, response, e);
                    Toast.makeText(getApplicationContext(),"666"+response+"333"+e,Toast.LENGTH_LONG).show();
                }
            });

    }


    /**
     * 上传文件
     */
    private void uploadImg(){
        String url = "http://10.151.9.36/dm/public/api/user/uploadImg";
        FinalHttp finalHttp = new FinalHttp();
        AjaxParams params = new AjaxParams();
        params.put("regpic","");
        params.put("upload_ygid","12321123");
        finalHttp.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                //Gson gson = new Gson();
                Toast.makeText(getApplicationContext(),"111"+o.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(getApplicationContext(),"111"+strMsg+t,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvents() {
        mTabMessage.setOnClickListener(this);
        mTabCheckRecord.setOnClickListener(this);
        mTabLeaveRest.setOnClickListener(this);
        mTabMy.setOnClickListener(this);
    }
    private void setTab(int i) {
        resetImgs();

        //将图片设置为亮色
        //设置内容区域
        switch (i){
            case 0:
                //ib_message.setImageResource(R.drawable.id_ib_message);

                break;
            case 1:
                //ib_checkRecord.setImageResource(R.drawable.id_ib_checkRecord);
                break;
            case 2:
                //ib_leaveRest.setImageResource(R.drawable.id_ib_leaveRest);
                break;
            case 3:
                //ib_my.setImageResource(R.drawable.id_ib_my);
                break;
        }
    }


    private  void setSelect(int i){
        setTab(i);
        mViewPager.setCurrentItem(i);
    }
    @Override
    public void onClick(View view) {
        resetImgs();
        switch (view.getId()){
            case R.id.id_mTabMessage:
                setSelect(0);
                break;
            case R.id.id_mTabCheckRecord:
                setSelect(1);
                break;
            case R.id.id_mTabLeaveRest:
                setSelect(2);
                break;
            case R.id.id_mTabMy:
                setSelect(3);
                break;
        }
    }

    /**
     * 切换图片于暗色
     * @author:俞泽峰
     */
    private void resetImgs() {
//        ib_message.setImageResource(R.drawable.id_ib_message);
//        ib_checkRecord.setImageResource(R.drawable.id_ib_checkRecord);
//        ib_leaveRest.setImageResource(R.drawable.id_ib_leaveRest);
//        ib_my.setImageResource(R.drawable.id_ib_my);
    }
}
