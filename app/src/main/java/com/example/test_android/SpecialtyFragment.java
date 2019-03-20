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

public class SpecialtyFragment extends Fragment {

    //private static final String DESCRIBABLE_KEY = "describable_key";

    private RecyclerView recyclerView;
    private SpecialtyRVAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.specialty_fragment, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Bundle args = getArguments();

        final Specialty[] myDataset = (Specialty[]) args.getSerializable("specialty");

        mAdapter = new SpecialtyRVAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SpecialtyRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mListener.onItemClick(position);
            }
        });

        return rootView;
    }

}
