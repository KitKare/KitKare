package kitkare.kitkare.app.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.dataModels.UserData;
import kitkare.kitkare.app.services.AccountService;
import kitkare.kitkare.app.views.partials.account.LoginFragment;
import kitkare.kitkare.app.views.partials.dashboard.DashboardFragment;

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

        if(SaveSharedPreference.getUserName(context).length() == 0)
        {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            this.getFragment(new LoginFragment());
            this.finish();
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
        Helper.prepareOptionsMenu(menu, context);
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
        boolean isActionExecuted = Helper.setMenuOptions(item, this);
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
}
