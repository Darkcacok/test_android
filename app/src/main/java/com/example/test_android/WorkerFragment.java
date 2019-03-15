package com.example.test_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

public class WorkerFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.worker_fragment, container, false);

        recyclerView = (RecyclerView) roorView.findViewById(R.id.workerFragmentRV);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Worker[] w = CreateWorkers();

        mAdapter = new WorkerRVAdapter(w);
        recyclerView.setAdapter(mAdapter);

        return roorView;
    }

    private Worker[] CreateWorkers(){
        Worker[] w = new Worker[5];
        Date date;
        String spec = "Менеджер";

        for(int i = 0; i < 5; i++){
            String fname = "Имя" + String.valueOf(i);
            String lname = "Фамилия" + String.valueOf(i);
            int year = 1996 + i - 1900;
            int month = 0 + i;
            int day = 1 + i;
            date = new Date(year, month, day);

            w[i] = new Worker(fname, lname, date, spec);
        }

        return w;
    }
}
