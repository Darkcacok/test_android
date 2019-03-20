package com.example.test_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkerRVAdapter extends RecyclerView.Adapter<WorkerRVAdapter.MyViewHolder> {


    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    private ArrayList<Worker> workers;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tViewfName;
        public TextView tViewlName;
        public TextView tViewAge;

        public MyViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            tViewfName = (TextView) v.findViewById(R.id.firstName);
            tViewlName = (TextView) v.findViewById(R.id.lastName);
            tViewAge = (TextView) v.findViewById(R.id.age);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public WorkerRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_card, parent, false);

            MyViewHolder vh = new MyViewHolder(v, mListener);

            return vh;
        }


    public WorkerRVAdapter(ArrayList<Worker> workers){
        this.workers = workers;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tViewfName.setText("Имя: " + workers.get(position).getFirstName());
        holder.tViewlName.setText("Фамилия: " + workers.get(position).getLastName());
        holder.tViewAge.setText("Возраст: " + workers.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }
}
