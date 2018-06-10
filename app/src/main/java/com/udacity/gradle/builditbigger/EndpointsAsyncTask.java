package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import timber.log.Timber;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, MyApiResponse> {
    private static MyApi mMyApiService;

    private JokeResponse mDelegate;

    public EndpointsAsyncTask(JokeResponse delegate) {
        this.mDelegate = delegate;

        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected MyApiResponse doInBackground(Void... voids) {
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

        MyApiResponse response = new MyApiResponse();
        try {
            Timber.d("Executing API call now...");
            response.setMessage(mMyApiService.getJoke().execute().getJoke());
            return response;
        } catch (IOException e) {
            Timber.d(e, "Error while executing async API call");
            response.setFailure(true);
            response.setMessage("Error while executing async API call");
            return response;
        }
    }

    @Override
    protected void onPostExecute(final MyApiResponse response) {
        if (response.isFailure()) {
            Timber.d("Telling delegate that this was failure...");
            mDelegate.failure(response.getMessage());
        } else {
            Timber.d("Telling delegate that this was success!");
            mDelegate.success(response.getMessage());
        }
    }

}
