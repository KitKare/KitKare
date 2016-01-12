package kitkare.kitkare.app.common;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import kitkare.kitkare.R;
import kitkare.kitkare.app.data.dataModels.UserData;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.activities.MainActivity;

public class MenuPopulator {
    public static void prepareOptionsMenu(Menu menu, Context context)
    {
        MenuItem logout = menu.findItem(R.id.action_logout);
        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem register = menu.findItem(R.id.action_register);
        MenuItem home = menu.findItem(R.id.action_home);
        MenuItem dashboard = menu.findItem(R.id.action_dashboard);

        if(SaveSharedPreference.getUserName(context).length() != 0)
        {
            logout.setVisible(true);
            login.setVisible(false);
            register.setVisible(false);
            dashboard.setVisible(true);
            home.setVisible(false);
        }
        else
        {
            logout.setVisible(false);
            login.setVisible(true);
            register.setVisible(true);
            dashboard.setVisible(false);
            home.setVisible(true);
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
            MenuPopulator.logout(context);
            return true;
        }

        if (id == R.id.action_home) {
            context.startActivity(new Intent(context, MainActivity.class));
            return true;
        }

        if (id == R.id.action_dashboard) {
            context.startActivity(new Intent(context, DashboardActivity.class));
            return true;
        }

        if (id == R.id.action_notify) {
            String title = context.getResources().getString(R.string.reminder_notification_title);
            String text  = context.getResources().getString(R.string.reminder_notification_text);
            Notifier.pushNotification(context, title, text);
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
