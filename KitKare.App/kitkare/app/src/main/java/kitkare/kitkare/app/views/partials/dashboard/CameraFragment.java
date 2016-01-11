package kitkare.kitkare.app.views.partials.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.OnSwipeTouchListener;
import kitkare.kitkare.app.views.DashboardActivity;

public class CameraFragment extends Fragment {
    static ImageView imageViewCamera;
    private Context context;
    private DashboardActivity dashboardActivity;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        imageViewCamera = (ImageView) view.findViewById(R.id.imageViewCamera);
        imageViewCamera.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                dashboardActivity.getFragment(new DashboardFragment(), R.animator.fragment_slide_right,  R.animator.fragment_slide_left);
            }

            @Override
            public void onSwipeLeft() {
                dashboardActivity.getFragment(new DashboardFragment());
            }
        });
        return view;
        //return inflater.inflate(R.layout.fragment_camera, container, false);
    }
}
