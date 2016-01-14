package kitkare.kitkare.app.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

import kitkare.kitkare.R;
import kitkare.kitkare.app.activities.fragments.AboutUsFragment;

public class AboutUsActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        if (savedInstanceState == null) {
            this.getFragment(new AboutUsFragment());
        }
    }

    private void getFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left, R.animator.fragment_slide_right);
        ft.add(R.id.container, fragment).addToBackStack("tag").commit();
    }
}