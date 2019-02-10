package org.pursuit.psychic_app_hw_jimenez_luis.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.pursuit.psychic_app_hw_jimenez_luis.R;
import org.pursuit.psychic_app_hw_jimenez_luis.database.ChoiceDatabase;
import org.pursuit.psychic_app_hw_jimenez_luis.model.Choice;

import java.util.Objects;
import java.util.Random;

public class ChoiceFragment extends Fragment implements View.OnClickListener {
    private static final String CHOICE_FRAGMENT_KEY = "CHOICE FRAGMENT";
    private static final String TAG = "CHOICE";
    private String theme;
    private View rootView;
    private FragmentInterface fragmentInterface;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private TypedArray imgs;
    private int npc;

    public ChoiceFragment() {
    }

    public static ChoiceFragment newInstance(String fragmentInstance) {
        ChoiceFragment fragment = new ChoiceFragment();
        Bundle args = new Bundle();
        args.putString(CHOICE_FRAGMENT_KEY, fragmentInstance);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInterface = (FragmentInterface) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            theme = getArguments().getString(CHOICE_FRAGMENT_KEY);
            assert theme != null;
            selectImages(theme);
            chooseRandom();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_choice, container, false);

        setupViews();
        setupOnClickListeners();

        return rootView;
    }

    private void setupOnClickListeners() {
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Choice choice = new Choice(getResult(v), theme);
        ChoiceDatabase guessDatabase = ChoiceDatabase.getInstance(Objects
                .requireNonNull(getActivity()).getApplicationContext());
        guessDatabase.addChoice(choice);
        Log.d(TAG, "onClick: " + choice.isCorrect() + " " + choice.getTheme());
        fragmentInterface.startResultsFragment(getResult(v));
    }

    private boolean getResult(View v) {
        int choice = -1;
        switch (v.getId()) {
            case R.id.first_image:
                choice = 1;
                break;
            case R.id.second_image:
                choice = 2;
                break;
            case R.id.third_image:
                choice = 3;
                break;
            case R.id.fourth_image:
                choice = 4;
                break;
        }
        return npc == choice;
    }
    private void selectImages(String theme) {
        switch (theme) {
            case "Cars":
                imgs = getResources().obtainTypedArray(R.array.cars_array);
                break;
            case "Cats":
                imgs = getResources().obtainTypedArray(R.array.cats_array);
                break;
            case "Dogs":
                imgs = getResources().obtainTypedArray(R.array.dogs_array);
                break;
            default:
                imgs = null;
        }
    }
    private void chooseRandom() {
        Random random = new Random();
        npc = random.nextInt(4) + 1;
        Log.d(TAG, "chooseRandom: " + npc);
    }

    @SuppressLint("ResourceType")
    private void injectDrawables() {
        if (imgs != null) {
            image1.setImageResource(imgs.getResourceId(0, -1));
            image2.setImageResource(imgs.getResourceId(1, -1));
            image3.setImageResource(imgs.getResourceId(2, -1));
            image4.setImageResource(imgs.getResourceId(3, -1));
            imgs.recycle();
        }
    }
    private void setupViews() {
        image1 = rootView.findViewById(R.id.first_image);
        image2 = rootView.findViewById(R.id.second_image);
        image3 = rootView.findViewById(R.id.third_image);
        image4 = rootView.findViewById(R.id.fourth_image);
        injectDrawables();
    }
}
