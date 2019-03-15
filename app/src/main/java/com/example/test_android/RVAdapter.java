package com.example.test_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{

    private String[] mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.textView);
        }
    }

    public RVAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount(){
        return mDataset.length;
    }
}
