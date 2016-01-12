package kitkare.kitkare.app.custom.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import kitkare.kitkare.R;

public class PasswordField extends LinearLayout {

    public PasswordField(Context context, AttributeSet attrs) {
        super(context, attrs);

        //readAttributes(context, attrs);
        init(context);
    }

    public PasswordField(Context context) {
        super(context);

        init(context);
    }

    private void init(Context c) {

        final LayoutInflater inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.password_field, this);

    }
}