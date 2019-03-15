package com.example.test_android;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkerRVAdapter extends RecyclerView.Adapter<WorkerRVAdapter.MyViewHolder> {

    private Worker[] workers;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tViewfName;
        public TextView tViewlName;
        public TextView tViewAge;

        public MyViewHolder(View v) {
            super(v);
            tViewfName = (TextView)v.findViewById(R.id.firstName);
            tViewlName = (TextView)v.findViewById(R.id.lastName);
            tViewAge = (TextView)v.findViewById(R.id.age);
        }
    }

    public WorkerRVAdapter(Worker[] workers){
        this.workers = workers;
    }

    @Override
    public WorkerRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tViewfName.append(" " + workers[position].getFirstName());
        holder.tViewlName.append(" " + workers[position].getLastName());
        holder.tViewAge.append(" " + String.valueOf(workers[position].getAge()));
    }

    @Override
    public int getItemCount() {
        return workers.length;
    }
}
