package kitkare.kitkare.app.activities.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.AboutUsActivity;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.custom.listeners.PinchToZoomGestureListener;
import kitkare.kitkare.app.data.remote.services.CatCareTipsService;

public class AboutUsFragment extends Fragment {
    Context context;
    AboutUsActivity aboutUsActivity;

    public AboutUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container,
                false);
        this.context = container.getContext();
        this.aboutUsActivity = (AboutUsActivity) this.context;

        return view;
    }
}
