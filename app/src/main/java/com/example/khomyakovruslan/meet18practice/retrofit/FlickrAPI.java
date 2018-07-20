package com.example.khomyakovruslan.meet18practice.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FlickrAPI {
/*
    private static final String API_KEY = "3bdfb5720a010203365916585348b8b8";
    private static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final Uri ENDPOINT = Uri.parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s")
            .build();*/

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://api.flickr.com/services/rest/";
    public static final String API_KEY = "3bdfb5720a010203365916585348b8b8";
    public static final String FORMAT = "json";
    public static final String NOJSONCALLBACK = "1";
    public static final String EXTRA = "url_s";



    public interface ApiInterface{
        @GET("?method=flickr.photos.getRecent")
        Call<FlickrResponce> getPhotos(
                @Query("api_key") String API_KEY,
                @Query("format") String FORMAT,
                @Query("nojsoncallback") String NOJSONCALLBACK,
                @Query("extras") String EXTRA
        );
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
