package com.example.tanishqsaluja.beep.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.telecom.Call;
import android.util.Log;

import com.example.tanishqsaluja.beep.MainActivity;
import com.example.tanishqsaluja.beep.R;

import java.util.Locale;
import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by tanishqsaluja on 24/3/18.
 */

public class MyReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TEST","inside receive method");
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(context, (int) System.currentTimeMillis(),i,0);
        Notification notification = new NotificationCompat.Builder(context, "test")
                .setContentTitle("I NEED HELP")
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLights(Color.RED,3000,3000)
                .setCategory(Notification.CATEGORY_CALL)
                .setDefaults(Notification.FLAG_INSISTENT)
                .setOngoing(true)
                .setAutoCancel(false)
                .build();
        int number=new Random().nextInt();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(number, notification);
    }
}

