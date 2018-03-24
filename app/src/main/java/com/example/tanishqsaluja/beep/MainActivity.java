package com.example.tanishqsaluja.beep;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*AudioManager am = (AudioManager)getSystemService(this.AUDIO_SERVICE);
        if(am.isMusicActive()){

        }*/
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.cancelAll();
        }
        //notificationManager.notify(123, notification);
        FloatingActionButton fab=findViewById(R.id.fab);
        View dialogView=getLayoutInflater().inflate(R.layout.alert_dialog,null,false);
        final TimePicker timePicker=dialogView.findViewById(R.id.time);

        final AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Schedule your task")
                .setCancelable(false)
                .setView(dialogView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         setAlarm(timePicker);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
    }
    private void setAlarm(TimePicker timePicker){
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent pendingIntent=new Intent(MainActivity.this,MyReceiver.class);
        // pendingIntent.putExtra("notesdb", (Parcelable) ndb);
        Calendar calendar=Calendar.getInstance();
        if(Build.VERSION.SDK_INT>=23) {
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute(),
                    0
            );
        }

        else{
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute(),
                    0
            );
        }

        alarmIntent = PendingIntent.getBroadcast(getBaseContext(),1234,
                pendingIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC,calendar.getTimeInMillis(),alarmIntent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings){
            Intent intent=new Intent(MainActivity.this,MedicineActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
