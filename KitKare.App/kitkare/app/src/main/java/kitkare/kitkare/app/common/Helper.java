package kitkare.kitkare.app.common;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import kitkare.kitkare.R;
import kitkare.kitkare.app.dataModels.UserData;
import kitkare.kitkare.app.views.MainActivity;

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
