package kitkare.kitkare;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.Notifier;
import kitkare.kitkare.app.common.SaveSharedPreference;

// https://xjaphx.wordpress.com/2012/07/07/create-a-service-that-does-a-schedule-task/
public class ScheduledService extends Service {
    // constant
    public static final long NOTIFY_INTERVAL = 1000 * 60 * 60 * 24; // 24 hours
    //private static String PREF_SET_NOTIF_SCHEDULER = "KitKareNotificationScheduler";

    private Context context = this;
    // run on another Thread to avoid crash
    private Handler handler = new Handler();
    // timer handling
    private Timer timer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // cancel if already existed
        if (timer != null) {
            timer.cancel();
        } else {
            // recreate new
            timer = new Timer();
        }
        // schedule task
        timer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    @Override
    public void onDestroy() {
        //SaveSharedPreference.removeKeyValuePair(context, PREF_SET_NOTIF_SCHEDULER);
        Helper.makeText(this, "KitKare notification service destroyed");
        super.onDestroy();
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            handler.post(new Runnable() {

                @Override
                public void run() {
                    // send notification
                    // if (SaveSharedPreference.getKeyValuePair(context, PREF_SET_NOTIF_SCHEDULER) == "true") {
                    String title = context.getResources().getString(R.string.reminder_notification_title);
                    String text = context.getResources().getString(R.string.reminder_notification_text);
                    Notifier.pushNotification(context, title, text);
                    // }

                    // SaveSharedPreference.setKeyValuePair(context, PREF_SET_NOTIF_SCHEDULER, "true");
                }

            });
        }

//        private String getDateTime() {
//            // get date time in custom format
//            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
//            return sdf.format(new Date());
//        }
    }
}