package kitkare.kitkare.app.common;

import android.app.ActivityManager;
import android.content.Context;
import android.widget.Toast;

public class Helper {
    public static void makeText(Context context, String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    public static void makeText(Context context, String message, int duration){
        Toast.makeText(
                context,
                message,
                duration).show();
    }
}
