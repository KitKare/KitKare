package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.dataModels.UserData;
import kitkare.kitkare.app.services.AccountService;
import kitkare.kitkare.app.viewModels.LoginViewModel;
import kitkare.kitkare.app.views.DashboardActivity;

public class LoginTask extends AsyncTask<String, Void, String> {
    private Context context;
    private AccountService accountService;
    private LoginViewModel user;

    public LoginTask(Context context, AccountService accountService, LoginViewModel user) {
        this.context = context;
        this.accountService = accountService;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            result = this.accountService.login(GlobalConstants.LOGIN, user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jObject = new JSONObject(result);
            try {
                String token = jObject.getString("access_token");
                String userName = jObject.getString("userName");
                if (token == null || userName == null || token.isEmpty() || userName.isEmpty()) {
                    Helper.makeText(context, "Login failed");
                    return;
                }

                UserData.setToken(token);
                UserData.setUsername(userName);
                Helper.makeText(context, "Hello, " + UserData.getUsername());

                context.startActivity(new Intent(context, DashboardActivity.class));
            } catch (JSONException e) {
                String errorDescription = jObject.getString("error_description");
                Helper.makeText(context, errorDescription);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Helper.makeText(context, "Login failed");
        }
    }
}
