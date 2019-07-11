package com.example.myinstagram.model;

import androidx.versionedparcelable.ParcelField;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcel;

//@Parcel
@ParseClassName("Post")
public class Post extends ParseObject {
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_IMAGE = "image";
    static final String KEY_USER = "user";


    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }



    public Post() {
    }

    public Post(String description, ParseFile image, ParseUser user) {
        put(KEY_DESCRIPTION, description);
        put(KEY_IMAGE, image);
        put(KEY_USER, user);
    }


    public static class Query extends ParseQuery<Post> {
        public Query() {
            super(Post.class);
        }

        public Query getTop() {
            setLimit(20);
            return this;
        }

        public Query withUser(){
            include("user");
            return  this;
        }


    }


}
