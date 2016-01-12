package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.activities.DashboardActivity;

public class FeedFragment extends Fragment {
    static ImageView imageViewCatFood;
    Context context;
    DashboardActivity dashboardActivity;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        imageViewCatFood = (ImageView) view.findViewById(R.id.imageViewCatFood);
        imageViewCatFood.setOnTouchListener(new OnSwipeTouchListener(context) {
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
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
