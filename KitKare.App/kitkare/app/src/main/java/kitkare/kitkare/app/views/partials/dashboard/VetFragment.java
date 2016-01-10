package kitkare.kitkare.app.views.partials.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kitkare.kitkare.R;
import kitkare.kitkare.app.views.DashboardActivity;

public class VetFragment extends Fragment {
    Context context;
    DashboardActivity dashboardActivity;

    public VetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vet, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
