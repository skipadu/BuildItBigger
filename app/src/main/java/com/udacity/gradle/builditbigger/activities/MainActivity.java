package com.udacity.gradle.builditbigger.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.pihrit.joketeller.JokeTellerActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.api.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.api.JokeResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_JOKE = "joke";

    @Nullable
    private CountingIdlingResource mCountingIdlingResource;

    @BindView(R.id.root_activity_main)
    CoordinatorLayout mRootCoordinatorLayout;
    @BindView(R.id.loading_indicator)
    ProgressBar mLoadingIndicator;

    @VisibleForTesting
    @NonNull
    public CountingIdlingResource getCountingIdlingResource() {
        if (mCountingIdlingResource == null) {
            mCountingIdlingResource = new CountingIdlingResource(MainActivity.class.getSimpleName());
        }
        return mCountingIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());
    }

    public void tellJoke(View v) {
        getCountingIdlingResource().increment();
        setLoadingIndicatorVisible(true);

        new EndpointsAsyncTask(new JokeResponse() {
            @Override
            public void success(String joke) {
                getCountingIdlingResource().decrement();
                setLoadingIndicatorVisible(false);
                Intent jokeTellerIntent = new Intent(MainActivity.this, JokeTellerActivity.class);
                jokeTellerIntent.putExtra(EXTRA_JOKE, joke);
                startActivity(jokeTellerIntent);
            }

            @Override
            public void failure(String error) {
                getCountingIdlingResource().decrement();
                setLoadingIndicatorVisible(false);

                final Snackbar snackbar = Snackbar.make(mRootCoordinatorLayout, error, Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.snackbar_action_error));
                snackbar.show();
            }
        }).execute();
    }

    private void setLoadingIndicatorVisible(boolean show) {
        mLoadingIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
