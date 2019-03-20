package com.example.test_android;

import java.util.ArrayList;

public class FromJsonToWorker {
    private ArrayList<Worker> response = new ArrayList<>();

    public FromJsonToWorker(ArrayList<Worker> response){
        this.response = response;
    }

    public FromJsonToWorker() {

    }

    public ArrayList<Worker> getResponse() {
        return response;
    }
}
