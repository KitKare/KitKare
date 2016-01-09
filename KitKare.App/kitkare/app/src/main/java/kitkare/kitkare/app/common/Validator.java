package kitkare.kitkare.app.common;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Context context;

    public Validator(Context context) {
        this.context = context;
    }

    public boolean validateEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);

        boolean isMatch = matcher.find();
        if (isMatch == false) {
            Helper.makeText(this.context, "Please enter a valid email.");
        }

        return isMatch;
    }

    public boolean validatePassword(String password, String confirmPassword) {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Helper.makeText(this.context, "Please type a password.");
            return false;
        } else if (password.compareTo(confirmPassword) != 0) {
            Helper.makeText(this.context, "Password does not match the confirm password.");
            return false;
        }

        return true;
    }

    public boolean validateString(String str, String message) {
        if (str.isEmpty()) {
            Helper.makeText(this.context, message);
            return false;
        }

        return true;
    }
}
