package com.example.test_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpecialtyRVAdapter extends RecyclerView.Adapter<SpecialtyRVAdapter.MyViewHolder>{

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    private Specialty[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public MyViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            textView = (TextView)v.findViewById(R.id.textView);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public SpecialtyRVAdapter(Specialty[] myDataset){
        mDataset = myDataset;
    }

    @Override
    public SpecialtyRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v, mListener);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.textView.setText(mDataset[position].getName());
    }

    @Override
    public int getItemCount(){
        return mDataset.length;
    }
}
