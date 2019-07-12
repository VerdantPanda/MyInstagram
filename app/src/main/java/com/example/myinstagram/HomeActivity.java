package com.example.myinstagram;

import android.app.ActionBar;
import android.content.Intent;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myinstagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    List<Post> posts;
    PostAdapter postAdapter;
    //@BindView(R.id.rvPosts) RecyclerView rvPosts;
    RecyclerView rvPosts;

    //MenuItem miLogout = findViewById(R.menu.menu_main);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //ButterKnife.bind(this);

        //action bar
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        rvPosts = findViewById(R.id.rvPosts);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        rvPosts.setAdapter(postAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(layoutManager);

        loadTopPosts();

    }

    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<com.example.myinstagram.model.Post> objects, ParseException e) {
                if (e == null) {
                    // Log.d("HomeActivity", ""+objects.size());
//                    for (int i = 0; i < objects.size(); i++) {
//                        Log.d("HomeActivity", posts.toString());
//                        posts.add(objects.get(i));
//                        postAdapter.notifyItemChanged(posts.size() - 1);
//                    }
                } else {
                    e.printStackTrace();
                }
            }
        });


        try {
            posts.addAll(postsQuery.find());
        } catch (ParseException e) {
            Log.d("HomeActivity", e.toString());
        }
        Toast.makeText(getApplicationContext(), "topPosts loaded", Toast.LENGTH_LONG).show();
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user) {
        //posts.add(new Post(description, imageFile, user));
        postAdapter.notifyDataSetChanged();
        //TODO: when taking picture will need to create post, presumably using this function
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logout();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        ParseUser.logOut();
    }


}
