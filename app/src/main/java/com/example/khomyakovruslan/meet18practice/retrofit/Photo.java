package com.example.khomyakovruslan.meet18practice.retrofit;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("url_s")
    public String urlS;

    public String getUrlS() {
        return urlS;
    }
}
