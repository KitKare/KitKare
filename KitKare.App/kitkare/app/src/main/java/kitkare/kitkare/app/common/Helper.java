package kitkare.kitkare.app.common;

import android.content.Context;
import android.content.Intent;
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

    public static boolean setMenuOptions(MenuItem item, Context context){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_logout) {
            Helper.logout(context);
            return true;
        }

        return false;
    }

    private static void logout(Context context){
        SaveSharedPreference.removeUserName(context);
        UserData.setToken("");
        UserData.setUsername("");
        Helper.makeText(context, "Log out successful!");
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
