package com.pihrit.joke_teller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeTellerActivity extends AppCompatActivity {
    private static final String EXTRA_JOKE = "joke";

    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);

        mJokeTextView = findViewById(R.id.tv_joke);

        Intent callerIntent = getIntent();
        if (callerIntent != null && callerIntent.hasExtra(EXTRA_JOKE)) {
            String joke = callerIntent.getStringExtra(EXTRA_JOKE);
            mJokeTextView.setText(joke);
        }

    }
}
