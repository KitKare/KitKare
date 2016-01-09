package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.services.AccountService;
import okhttp3.MediaType;

public class RegisterTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private AccountService accountService;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public RegisterTask(Context context, AccountService accountService){
        this.mContext = context;
        this.accountService = accountService;
    }

    @Override
    protected String doInBackground(String... params) {
        String email = params[0];
        String password = params[1];
        String confirmPassword = params[2];
        //String test = "{\"email\":\"gosho@pesho.pesho\",\"password\":\"pesho\",\"confirmPassword\":\"pesho\"}";

        try {
            this.accountService.doPostRequest(GlobalConstants.REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
