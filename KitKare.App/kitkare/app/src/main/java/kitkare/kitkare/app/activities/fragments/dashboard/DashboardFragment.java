package kitkare.kitkare.app.activities.fragments.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.CameraActivity;
import kitkare.kitkare.app.activities.DashboardActivity;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    static Button btnFeed, btnCamera, btnVet, btnTips;
    private Context context;
    private DashboardActivity dashboardActivity;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        btnFeed = (Button) view.findViewById(R.id.btnDevice);
        btnCamera = (Button) view.findViewById(R.id.btnCamera);
        btnVet = (Button) view.findViewById(R.id.btnVet);
        btnTips = (Button) view.findViewById(R.id.btnTips);

        this.attachEvents();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDevice) {
            this.dashboardActivity.getFragment(new DeviceFragment());
        } else if (v.getId() == R.id.btnCamera) {
            context.startActivity(new Intent(context, CameraActivity.class));
        } else if (v.getId() == R.id.btnVet) {
            this.dashboardActivity.getFragment(new VetFragment());
        } else if (v.getId() == R.id.btnTips){
            this.dashboardActivity.getFragment(new CatCareTipsFragment());
        }
    }

    private void attachEvents() {
        btnFeed.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnVet.setOnClickListener(this);
        btnTips.setOnClickListener(this);
    }
}
