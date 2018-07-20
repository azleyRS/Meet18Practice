package com.example.khomyakovruslan.meet18practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int type = 0;
    List<String> list;
    List<String> imageUrlList;
    Context context;

    public MyAdapter() {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
    }

    @Override
    public int getItemViewType(int position) {
        type = Integer.parseInt(list.get(position));

        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        switch (viewType){
            case 1: return new MuViewHolder1(view);
            case 2: return new MuViewHolder2(view);
            case 3: View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawee_list_item,parent,false);
                return new MuViewHolder3(view3);
            case 4: return new MuViewHolder4(view);
        }
        return new MuViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 1: MuViewHolder1 muViewHolder1 = (MuViewHolder1)holder;
            muViewHolder1.bind(position);
                if (imageUrlList != null){
                    new LoadImage(((MuViewHolder1) holder).imageView1).execute(imageUrlList.get(position));
                    //((MuViewHolder1) holder).imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            break;
            case 2: MuViewHolder2 muViewHolder2 = (MuViewHolder2)holder;
            muViewHolder2.bind(position);
            if (imageUrlList != null){
                Picasso.get()
                        .load(imageUrlList.get(position))
                        .into(((MuViewHolder2) holder).imageView2);
            }

            break;
            case 3: MuViewHolder3 muViewHolder3 = (MuViewHolder3)holder;
            muViewHolder3.bind(position);
                if (imageUrlList != null){
                    Uri uri = Uri.parse(imageUrlList.get(position));
                    Log.v("tag",imageUrlList.get(position));
                    ((MuViewHolder3) holder).draweeView.setImageURI(uri);
                }
            break;
            case 4: MuViewHolder4 muViewHolder4 = (MuViewHolder4)holder;
            muViewHolder4.bind(position);
                if (imageUrlList != null){
                    Glide.with(context).load(imageUrlList.get(position)).into(((MuViewHolder4) holder).imageView);
                }

            break;
        }
    }

    public static Bitmap getImage(String s) {
        try {
            URL url = new URL(s);
            return getImage(url);
        } catch (MalformedURLException e) {
            Log.v("TAG", "fail " + s);
            return null;
        }
    }

    public static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            Log.v("TAG", "connected");
            int responceCode = connection.getResponseCode();
            if (responceCode == 200){
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else {
                return null;
            }
        } catch (Exception e){
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<String> result, Context baseContext) {
        imageUrlList = result;
        this.context = baseContext;
        this.notifyDataSetChanged();
    }

    class MuViewHolder1 extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView1;
        private int position;

        public MuViewHolder1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText(R.string.url_http_connection);
            imageView1 = itemView.findViewById(R.id.image_view);
        }

        public void bind(int position) {
            this.position = position;
        }
    }
    class MuViewHolder2 extends RecyclerView.ViewHolder{
        TextView textView;
        private int position;
        ImageView imageView2;

        public MuViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText(R.string.picasso);
            imageView2 = itemView.findViewById(R.id.image_view);
        }

        public void bind(int position) {
            this.position = position;
        }
    }
    class MuViewHolder3 extends RecyclerView.ViewHolder{
        TextView textView;
        private int position;
        SimpleDraweeView draweeView;

        public MuViewHolder3(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.drawee_textview);
            textView.setText(R.string.fresco);
            draweeView = itemView.findViewById(R.id.drawee_view);
        }

        public void bind(int position) {
            this.position = position;
        }
    }
    class MuViewHolder4 extends RecyclerView.ViewHolder{
        TextView textView;
        private int position;
        ImageView imageView ;

        public MuViewHolder4(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText("forth way");
            imageView = itemView.findViewById(R.id.image_view);
        }

        public void bind(int position) {
            this.position = position;
        }
    }
}
