package com.example.myinstagram.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Post")
public class Post extends ParseObject {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_USER = "user";

    public static String getDescription() {
        return KEY_DESCRIPTION;
    }

    public static String getImage() {
        return KEY_IMAGE;
    }

    public static String getUser() {
        return KEY_USER;
    }




}
