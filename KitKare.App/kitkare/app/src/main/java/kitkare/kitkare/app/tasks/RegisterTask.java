package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.services.AccountService;
import kitkare.kitkare.app.viewModels.LoginViewModel;
import kitkare.kitkare.app.viewModels.RegisterUserViewModel;
import kitkare.kitkare.app.views.DashboardActivity;

public class RegisterTask extends AsyncTask<String, Void, String> {
    private Context context;
    private AccountService accountService;
    private RegisterUserViewModel user;

    public RegisterTask(Context context, AccountService accountService, RegisterUserViewModel user) {
        this.context = context;
        this.accountService = accountService;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            result = this.accountService.register(GlobalConstants.REGISTER, user);
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

        if (result.isEmpty()) {
            Helper.makeText(context, "Registration successful");

            this.loginUser();
            context.startActivity(new Intent(context, DashboardActivity.class));
        } else {
            try {
                JSONObject jObject = new JSONObject(result);
                String errorMessage = jObject.getString("Message");
                Helper.makeText(context, errorMessage);
            } catch (JSONException e) {
                e.printStackTrace();
                Helper.makeText(context, "Incorrect registration data");
            }
        }
    }

    private void loginUser(){
        LoginViewModel user = new LoginViewModel(this.user.email, this.user.password);

        LoginTask loginTask = new LoginTask(this.context, this.accountService, user);
        loginTask.execute();
    }
}
