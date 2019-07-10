package com.example.myinstagram;

import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.myinstagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadTopPosts();


    }

    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        Log.d("HomeActivity",
                                String.format("Post[%d] = %s\nusername: %s",
                                        i,
                                        objects.get(i).getDescription(),
                                        objects.get(i).getUser().getUsername()
                                ));
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user) {
        //TODO: create and save post
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void logout(){
        ParseUser.logOut();
    }




}
