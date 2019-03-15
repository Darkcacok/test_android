package com.example.test_android;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.io.InputStream;


public class MainActivity extends AppCompatActivity {


    private SpecialtyFragment specialtyFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = getSupportFragmentManager();

        specialtyFragment = new SpecialtyFragment();

        transaction = manager.beginTransaction();
        transaction.add(R.id.container, specialtyFragment);
        transaction.commit();

        //SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

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
