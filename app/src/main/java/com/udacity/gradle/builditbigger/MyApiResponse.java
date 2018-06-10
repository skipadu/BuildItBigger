package com.udacity.gradle.builditbigger;

public class MyApiResponse {
    private String message;
    private boolean isFailure;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFailure() {
        return isFailure;
    }

    public void setFailure(boolean failure) {
        isFailure = failure;
    }
}
