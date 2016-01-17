package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.tasks.tips.AddToFavoritesTask;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class SingleCatCareTipFragment extends Fragment implements View.OnClickListener {
    static TextView tvCatCareTipTitle, tvCatCareTipContent, tvCatCareTipCreatedOn;
    static Button btnAddToFavorites;
    Context context;
    DashboardActivity dashboardActivity;
    CatCareTipViewModel catCareTip;

    public SingleCatCareTipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_cat_care_tip, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;
        Bundle bundle = this.getArguments();
        catCareTip = bundle.getParcelable(CatCareTipsFragment.SINGLE_CAT_CARE_TIP_KEY);

        tvCatCareTipTitle = (TextView) view.findViewById(R.id.tvCatCareTipTitle);
        tvCatCareTipContent = (TextView) view.findViewById(R.id.tvCatCareTipContent);
        tvCatCareTipCreatedOn = (TextView) view.findViewById(R.id.tvCatCareTipCreatedOn);

        tvCatCareTipTitle.setText(catCareTip.getTitle());
        tvCatCareTipContent.setText(catCareTip.getContent());
        tvCatCareTipCreatedOn.setText(Helper.getShortDateFormatter().format(catCareTip.getCreatedon()));

        btnAddToFavorites = (Button) view.findViewById(R.id.btnAddToFavorites);
        btnAddToFavorites.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddToFavorites) {
            addTipToFavorites();
        }
    }

    private void addTipToFavorites() {
        AddToFavoritesTask addToFavoritesTask = new AddToFavoritesTask(context, catCareTip);
        addToFavoritesTask.execute();
    }
}
