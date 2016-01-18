package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.DashboardActivity;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.viewModels.VetHistoryViewModel;

public class VetHistoryFragment extends Fragment {
    static TextView vetLastCallDateTextView, vetNameTextView, tvNoVetHistoryYet;
    //VetHistoryViewModel model;
    Context context;
    DashboardActivity dashboardActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vet_history, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        //TODO
        //this.model = new VetHistoryViewModel();

        vetLastCallDateTextView = (TextView) view.findViewById(R.id.textViewVetHistoryLastCallDate);
        vetNameTextView = (TextView) view.findViewById(R.id.textViewVetHistoryVetName);
        tvNoVetHistoryYet = (TextView) view.findViewById(R.id.tvNoVetHistoryYet);

        String lastCallDateAsString = SaveSharedPreference.getKeyValuePair(context, VetFragment.PREF_VET_LAST_CALL);
        String vetName = SaveSharedPreference.getKeyValuePair(context, VetFragment.PREF_VET_NAME);

        if (!vetName.isEmpty()){
            tvNoVetHistoryYet.setVisibility(View.GONE);
            vetLastCallDateTextView.setText(lastCallDateAsString);
            vetNameTextView.setText(vetName);
        }
        
        return view;
    }
}
