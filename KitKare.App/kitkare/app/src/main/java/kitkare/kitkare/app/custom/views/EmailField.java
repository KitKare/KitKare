package kitkare.kitkare.app.custom.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import kitkare.kitkare.R;

// http://stackoverflow.com/questions/13914970/why-edittext-in-a-custom-compound-view-is-re-using-the-text-entered-in-another-c
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