package kitkare.kitkare.app.views.partials.account;

import android.content.Context;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kitkare.kitkare.R;
import kitkare.kitkare.ScheduledService;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.tasks.LoginTask;
import kitkare.kitkare.app.viewModels.LoginViewModel;
import kitkare.kitkare.app.views.MainActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    static EditText email, password;
    static Button btnLogin, btnRegister;
    Context context;
    MainActivity mainActivity;
    //AccountService accountService;
    //Validator validator;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,
                false);

        this.context = container.getContext();
        this.mainActivity = (MainActivity) this.context;
        //this.accountService = new AccountService();
        //this.validator = new Validator(this.context);

        //EditText views
        email = (EditText) view.findViewById(R.id.etLoginEmail);
        password = (EditText) view.findViewById(R.id.etLoginPassword);

        //Button views
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            this.mainActivity.loadRegister();
        } else if (v.getId() == R.id.btnLogin) {
            this.loginUser();
        }
    }

    private void loginUser() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        boolean isUsernameValid = this.mainActivity.validator.validateString(emailText, "Please type email.");
        boolean isPasswordValid = this.mainActivity.validator.validateString(passwordText, "Please type a password.");

        if (!isUsernameValid || !isPasswordValid){
            return;
        }

        LoginViewModel user = new LoginViewModel(emailText, passwordText);

        LoginTask loginTask = new LoginTask(this.context, this.mainActivity.accountService, user);
        loginTask.execute();

        // start scheduled service to send notifications
        context.startService(new Intent(context, ScheduledService.class));
    }
}