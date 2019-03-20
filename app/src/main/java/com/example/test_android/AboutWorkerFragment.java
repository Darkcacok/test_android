package com.example.test_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutWorkerFragment extends Fragment {


    TextView tViewfName;
    TextView tViewlName;
    TextView birthday;
    TextView tViewAge;
    TextView specialty;


    public static final String TAG = "AboutWorkerFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View roorView = inflater.inflate(R.layout.about_worker_fragment, container, false);

        tViewfName = (TextView)roorView.findViewById(R.id.firstName);
        tViewlName = (TextView)roorView.findViewById(R.id.lastName);
        birthday = (TextView)roorView.findViewById(R.id.birthday);
        tViewAge = (TextView)roorView.findViewById(R.id.age);
        specialty = (TextView)roorView.findViewById(R.id.specialty);

        Bundle args = getArguments();

        Worker w = (Worker) args.getSerializable("about_worker");

        tViewfName.setText("Имя: " + w.getFirstName());
        tViewlName.setText("Фамилия: " + w.getLastName());
        birthday.setText("Дата рождения: " + w.getBirthday());
        tViewAge.setText("Возраст: " + w.getAge());
        specialty.setText("Должность: " + w.getSpecialty());

        return roorView;
    }
}
