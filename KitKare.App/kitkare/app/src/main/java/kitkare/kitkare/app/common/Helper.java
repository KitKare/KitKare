package kitkare.kitkare.app.common;

import android.content.Context;
import android.widget.Toast;

public class Helper {
    public static void makeText(Context context, String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
