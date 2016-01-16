package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.tasks.GiveFoodTask;

public class GiveFoodFragment extends Fragment implements View.OnClickListener{
    static ImageView imageViewCatFood;
    static Button btnGiveFood;

    Context context;
    DashboardActivity dashboardActivity;

    public GiveFoodFragment() {
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

        btnGiveFood = (Button) view.findViewById(R.id.btnGiveFood);
        btnGiveFood.setOnClickListener(this);

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private void loadPageData() {
        GiveFoodTask giveFoodTask = new GiveFoodTask(context);
        giveFoodTask.execute();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGiveFood) {
            loadPageData();
        }
    }
}
