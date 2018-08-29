package com.lollipop.access_controldemo.ui;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lollipop.access_controldemo.R;

public class NotificationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        assert nm != null;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        nm.cancel(bundle.getInt("notificationID"));
    }
}
