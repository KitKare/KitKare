package kitkare.kitkare.app.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import kitkare.kitkare.app.common.Helper;
import kitkare.kitkare.app.common.SaveSharedPreference;
import kitkare.kitkare.app.common.Validator;
import kitkare.kitkare.app.services.AccountService;
import kitkare.kitkare.app.views.partials.account.LoginFragment;
import kitkare.kitkare.R;
import kitkare.kitkare.app.views.partials.MainFragment;
import kitkare.kitkare.app.views.partials.account.RegisterFragment;

public class MainActivity extends AppCompatActivity {
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
        validator = new Validator(this);

        if(SaveSharedPreference.getUserName(MainActivity.this).length() != 0)
        {
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (savedInstanceState == null) {
            this.getFragment(new MainFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
        getFragment(new RegisterFragment());
    }

    private void getFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }
}
