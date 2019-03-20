package com.example.test_android;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainActivity extends AppCompatActivity {

    DBHelper db = new DBHelper(this);

    Specialty[] specialty = {new Specialty(101, "Менеджер"), new Specialty(102, "Разработчик")};
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
        args.putSerializable("specialty", specialty);
        specialtyFragment.setArguments(args);
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, specialtyFragment);
        //transaction.addToBackStack(null);
        transaction.commit();


        fetchData fetchData = new fetchData();
        fetchData.execute();
        FromJsonToWorker fromJsonToWorker = new FromJsonToWorker();

        try {
            fromJsonToWorker = fetchData.get(4, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertSpecialty(db, 101, "Менеджер");
        insertSpecialty(db, 102, "Разработчик");

        ArrayList<Worker> dataExample = fromJsonToWorker.getResponse();

        for(int i = 0; i < dataExample.size(); ++i)
            for(int j = 0; j < dataExample.get(i).getArraySpecialty().size(); ++j)
                insertWorkers(db,dataExample.get(i), j);

    }

    private void insertSpecialty(SQLiteDatabase db, int id, String name)
    {
        ContentValues values = new ContentValues();

        values.put(SampleDBContract.Specialty.COLUMN_SPECIALTY_ID, id);
        values.put(SampleDBContract.Specialty.COLUMN_NAME, name);

        db.insert(SampleDBContract.Specialty.TABLE_NAME, null, values);
    }

    private void insertWorkers(SQLiteDatabase db, Worker w, int spec)
    {
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employee.COLUMN_FIRSTNAME, w.getFirstName());
        values.put(SampleDBContract.Employee.COLUMN_LASTNAME, w.getLastName());
        if(w.getDate() != null)
            values.put(SampleDBContract.Employee.COLUMN_DATE_OF_BIRTH, w.getDate().getTime());
        values.put(SampleDBContract.Employee.COLUMN_AVATAR_URL, w.getAvatr_url());
        values.put(SampleDBContract.Employee.COLUMN_SPECIALTY_ID, w.getArraySpecialty().get(spec).getSpecialty_id());

        db.insert(SampleDBContract.Employee.TABLE_NAME, null, values);
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
            curentWorkers = getCurentWorkers(w, specialty[position]);
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

    private ArrayList<Worker> getCurentWorkers(Worker[] w, Specialty spec){
        ArrayList<Worker> cw = new ArrayList<Worker>();

        for(int i = 0; i < w.length; ++i){
            if(w[i].isSpecialty(spec))
                cw.add(w[i]);
        }

        return cw;
    }

    private Worker[] CreateWorkers(){
        Worker[] w = new Worker[10];
        Date date;
        Specialty manager = new Specialty(101, "Менеджер");

//        for(int i = 0; i < 5; i++){
//            String fname = "Имя" + String.valueOf(i);
//            String lname = "Фамилия" + String.valueOf(i);
//            int year = 1996 + i - 1900;
//            int month = 0 + i;
//            int day = 1 + i;
//            date = new Date(year, month, day);
//
//            w[i] = new Worker(fname, lname, date, menager);
//        }
//
        Specialty developer= new Specialty(102, "Разработчик");
//
//        for(int i = 5; i < 10; i++){
//
//            String fname = "Имя" + String.valueOf(i);
//            String lname = "Фамилия" + String.valueOf(i);
//            int year = 1996 + i - 1900;
//            int month = 0 + i;
//            int day = 1 + i;
//            date = new Date(year, month, day);
//
//            w[i] = new Worker(fname, lname, date, developer);
//        }


        w[0] = new Worker("иВан", "ИваноВ", "1987-03-23", manager);
        w[1] = new Worker("Петр", "петроВ", null, manager);
        ArrayList<Specialty> spec = new ArrayList<>();
        spec.add(manager); spec.add(developer);
        w[2] = new Worker("Вася", "Пупкин",  "1985-11-29", spec);
        w[3] = new Worker("ЕКАТЕРИНА", "пертрова",  "1990-01-07", developer);
        w[4] = new Worker("Николай", "Сидоров",  "", developer);
        w[5] = new Worker("Виктор", "Федотов",  "23-07-2000", developer);
        w[6] = new Worker("Артур", "ВАрламов",  "23-07-2000", developer);
        w[7] = new Worker("Артур", "ВАрламов",  "23-07-1982", developer);
        w[8] = new Worker("Руслан", "Русанов",  "17-10-1984", developer);
        w[9] = new Worker("Владимир", "Миронов",  "03-08-1972", developer);

        return w;
    }
}
