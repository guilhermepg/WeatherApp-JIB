package com.example.challangeweatherjib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView citySearch;
    Button searchButton;

    class Weather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... address) {

            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Establish connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return it as String
                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    public void search(View view){
        citySearch = findViewById(R.id.citySearch);
        searchButton = findViewById(R.id.searchButton);

        String cName = citySearch.getText().toString();

        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://samples.openweathermap.org/data/2.5/weather?q=" + cName + "&appid=5e902854616832562f41797c0d656292").get();
            Log.i("tag", "content");

            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString( "weather");
            Log.i("weatherData", weatherData);

            JSONArray array = new JSONArray(weatherData);

            String main = "";
            String description = "";

            for (int i = 0; i < array.length(); i++){
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");
            }

            Log.i("main",main);
            Log.i("description",description);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
