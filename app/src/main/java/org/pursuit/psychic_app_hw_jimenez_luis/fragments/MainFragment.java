package org.pursuit.psychic_app_hw_jimenez_luis.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import org.pursuit.psychic_app_hw_jimenez_luis.R;


public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private FragmentInterface fragmentInterface;
    private View rootView;
    private Spinner spinner;
    private Button button;
    private String selection;

    public MainFragment() {
    }
    public static MainFragment newInstance(){ return new MainFragment();}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInterface = (FragmentInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        spinner = rootView.findViewById(R.id.theme_spinner);
        button = rootView.findViewById(R.id.main_fragment_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = spinner.getSelectedItem().toString();
                Log.d(TAG, "onClick: " + selection);
                fragmentInterface.startChoiceFragment(selection);
            }
        });
        return rootView;
    }
}
