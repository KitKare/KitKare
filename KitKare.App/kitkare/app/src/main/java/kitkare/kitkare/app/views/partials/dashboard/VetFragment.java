package kitkare.kitkare.app.views.partials.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.OnSwipeTouchListener;
import kitkare.kitkare.app.views.DashboardActivity;

public class VetFragment extends Fragment {
    static ImageView imageViewVet;
    static Button callVet;
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

        callVet = (Button) view.findViewById(R.id.btnCallVet);
        imageViewVet = (ImageView) view.findViewById(R.id.imageViewVet);

        imageViewVet.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                dashboardActivity.getFragment(new DashboardFragment(), R.animator.fragment_slide_right,  R.animator.fragment_slide_left);
            }

            @Override
            public void onSwipeLeft() {
                dashboardActivity.getFragment(new DashboardFragment());
            }
        });

        // TODO - get contacts from phone book and make actual call
        callVet.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Helper.makeText(context, "Calling...");
                return true;
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
