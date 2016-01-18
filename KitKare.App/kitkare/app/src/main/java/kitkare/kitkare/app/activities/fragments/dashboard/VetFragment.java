package kitkare.kitkare.app.activities.fragments.dashboard;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.custom.listeners.OnSwipeTouchListener;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.activities.DashboardActivity;

// http://stackoverflow.com/questions/4993063/how-to-call-android-contacts-list-and-select-one-phone-number-from-its-details-s
// http://stackoverflow.com/questions/4816683/how-to-make-a-phone-call-programatically
public class VetFragment extends Fragment implements View.OnClickListener {
    static final String PREF_VET_NUMBER = "KitKareVetNumber";
    static final String PREF_VET_NAME = "KitKareVetName";
    static final String PREF_VET_LAST_CALL = "KitKareLastCall";

    static String vetNumber;
    static String vetName;
    static ImageView imageViewVet;
    static Button callVet, pickVetNumber, editVetNumber, deleteVetNumber, vetHistory;
    static TextView vetNumberTextView;

    Context context;
    DashboardActivity dashboardActivity;

    public VetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vet, container,
                false);

        this.context = container.getContext();
        this.dashboardActivity = (DashboardActivity) this.context;

        callVet = (Button) view.findViewById(R.id.btnCallVet);
        pickVetNumber = (Button) view.findViewById(R.id.btnPickVet);
        editVetNumber = (Button) view.findViewById(R.id.btnEditVetNumber);
        deleteVetNumber = (Button) view.findViewById(R.id.btnDeleteVetNumber);

        imageViewVet = (ImageView) view.findViewById(R.id.imageViewVet);

        vetHistory = (Button) view.findViewById(R.id.btnVetHistory);
        vetNumberTextView = (TextView) view.findViewById(R.id.vtVetNumberSaved);

        this.getVet();
        this.setViewsVisibility();
        this.attachEventListeners();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Uri uri = data.getData();

            if (uri != null) {
                Cursor cursor = null;
                try {
                    cursor = context.getContentResolver().query(uri, new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME_PRIMARY},
                            null, null, null);

                    if (cursor != null && cursor.moveToFirst()) {
                        String number = cursor.getString(0);
                        String name = cursor.getString(1);
                        // enter action with picked number here
                        saveVet(number, name);
                        getVet();
                        setViewsVisibility();
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPickVet) {
            pickVetNumber();
        } else if (v.getId() == R.id.btnDeleteVetNumber) {
            getDeleteAlert();
        } else if (v.getId() == R.id.btnEditVetNumber){
            pickVetNumber();
        }else if (v.getId() == R.id.btnVetHistory){
            this.dashboardActivity.getFragment(new VetHistoryFragment());
        }
    }

    private void attachEventListeners() {
        imageViewVet.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                dashboardActivity.getFragment(new DashboardFragment(), R.animator.fragment_slide_right, R.animator.fragment_slide_left);
            }

            @Override
            public void onSwipeLeft() {
                dashboardActivity.getFragment(new DashboardFragment());
            }
        });

        pickVetNumber.setOnClickListener(this);
        deleteVetNumber.setOnClickListener(this);
        editVetNumber.setOnClickListener(this);
        vetHistory.setOnClickListener(this);

        // http://stackoverflow.com/questions/18911290/perform-both-the-normal-click-and-long-click-at-button
        callVet.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                callVet();
                return true;
            }
        });
    }

    private void getDeleteAlert(){
        // http://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        new AlertDialog.Builder(context)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteVet();
                        setViewsVisibility();
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void pickVetNumber() {
        // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent, 1);
    }

    private void saveVet(String number, String name) {
        SaveSharedPreference.setKeyValuePair(context, PREF_VET_NUMBER, number);
        SaveSharedPreference.setKeyValuePair(context, PREF_VET_NAME, name);
        vetNumber = number;
        vetName = name;

        //UserData.setVetNumber(number);
        Helper.makeText(context, name + ": " + number + " saved.", Toast.LENGTH_LONG);
    }

    private void callVet() {
        if (vetNumber == null || vetNumber.isEmpty()) {
            Helper.makeText(context, "Please pick a vet's number first.");
            return;
        }

        Date timeOfCall = new Date();
        SaveSharedPreference.setKeyValuePair(context, PREF_VET_LAST_CALL, timeOfCall.toString());

        Helper.makeText(context, "Calling...");

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + vetNumber));
        startActivity(intent);
    }

    private void getVet() {
        vetNumber = SaveSharedPreference.getKeyValuePair(context, PREF_VET_NUMBER); // UserData.getVetNumber()
        vetName = SaveSharedPreference.getKeyValuePair(context, PREF_VET_NAME);
    }

    private void deleteVet() {
        SaveSharedPreference.removeKeyValuePair(context, PREF_VET_NUMBER); // UserData.getVetNumber()
        SaveSharedPreference.removeKeyValuePair(context, PREF_VET_NAME); // UserData.getVetNumber()
        vetNumber = "";
        vetName = "";
    }

    private void setViewsVisibility() {
        if (vetNumber != null && !vetNumber.isEmpty()) {
            vetNumberTextView.setText(vetName + ": " + vetNumber);
            pickVetNumber.setVisibility(View.GONE);
            editVetNumber.setVisibility(View.VISIBLE);
            deleteVetNumber.setVisibility(View.VISIBLE);
            callVet.setVisibility(View.VISIBLE);
            Helper.makeText(context, getResources().getString(R.string.hintCallBtn, Toast.LENGTH_LONG));
        } else {
            editVetNumber.setVisibility(View.GONE);
            deleteVetNumber.setVisibility(View.GONE);
            pickVetNumber.setVisibility(View.VISIBLE);
            vetNumberTextView.setText(context.getResources().getString(R.string.tvNoVetNumberYet));
            callVet.setVisibility(View.GONE);
        }
    }
}
