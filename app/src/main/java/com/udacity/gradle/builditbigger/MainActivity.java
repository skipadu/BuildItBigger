package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pihrit.joke_teller.JokeTellerActivity;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_JOKE = "joke";

    @Nullable
    private CountingIdlingResource mCountingIdlingResource;

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

        Timber.plant(new Timber.DebugTree());
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

    public void tellJoke(View view) {
        getCountingIdlingResource().increment();
        new EndpointsAsyncTask(new JokeResponse() {
            @Override
            public void ready(String joke) {
                getCountingIdlingResource().decrement();
                Intent jokeTellerIntent = new Intent(MainActivity.this, JokeTellerActivity.class);
                jokeTellerIntent.putExtra(EXTRA_JOKE, joke);
                startActivity(jokeTellerIntent);
            }
        }).execute();
    }

}
