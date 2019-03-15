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
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    static View.OnClickListener myOnClickListener;

    private SpecialtyFragment specialtyFragment;
    private WorkerFragment workerFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOnClickListener = new MyOnClickListener(this);


        manager = getSupportFragmentManager();

        specialtyFragment = new SpecialtyFragment();

        transaction = manager.beginTransaction();
        transaction.add(R.id.container, specialtyFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        //SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
    }

    private class MyOnClickListener implements View.OnClickListener{

        private final Context context;

        private MyOnClickListener(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            manager = getSupportFragmentManager();

            workerFragment = new WorkerFragment();

            transaction = manager.beginTransaction();
            transaction.replace(R.id.container, workerFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
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
