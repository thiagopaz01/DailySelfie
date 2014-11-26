package br.com.thiagopaz.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    private static final int MY_NOTIFICATION_ID = 1;
    private static final String TAG = "AlarmNotificationReceiver";
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private final CharSequence tickerText = "Time for another selfie";
    private final CharSequence contentTitle = "Daily Selfie";
    private final CharSequence contentText = "Time for another selfie";

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationIntent = new Intent(context, MyActivity.class);

        /*mContentIntent = PendingIntent.getActivity(context, 0,
                mNotificationIntent, PendingIntent.FLAG_ONE_SHOT);*/

        mContentIntent = PendingIntent.getActivity(context, 0,
                mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        Notification.Builder notificationBuilder = new Notification.Builder(
                context).setTicker(tickerText)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true).setContentTitle(contentTitle)
                .setContentText(contentText).setContentIntent(mContentIntent);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(MY_NOTIFICATION_ID,notificationBuilder.build());
    }
}
