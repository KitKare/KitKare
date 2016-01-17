package kitkare.kitkare.app.activities.fragments.account;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.custom.views.EmailField;
import kitkare.kitkare.app.custom.views.PasswordField;
import kitkare.kitkare.app.tasks.account.RegisterTask;
import kitkare.kitkare.app.viewModels.RegisterUserViewModel;
import kitkare.kitkare.app.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    static EditText email, password, confirmPassword;
    static Button btnLogin, btnRegister;
    static ProgressBar progressBar;
    Context context;
    MainActivity mainActivity;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container,
                false);

        this.context = container.getContext();
        this.mainActivity = (MainActivity) this.context;
        //this.accountService = new AccountService();
        //this.validator = new Validator(this.context);

        EmailField emailField = (EmailField) view.findViewById(R.id.efRegisterEmail);
        TextView tvRegisterEmail = (TextView) emailField.findViewById(R.id.efTextView);
        tvRegisterEmail.setText(getResources().getString(R.string.tvMainLabelEmail));

        PasswordField passwordField = (PasswordField) view.findViewById(R.id.pfRegisterPassword);
        TextView tvRegisterPassword = (TextView) passwordField.findViewById(R.id.pfTextView);
        tvRegisterPassword.setText(getResources().getString(R.string.tvMainLabelPassword));

        PasswordField confirmPasswordField = (PasswordField) view.findViewById(R.id.pfRegisterConfirmPassword);
        TextView tvRegisterConfirmPassword = (TextView) confirmPasswordField.findViewById(R.id.pfTextView);
        tvRegisterConfirmPassword.setText(getResources().getString(R.string.tvMainLabelConfirmPassword));

        //EditText views
        email = (EditText) emailField.findViewById(R.id.efEditText);
        password = (EditText) passwordField.findViewById(R.id.pfEditText);
        confirmPassword = (EditText) confirmPasswordField.findViewById(R.id.pfEditText);

        //Button views
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarRegister);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            this.mainActivity.loadLogin();
        } else if (v.getId() == R.id.btnRegister) {
            progressBar.setVisibility(View.VISIBLE);
            this.registerUser();
            progressBar.setVisibility(View.GONE);
        }
    }

    private void registerUser() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirmPassword.getText().toString();

        boolean isValidaEmail = this.mainActivity.validator.validateEmail(emailText);
        boolean isPasswordValid = this.mainActivity.validator.validatePassword(passwordText, confirmPasswordText);

        if (!(isPasswordValid && isValidaEmail)){
            return;
        }

        RegisterUserViewModel user = new RegisterUserViewModel(emailText, passwordText, confirmPasswordText);

        RegisterTask registerTask = new RegisterTask(this.context, this.mainActivity.accountService, user);
        registerTask.execute();
    }
}
