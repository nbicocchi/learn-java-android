package com.ImageUploader;

import android.net.Uri;

import com.google.firebase.database.Exclude;

public class Upload {
    private String nameImage;
    private String imageURL;
    @Exclude
    private String key;



    public Upload()
    {
        //we need this empty constructor
    }

    public Upload(String nameImage, String imageURL) {

        if(nameImage.trim().equals(""))
        {
            nameImage="no Name";

        }
        this.nameImage = nameImage;
        this.imageURL = imageURL;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
