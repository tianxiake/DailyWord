package com.nebulaera.dailyword;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RemoteViews;

/**
 * @author yongjie
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyBtn = (AppCompatButton) findViewById(R.id.btn_notify);
        notifyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        sendNotify();
    }

    private void sendNotify() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        remoteViews.setImageViewResource(R.id.iv_icon, R.mipmap.ic_launcher_round);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(10, pendingIntent);

        Notification build = builder.setSmallIcon(R.mipmap.ic_launcher_round).setCustomContentView(remoteViews).setContentIntent(pendingIntent).build();
        build.flags = Notification.FLAG_ONGOING_EVENT;
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(9, build);
    }
}
