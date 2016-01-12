package kitkare.kitkare.app.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import kitkare.kitkare.R;
import kitkare.kitkare.app.views.DashboardActivity;

// http://stackoverflow.com/questions/6391870/how-exactly-to-use-notification-builder
public class Notifier {
    private final static int PI_REQUEST_CODE = 123456789;
    private final static int NOTIFICATION_ID = 987654321;

    public static void pushNotification(Context context, String title, String content){
        Intent notificationIntent = new Intent(context, DashboardActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                PI_REQUEST_CODE, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.catnotif)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.catnotif))
                .setTicker(res.getString(R.string.app_notification))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(content);

        //TODO resolve older version
        Notification n = builder.build();

        nm.notify(NOTIFICATION_ID, n);
    }
}
