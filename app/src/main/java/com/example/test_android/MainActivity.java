package com.example.test_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String DESCRIBABLE_KEY = "describable_key";

    String[] myDataset = {"Менеджер", "Разработчик"};
    Worker[] w = CreateWorkers();
    ArrayList<Worker> curentWorkers;

    private SpecialtyFragment specialtyFragment;
    private WorkerFragment workerFragment;
    private AboutWorkerFragment aboutWorkerFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        specialtyFragment = new SpecialtyFragment();

        specialtyFragment.setOnItemClickListener(new SpecialtyFragment.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeFragment(position);
            }
        });

        Bundle args = new Bundle();
        args.putStringArray("describable_key", myDataset);
        specialtyFragment.setArguments(args);
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, specialtyFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        //SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
    }

    private void changeFragment(int position){

        manager = getSupportFragmentManager();

        if(manager.findFragmentByTag(WorkerFragment.TAG) == null) {
            workerFragment = new WorkerFragment();

            workerFragment.setOnItemClickListener(new WorkerFragment.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    changeFragment(position);
                }
            });

            Bundle args = new Bundle();
            curentWorkers = getCurentWorkers(w, myDataset[position]);
            args.putSerializable("worker", curentWorkers);
            workerFragment.setArguments(args);
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container, workerFragment, WorkerFragment.TAG);
        }
        else
            if (manager.findFragmentByTag(AboutWorkerFragment.TAG) == null){
                aboutWorkerFragment = new AboutWorkerFragment();

                Bundle args = new Bundle();
                args.putSerializable("about_worker", curentWorkers.get(position));
                aboutWorkerFragment.setArguments(args);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.container, aboutWorkerFragment, aboutWorkerFragment.TAG);
            }


        transaction.addToBackStack(null);
        transaction.commit();
    }

    private ArrayList<Worker> getCurentWorkers(Worker[] w, String spec){
        ArrayList<Worker> cw = new ArrayList<Worker>();

        for(int i = 0; i < w.length; ++i){
            if(w[i].getSpecialty().equals(spec))
                cw.add(w[i]);
        }

        return cw;
    }

    private Worker[] CreateWorkers(){
        Worker[] w = new Worker[10];
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

        spec = "Разработчик";

        for(int i = 5; i < 10; i++){

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


//    private class FetchDataTask extends AsyncTask<String, Void, String>{
//
//        @Override
//        protected String doInBackground(String... strings) {
//            InputStream inputStream = null;
//            String result = null;
//            HttpClient client = new
//            return null;
//        }
//    }
}
