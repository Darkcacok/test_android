package com.example.test_android;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, FromJsonToWorker> {
    String data = "";

    @Override
    protected FromJsonToWorker doInBackground(Void... voids) {
        URL url = null;
        try {
            url = new URL("https://gitlab.65apps.com/65gb/static/raw/master/testTask.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                line = line.replaceAll("\t", "");
                data = data + line;
            }

            Gson gson = new GsonBuilder().setLenient().create();
            FromJsonToWorker FromJsonToWorker = gson.fromJson(data, FromJsonToWorker.class);

            return FromJsonToWorker;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(FromJsonToWorker fromJsonToWorker){
        super.onPostExecute(fromJsonToWorker);
    }
}
