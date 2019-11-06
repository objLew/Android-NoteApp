package com.example.lewislovette_7814291;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UsersModel {
    //Dealing with user data
    private String email;
    private String password;
    private Bitmap profilePic;

    private View view;
    DatabaseHandler db;

    private static final UsersModel oneInstance = new UsersModel();

    public static UsersModel getInstance() {
        return oneInstance;
    }

    private UsersModel() {

    }

    public Bitmap getProfilePic() {
        byte[] blob = db.getPicture(email);    //retrieve profile pic for specific user

        ByteArrayInputStream imageStream = new ByteArrayInputStream(blob);
        Bitmap blobToBitmap = BitmapFactory.decodeStream(imageStream);

        return blobToBitmap;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        profilePic.compress(Bitmap.CompressFormat.PNG, 100, out);

        //converting to blob to pass to db;
        byte[] toBlob = out.toByteArray();

        db.setPicture(toBlob);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        //would then get data from db about this user (if it exists)
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
