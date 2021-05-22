package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MyFragmentListener {

    // My code
    private final String FIRST_FRAGMENT_TAG = "firstFragment";
    private final String SECOND_FRAGMENT_TAG = "secondFragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        getSupportFragmentManager().popBackStack();
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment, FIRST_FRAGMENT_TAG);

        // _TODO: invoke function which apply changes of the transaction
        // My code

        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        // _TODO: implement it
        // My code
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, SECOND_FRAGMENT_TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // My code
    @Override
    public void doSomething(String fragmentTag, int... vars) {
        switch (fragmentTag) {
            case FIRST_FRAGMENT_TAG:
                openSecondFragment(vars[0], vars[1]);
                break;
            case SECOND_FRAGMENT_TAG:
                openFirstFragment(vars[0]);
                break;
        }
    }
}
