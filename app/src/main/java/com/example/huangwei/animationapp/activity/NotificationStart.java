package com.example.huangwei.animationapp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import com.example.huangwei.animationapp.R;

/**
 * @author huangwei E-mail: huangwei@tigerbrokers.com
 * @version 创建时间： 2017/08/29/11:49
 */

public class NotificationStart extends AppCompatActivity implements View.OnClickListener {
  NotificationManager notificationManager;
  NotificationCompat.Builder builder;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification_start);
    findViewById(R.id.btn_notification_start).setOnClickListener(this);
    findViewById(R.id.btn_notification_expand).setOnClickListener(this);
    initNotification();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_notification_start:
        clickStart();
        break;
      case R.id.btn_notification_expand:
        clickExpand();
        break;
      default:
        break;
    }
  }

  private void clickStart() {
    notificationManager.notify(0,builder.build());
  }

  private void clickExpand(){
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setClass(this,NotificationShow.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
    //builder.setContent(new RemoteViews(getPackageName(),R.layout.layout_collapse_notification));
    //builder.setContentIntent(pendingIntent);
    //builder.setCustomBigContentView(new RemoteViews(getPackageName(),R.layout.layout_expand_notification));
    //builder.setCustomHeadsUpContentView(new RemoteViews(getPackageName(),R.layout.layout_collapse_notification));
    builder.setFullScreenIntent(pendingIntent,false);
    notificationManager.notify(1,builder.build());
  }

  private void initNotification(){
    Intent resultIntent = new Intent(this, NotificationShow.class);
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(NotificationShow.class);
    stackBuilder.addNextIntent(resultIntent);
    //PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_CANCEL_CURRENT);
    builder = new NotificationCompat.Builder(this);
    builder.setContentTitle(getString(R.string.notification_title))
           .setContentText(getString(R.string.notification_content))
           .setSmallIcon(R.drawable.shape_oval_round)
           .setAutoCancel(true)
           .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon_security));
    builder.setContentIntent(pendingIntent);
    builder.setPriority(NotificationCompat.PRIORITY_HIGH);
    //builder.setFullScreenIntent(pendingIntent,true);
    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
  }

}
