package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import timber.log.Timber;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi mMyApiService;

    private JokeResponse mDelegate;

    public EndpointsAsyncTask(JokeResponse delegate) {
        this.mDelegate = delegate;

        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (mMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            mMyApiService = builder.build();
        }

        try {
            Timber.d("Executing API call now...");
            return mMyApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            Timber.d(e, "Error while executing async API call");
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Timber.d("API call ready. Returning joke...");
        mDelegate.ready(joke);
    }
}
