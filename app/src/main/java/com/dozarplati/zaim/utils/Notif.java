package com.dozarplati.zaim.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.dozarplati.zaim.R;

import java.util.Date;


public class Notif {
    public static void notification(String message, Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = createID();
        notificationId = 1;
        String channelId = "channel-id";
        String channelName = "Channel Name";
        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(message);



        notificationManager.notify(notificationId, mBuilder.build());
    }

    public static int createID() {
        Date now = new Date();
        return (int) now.getTime();
    }
}
