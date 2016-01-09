package kitkare.kitkare.app.tasks;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.services.AccountService;
import kitkare.kitkare.app.viewModels.RegisterUserViewModel;
import okhttp3.MediaType;

public class RegisterTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private AccountService accountService;
    private RegisterUserViewModel user;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public RegisterTask(Context context, AccountService accountService, RegisterUserViewModel user) {
        this.mContext = context;
        this.accountService = accountService;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            this.accountService.register(GlobalConstants.REGISTER, user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
