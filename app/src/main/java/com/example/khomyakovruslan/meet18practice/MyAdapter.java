package com.example.khomyakovruslan.meet18practice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int type = 0;
    List<String> list;

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
            case 3: return new MuViewHolder3(view);
            case 4: return new MuViewHolder4(view);
        }
        return new MuViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 1: MuViewHolder1 muViewHolder1 = (MuViewHolder1)holder;
            break;
            case 2: MuViewHolder2 muViewHolder2 = (MuViewHolder2)holder;
            break;
            case 3: MuViewHolder3 muViewHolder3 = (MuViewHolder3)holder;
            break;
            case 4: MuViewHolder4 muViewHolder4 = (MuViewHolder4)holder;
            break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MuViewHolder1 extends RecyclerView.ViewHolder{
        TextView textView;

        public MuViewHolder1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText("first way");
        }
    }
    class MuViewHolder2 extends RecyclerView.ViewHolder{
        TextView textView;
        public MuViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText("second way");
        }
    }
    class MuViewHolder3 extends RecyclerView.ViewHolder{
        TextView textView;
        public MuViewHolder3(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText("third way");
        }
    }
    class MuViewHolder4 extends RecyclerView.ViewHolder{
        TextView textView;
        public MuViewHolder4(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.way_textview);
            textView.setText("forth way");
        }
    }
}
