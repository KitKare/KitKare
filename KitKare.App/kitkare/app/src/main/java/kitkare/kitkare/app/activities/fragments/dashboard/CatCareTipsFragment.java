package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.adapters.CatCareTipsAdapter;
import kitkare.kitkare.app.data.remote.services.CatCareTipsService;
import kitkare.kitkare.app.tasks.GetAllCatCareTipsTask;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CatCareTipsFragment extends Fragment {
    private ArrayList<CatCareTipViewModel> catCareTips;
    private GridView gvCatCareTips;
    CatCareTipsService service;

    Context context;
    DashboardActivity dashboardActivity;

    public CatCareTipsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_care_tips, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;
        this.service = new CatCareTipsService();
        this.reloadPageInfo();
        this.gvCatCareTips = (GridView) view.findViewById(R.id.gvCatCareTips);

        return view;
    }

    private void reloadPageInfo() {
        GetAllCatCareTipsTask getAllCatCareTipsTask = new GetAllCatCareTipsTask(this, service);
        getAllCatCareTipsTask.execute();
    }

    public void updatePageInfo(ArrayList<CatCareTipViewModel> catCareTips) {
        this.catCareTips = catCareTips;
        this.gvCatCareTips.setAdapter(new CatCareTipsAdapter(this.context, this.catCareTips));
    }
}
