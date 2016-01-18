package kitkare.kitkare.app.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import kitkare.kitkare.app.common.ConnectionChecker;
import kitkare.kitkare.app.common.MenuPopulator;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.common.Validator;
import kitkare.kitkare.app.data.remote.models.UserData;
import kitkare.kitkare.app.data.remote.services.AccountService;
import kitkare.kitkare.app.activities.fragments.account.LoginFragment;
import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.fragments.MainFragment;
import kitkare.kitkare.app.activities.fragments.account.RegisterFragment;
import kitkare.kitkare.app.tasks.account.LoginTask;

public class MainActivity extends AppCompatActivity {
    private final Context context = this;

    public AccountService accountService;
    public Validator validator;

    public MainActivity(){
        this(new AccountService());
    }

    public MainActivity(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionChecker.checkIfInternetConnection(context);

        if(SaveSharedPreference.getUserName(MainActivity.this).length() != 0)
        {
            setToken();

            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }

        validator = new Validator(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(new MainFragment());
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        if (savedInstanceState == null) {
            this.getFragment(new MainFragment());
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
        }else{
            int id = item.getItemId();

            if (id == R.id.action_login) {
                this.loadLogin();
                return true;
            }

            if (id == R.id.action_register) {
                this.loadRegister();
                return true;
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void loadLogin() {
        getFragment(new LoginFragment());
    }

    public void loadRegister() {
        getFragment(new RegisterFragment(), R.animator.fragment_slide_right, R.animator.fragment_slide_left);
    }

    public void getFragment(Fragment fragment, int enter, int exit){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }

    private void getFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left, R.animator.fragment_slide_right);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }

    private void setToken(){
        String token = SaveSharedPreference.getKeyValuePair(context, LoginTask.PREF_TOKEN);
        UserData.setToken(token);
    }
}
