package com.example.khomyakovruslan.meet18practice;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

class LoadImage extends AsyncTask<String,Void,Bitmap> {

    private final WeakReference<ImageView> imageViewWeakReference;

    public LoadImage(ImageView imageView1) {
        imageViewWeakReference = new WeakReference<>(imageView1);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return MyAdapter.getImage(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewWeakReference != null) {
            ImageView imageView = imageViewWeakReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    Log.v("tag", "finished");
                } else {
                    /*Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.ic_launcher);
                    imageView.setImageDrawable(placeholder);*/
                }
            }
        }
    }
}
