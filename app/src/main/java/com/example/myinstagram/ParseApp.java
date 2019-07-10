package com.example.myinstagram;

import android.app.Application;

import com.example.myinstagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("instagram-fb")
                .clientKey("fagbamila!")
                .server("http://instagram-fb.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
