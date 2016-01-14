package kitkare.kitkare.app.common;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class Helper {
    private static SimpleDateFormat formatter;
    private static SimpleDateFormat noMsFormatter;

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

    public static SimpleDateFormat getDateFormatter() {
        if (formatter == null) {
            formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
            formatter.setLenient(false);
        }

        return formatter;
    }

    public static SimpleDateFormat getNoMsDateFormatter() {
        if (noMsFormatter == null) {
            noMsFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            noMsFormatter.setLenient(false);
        }

        return noMsFormatter;
    }
}
