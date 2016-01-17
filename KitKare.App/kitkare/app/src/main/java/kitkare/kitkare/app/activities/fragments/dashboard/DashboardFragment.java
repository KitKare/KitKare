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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
