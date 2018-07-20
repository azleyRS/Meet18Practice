package com.example.khomyakovruslan.meet18practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.khomyakovruslan.meet18practice.retrofit.FlickrAPI;
import com.example.khomyakovruslan.meet18practice.retrofit.FlickrResponce;
import com.example.khomyakovruslan.meet18practice.retrofit.Photo;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        FlickrAPI.ApiInterface api = FlickrAPI.getClient().create(FlickrAPI.ApiInterface.class);
        Call<FlickrResponce> photosCall = api.getPhotos(FlickrAPI.API_KEY,FlickrAPI.FORMAT,FlickrAPI.NOJSONCALLBACK,FlickrAPI.EXTRA);
        photosCall.enqueue(new Callback<FlickrResponce>() {
            @Override
            public void onResponse(Call<FlickrResponce> call, Response<FlickrResponce> response) {
                FlickrResponce flickrResponce = response.body();

                List<String> result = new ArrayList<>();
                List<Photo> photoList = flickrResponce.getPhotos().getPhoto();
                for (Photo photo : photoList){
                    result.add(photo.getUrlS());
                }
                Log.v("TAG", result.toString());

                adapter.update(result, getBaseContext());

            }

            @Override
            public void onFailure(Call<FlickrResponce> call, Throwable t) {
                Log.v("TAG","failed");
            }
        });
    }
}
