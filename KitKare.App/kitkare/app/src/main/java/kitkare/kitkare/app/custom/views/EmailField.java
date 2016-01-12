package kitkare.kitkare.app.custom.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import kitkare.kitkare.R;

public class EmailField extends LinearLayout {

    public EmailField(Context context, AttributeSet attrs) {
        super(context, attrs);

        //readAttributes(context, attrs);
        init(context);
    }

    public EmailField(Context context) {
        super(context);

        init(context);
    }

    private void init(Context c) {

        final LayoutInflater inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.email_field, this);

    }
}