package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.custom.listeners.OnDoubleTapListener;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.tasks.GiveFoodTask;

public class GiveFoodFragment extends Fragment {
    static ImageView imageViewCatFood;
    static Button btnGiveFood;

    private Context context;
    private DashboardActivity dashboardActivity;

    public GiveFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        imageViewCatFood = (ImageView) view.findViewById(R.id.imageViewCatFood);
        btnGiveFood = (Button) view.findViewById(R.id.btnGiveFood);

        this.attachEventListeners();

        return view;
    }

    private void loadPageData() {
        GiveFoodTask giveFoodTask = new GiveFoodTask(context);
        giveFoodTask.execute();
    }

    private void attachEventListeners(){
        btnGiveFood.setOnTouchListener(new OnDoubleTapListener(context) {
            @Override
            public void onDoubleTap(MotionEvent e) {
                loadPageData();
            }
        });
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
    }
}
