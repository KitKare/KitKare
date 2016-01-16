package kitkare.kitkare.app.common;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class Helper {
    private static SimpleDateFormat formatter;
    private static SimpleDateFormat noMsFormatter;
    private static SimpleDateFormat shortFormatter;
    private static SimpleDateFormat dayDateFormatter;

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

    public static SimpleDateFormat getDayDateFormatter() {
        if (shortFormatter == null) {
            shortFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            shortFormatter.setLenient(false);
        }

        return shortFormatter;
    }

    public static SimpleDateFormat getShortDateFormatter() {
        if (dayDateFormatter == null) {
            dayDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            dayDateFormatter.setLenient(false);
        }

        return dayDateFormatter;
    }
}
