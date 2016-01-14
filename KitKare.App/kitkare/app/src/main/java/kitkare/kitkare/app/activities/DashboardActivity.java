package kitkare.kitkare.app.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.ConnectionChecker;
import kitkare.kitkare.app.common.MenuPopulator;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.data.remote.services.AccountService;
import kitkare.kitkare.app.activities.fragments.account.LoginFragment;
import kitkare.kitkare.app.activities.fragments.dashboard.DashboardFragment;

public class DashboardActivity extends AppCompatActivity {
    private final Context context = this;

    public AccountService accountService;

    public DashboardActivity() {
        this(new AccountService());
    }

    public DashboardActivity(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //TODO background task?
        ConnectionChecker.checkIfInternetConnection(context);

        if(SaveSharedPreference.getUserName(context).length() == 0)
        {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            this.getFragment(new LoginFragment());
            this.finish();
            return;
        }

//        String pageTitle = getResources().getString(R.string.app_name) + ": " + getResources().getString(R.string.tvDashboard);
//        this.setTitle(pageTitle);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        if (savedInstanceState == null) {
            this.getFragment(new DashboardFragment());
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuPopulator.prepareOptionsMenu(menu, context);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isActionExecuted = MenuPopulator.setMenuOptions(item, this);
        if (isActionExecuted) {
            return isActionExecuted;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void getFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left, R.animator.fragment_slide_right);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }

    public void getFragment(Fragment fragment, int enter, int exit) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }
}
