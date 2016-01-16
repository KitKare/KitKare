package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.adapters.CatCareTipsAdapter;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.data.interfaces.IUpdatePageInfo;
import kitkare.kitkare.app.data.local.services.CatCareTipsService;
import kitkare.kitkare.app.tasks.GetAllCatCareTipsTask;
import kitkare.kitkare.app.tasks.GetFavoriteCatCareTips;
import kitkare.kitkare.app.viewModels.CatCareTipViewModel;

public class FavoriteCatCareTipsFragment extends Fragment implements IUpdatePageInfo {
    public static final String SINGLE_CAT_CARE_TIP_KEY = "singleCatCareTip";
    private ArrayList<CatCareTipViewModel> catCareTips;
    private GridView gvCatCareTips;
    static ImageView imageViewTips;
    Context context;
    DashboardActivity dashboardActivity;


    public FavoriteCatCareTipsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_cat_care_tips, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;
        this.gvCatCareTips = (GridView) view.findViewById(R.id.gvFavoriteCatCareTips);

        imageViewTips = (ImageView) view.findViewById(R.id.imageViewTips1);

        this.attachEventListeners();
        this.loadPageData();

        return view;
    }

    public void getFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left, R.animator.fragment_slide_right);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }

    @Override
    public void updatePageData(ArrayList list) {
        this.catCareTips = list;
        this.gvCatCareTips.setAdapter(new CatCareTipsAdapter(this.context, this.catCareTips));
    }

    private void loadPageData() {
        GetFavoriteCatCareTips getFavoriteCatCareTips = new GetFavoriteCatCareTips(context, this);
        getFavoriteCatCareTips.execute();
    }

    private void attachEventListeners() {
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
                getFragment(fragment);
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
    }
}
