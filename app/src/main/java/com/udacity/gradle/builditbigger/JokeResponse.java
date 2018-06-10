package com.udacity.gradle.builditbigger;

public interface JokeResponse {
    void success(String joke);
    void failure(String error);
}
