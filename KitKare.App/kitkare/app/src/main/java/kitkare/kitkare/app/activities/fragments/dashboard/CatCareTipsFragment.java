package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.adapters.CatCareTipsAdapter;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.tasks.tips.GetAllCatCareTipsTask;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class CatCareTipsFragment extends Fragment implements IUpdatePageData, View.OnClickListener {
    public static final String SINGLE_CAT_CARE_TIP_KEY = "singleCatCareTip";
    private ArrayList<CatCareTipViewModel> catCareTips;
    private GridView gvCatCareTips;
    static ImageView imageViewTips;
    static Button favoritesButton;

    Context context;
    DashboardActivity dashboardActivity;

    public CatCareTipsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_care_tips, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;
        this.gvCatCareTips = (GridView) view.findViewById(R.id.gvCatCareTips);

        imageViewTips = (ImageView) view.findViewById(R.id.imageViewTips);
        favoritesButton = (Button) view.findViewById(R.id.btnFavorites);

        this.attachEventListeners();
        this.loadPageData();

        return view;
    }

    @Override
    public void updatePageData(ArrayList list) {
        this.catCareTips = list;
        this.gvCatCareTips.setAdapter(new CatCareTipsAdapter(this.context, this.catCareTips));
    }

    private void loadPageData() {
        GetAllCatCareTipsTask getAllCatCareTipsTask = new GetAllCatCareTipsTask(context, this);
        getAllCatCareTipsTask.execute();
    }

    private void attachEventListeners(){
        // http://stackoverflow.com/questions/14675695/how-to-use-onclicklistener-for-grid-view
        gvCatCareTips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // http://stackoverflow.com/questions/7149802/how-to-transfer-some-data-to-another-fragment
                SingleCatCareTipFragment fragment = new SingleCatCareTipFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(SINGLE_CAT_CARE_TIP_KEY, catCareTips.get(position));
                fragment.setArguments(bundle);
                dashboardActivity.getFragment(fragment);
            }
        });

        imageViewTips.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                dashboardActivity.getFragment(new DashboardFragment(), R.animator.fragment_slide_right, R.animator.fragment_slide_left);
            }

            @Override
            public void onSwipeLeft() {
                dashboardActivity.getFragment(new DashboardFragment());
            }
        });

        favoritesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFavorites) {
            dashboardActivity.getFragment(new FavoriteCatCareTipsFragment());
        }
    }
}
