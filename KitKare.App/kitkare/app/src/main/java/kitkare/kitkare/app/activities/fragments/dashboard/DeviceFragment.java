package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.custom.listeners.OnDoubleTapListener;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.tasks.device.ToggleLightsTask;
import kitkare.kitkare.app.tasks.device.GiveFoodTask;
import kitkare.kitkare.app.tasks.device.GiveWaterTask;

public class DeviceFragment extends Fragment {
    static ImageView imageViewCatFood;
    static Button btnGiveFood, btnGiveWater, btnToggleLights;

    private Context context;
    private DashboardActivity dashboardActivity;

    public DeviceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        imageViewCatFood = (ImageView) view.findViewById(R.id.imageViewCatFood);
        btnGiveFood = (Button) view.findViewById(R.id.btnGiveFood);
        btnGiveWater = (Button) view.findViewById(R.id.btnGiveWater);
        btnToggleLights = (Button) view.findViewById(R.id.btnToggleLights);

        this.attachEventListeners();

        return view;
    }

    private void giveFood() {
        GiveFoodTask giveFoodTask = new GiveFoodTask(context);
        giveFoodTask.execute();
    }

    private void giveWater() {
        GiveWaterTask giveWaterTask = new GiveWaterTask(context);
        giveWaterTask.execute();
    }

    private void toggleLights() {
        ToggleLightsTask toggleLightsTask = new ToggleLightsTask(context);
        toggleLightsTask.execute();
    }

    private void attachEventListeners(){
        btnGiveFood.setOnTouchListener(new OnDoubleTapListener(context) {
            @Override
            public void onDoubleTap(MotionEvent e) {
                giveFood();
            }
        });

        btnGiveWater.setOnTouchListener(new OnDoubleTapListener(context) {
            @Override
            public void onDoubleTap(MotionEvent e) {
                giveWater();
            }
        });

        btnToggleLights.setOnTouchListener(new OnDoubleTapListener(context) {
            @Override
            public void onDoubleTap(MotionEvent e) {
                toggleLights();
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
