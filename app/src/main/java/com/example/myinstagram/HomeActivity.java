package com.example.myinstagram;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


import com.example.myinstagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    List<Post> posts;
    PostAdapter postAdapter;
    RecyclerView rvPosts;
    BottomNavigationView bnvBar;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final static int MY_ACTIVITY_REQUEST_CODE = 1044;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvPosts = findViewById(R.id.rvPosts);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        rvPosts.setAdapter(postAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(layoutManager);
        bnvBar = findViewById(R.id.bnvBar);
        bnvBar.setSelectedItemId(R.id.action_home);
        bnvBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_camera:

                        Intent toNewPostActivity = new Intent(HomeActivity.this, NewPostActivity.class);
                        startActivityForResult(toNewPostActivity, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                        postAdapter.notifyDataSetChanged();
                        rvPosts.scrollToPosition(0);
                        loadTopPosts();
                        break;
                }
                return true;
            }
        });
        loadTopPosts();
    }

    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        try {
            posts.clear();
            posts.addAll(postsQuery.find());
            postAdapter.notifyDataSetChanged();

        } catch (ParseException e) {
            Log.d("HomeActivity", e.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1034) {
            if (resultCode == 1044) {
                Post recentlyTaken = (Post) data.getExtras().get("post");
                posts.add(0, recentlyTaken);
                postAdapter.notifyItemInserted(0);
                loadTopPosts();
                Log.d("HELP", recentlyTaken.toString());

            } else {
                // Result was a failure
            }
        }
    }
}
