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

    public static void checkIfInternetConnection(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Helper.makeText(context, "You do not have connection!", Toast.LENGTH_LONG);
        }
    }

    public static void prepareOptionsMenu(Menu menu, Context context)
    {
        MenuItem logout = menu.findItem(R.id.action_logout);
        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem register = menu.findItem(R.id.action_register);

        if(SaveSharedPreference.getUserName(context).length() != 0)
        {
            logout.setVisible(true);
            login.setVisible(false);
            register.setVisible(false);
        }
        else
        {
            logout.setVisible(false);
            login.setVisible(true);
            register.setVisible(true);
        }
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
