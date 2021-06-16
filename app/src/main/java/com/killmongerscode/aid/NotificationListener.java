package com.killmongerscode.aid;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NotificationListener extends NotificationListenerService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String packageName = sbn.getPackageName().toLowerCase();
        Notification notification = sbn.getNotification();
        if(notification == null){
            return;

        }

        else {

            Toast.makeText(this, "fucks it", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "fuck this", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap) {
        super.onNotificationRemoved(sbn, rankingMap);
    }

}
