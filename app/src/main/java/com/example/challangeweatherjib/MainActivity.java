package com.example.challangeweatherjib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    class Weather extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String content;
        Weather weather = new Weather();
        content = weather.execute("").get();
    }
}
