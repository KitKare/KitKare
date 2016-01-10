package kitkare.kitkare.app.views.partials.account;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kitkare.kitkare.R;
import kitkare.kitkare.app.tasks.RegisterTask;
import kitkare.kitkare.app.viewModels.RegisterUserViewModel;
import kitkare.kitkare.app.views.MainActivity;

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
    Context context;
    MainActivity mainActivity;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container,
                false);

        this.context = container.getContext();
        this.mainActivity = (MainActivity) this.context;
        //this.accountService = new AccountService();
        //this.validator = new Validator(this.context);

        //EditText views
        email = (EditText) rootView.findViewById(R.id.etLoginEmail);
        password = (EditText) rootView.findViewById(R.id.etLoginPassword);
        confirmPassword = (EditText) rootView.findViewById(R.id.etLoginConfirmPassword);

        //Button views
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            this.mainActivity.loadLogin();
        } else if (v.getId() == R.id.btnRegister) {
            this.registerUser();
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
