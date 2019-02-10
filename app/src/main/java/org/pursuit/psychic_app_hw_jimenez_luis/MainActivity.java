package org.pursuit.psychic_app_hw_jimenez_luis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.pursuit.psychic_app_hw_jimenez_luis.fragments.ChoiceFragment;
import org.pursuit.psychic_app_hw_jimenez_luis.fragments.FragmentInterface;
import org.pursuit.psychic_app_hw_jimenez_luis.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMainFragment();

    }

    @Override
    public void startMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commit();

    }

    @Override
    public void startChoiceFragment(String key) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ChoiceFragment.newInstance(key))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void startResultsFragment(boolean result) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startMainFragment();
    }
}
