package com.example.firebase6;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class firebaseMszService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("tag","new tocker is "+ token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
            if(message.getNotification()!=null) {
                call(
                        message.getNotification().getTitle(),
                        message.getNotification().getBody()
                );
            }
    }
    public void call(String title, String text) {
        Drawable newDrawable = ResourcesCompat.getDrawable(getResources(),R.drawable.img,null);
        BitmapDrawable newDrawable2 = (BitmapDrawable)newDrawable;
        Bitmap newDrawable3 = newDrawable2.getBitmap();

        Intent intent = new Intent(this,intentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title",title);
        intent.putExtra("text",text);

        PendingIntent penIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationManager notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder newNotification;
//        String str = "https://media.istockphoto.com/id/1388623445/photo/bear-skin-state-trail-bridge.jpg?s=1024x1024&w=is&k=20&c=Tm_8oju7-Iea188Og8r00WBV58TSVhfEKb7vjtErpKM=";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        newNotification = new NotificationCompat.Builder(this)
                .setLargeIcon(newDrawable3)
                .setSmallIcon(R.drawable.img)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(penIntent)
                .setChannelId("CHANNEL_ID")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationChannel firstNotificationChannel = null;

            firstNotificationChannel = new NotificationChannel("CHANNEL_ID", "NEW CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            notiManager.createNotificationChannel(firstNotificationChannel);
        }
        else{
            newNotification = new NotificationCompat.Builder(this)
                    .setLargeIcon(newDrawable3)
                    .setSmallIcon(R.drawable.img)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(penIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }
//        newNotification.setContentIntent(penIntent);
        notiManager.notify(1,newNotification.build());
    }
}