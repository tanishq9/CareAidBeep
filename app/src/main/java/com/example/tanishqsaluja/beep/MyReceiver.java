package com.example.tanishqsaluja.beep;

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
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by tanishqsaluja on 24/3/18.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
   /*     Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // v.vibrate(50000);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(5000,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            v.vibrate(5000);
        }

*/      Log.e("TEST","inside receive method");
      //  Intent callIntent = new Intent(Intent.ACTION_CALL);
      //  callIntent.setData(Uri.parse("tel: 9811198045"));
        Intent i = new Intent(context, MainActivity.class);
     //   Uri defSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //  Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent= PendingIntent.getActivity(context, (int) System.currentTimeMillis(),i,0);
        Notification notification = new NotificationCompat.Builder(context, "test")
                .setContentTitle("Time for the task: "+intent.getStringExtra("title"))
                //.setDefaults(Notification.DEFAULT_VIBRATE)
                //.setPriority(Notification.PRIORITY_MAX)
                .setVibrate(new long[] { 0, 5000,1000,5000,1000 })
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLights(Color.RED,3000,3000)
                //.setSound(defSound)
                .setOngoing(true)
                .setAutoCancel(false)
                .build();
       notification.sound = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.ringme);

/*
        MediaPlayer player = MediaPlayer.create(context,R.raw.ring);
        player.setLooping(true);
        player.start();
*/
//        if(player.isPlaying()||player.isLooping()){
//            player.stop();
//        }

        notification.defaults|= Notification.DEFAULT_LIGHTS;
        notification.defaults|= Notification.DEFAULT_VIBRATE;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(123, notification);
    }
}

