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
            Toast.makeText(
                    this.context,
                    "Please enter a valid email.",
                    Toast.LENGTH_SHORT).show();
        }

        return isMatch;
    }

    public boolean validatePassword(String password, String confirmPassword) {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(
                    this.context,
                    "Please type a password.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.compareTo(confirmPassword) != 0) {
            Toast.makeText(
                    this.context,
                    "Password does not match the confirm password.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
