package com.example.myinstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("instagram-fb")
                .clientKey("fagbamila!")
                .server("http://instagram-fb.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
