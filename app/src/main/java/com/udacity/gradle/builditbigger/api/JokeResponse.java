package com.udacity.gradle.builditbigger.api;

public interface JokeResponse {
    void success(String joke);
    void failure(String error);
}
