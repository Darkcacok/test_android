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

import java.util.ArrayList;
import java.util.Date;

public class WorkerFragment extends Fragment {

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static final String TAG = "WorkerFragment";

    private RecyclerView recyclerView;
    private WorkerRVAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.worker_fragment, container, false);

        recyclerView = (RecyclerView) roorView.findViewById(R.id.workerFragmentRV);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Bundle args = getArguments();

        ArrayList<Worker> workers = (ArrayList<Worker>) args.getSerializable("worker");

        mAdapter = new WorkerRVAdapter(workers);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkerRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mListener.onItemClick(position);
            }
        });

        return roorView;
    }
}
