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
import android.widget.Toast;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.custom.listeners.OnDoubleTapListener;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.data.remote.models.UserData;
import kitkare.kitkare.app.tasks.device.CheckIfLightsAreOnTask;
import kitkare.kitkare.app.tasks.device.ToggleLightsTask;
import kitkare.kitkare.app.tasks.device.GiveFoodTask;
import kitkare.kitkare.app.tasks.device.GiveWaterTask;

public class DeviceFragment extends Fragment implements IUpdatePageData, View.OnClickListener {
    static ImageView imageViewCatFood;
    static Button btnGiveFood, btnGiveWater, btnToggleLights, btnLightsOn, btnLightsOff, btnFeedingHistory;

    private Context context;
    private DashboardActivity dashboardActivity;
    private boolean areLightsOn;

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
        btnLightsOn = (Button) view.findViewById(R.id.btnToggleLightsOn);
        btnLightsOff = (Button) view.findViewById(R.id.btnToggleLightsOff);
        btnFeedingHistory = (Button) view.findViewById(R.id.btnFeedingHistory);

        this.checkIfLightsAreOn();
        this.setVisibility();
        this.attachEventListeners();
        Helper.makeText(context, "Double tap to activate a device feature.", Toast.LENGTH_LONG);

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

    private void checkIfLightsAreOn() {
        CheckIfLightsAreOnTask checkIfLightsAreOnTask = new CheckIfLightsAreOnTask(context, this);
        checkIfLightsAreOnTask.execute();
    }

    private void attachEventListeners() {
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
                dashboardActivity.getFragment(new DashboardFragment(), R.animator.fragment_slide_right, R.animator.fragment_slide_left);
            }

            @Override
            public void onSwipeLeft() {
                dashboardActivity.getFragment(new DashboardFragment());
            }
        });

        btnFeedingHistory.setOnClickListener(this);
    }

    @Override
    public void updatePageData(ArrayList list) {
        if (list.size() > 0) {
            areLightsOn = list.get(0).equals("true");
        }

        setVisibility();
    }

    private void setVisibility() {
        if (areLightsOn) {
            btnToggleLights = btnLightsOn;
            btnLightsOff.setVisibility(View.GONE);
            btnLightsOn.setVisibility(View.VISIBLE);
        } else {
            btnToggleLights = btnLightsOff;
            btnLightsOn.setVisibility(View.GONE);
            btnLightsOff.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFeedingHistory) {
            this.dashboardActivity.getFragment(new FeedingHistoryFragment());
        }
    }
}
