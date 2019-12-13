package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Weather extends AppCompatActivity {

    TextView temperature,City,Date,Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperature = (TextView) findViewById(R.id.textViewIdTmp);
        City = (TextView) findViewById(R.id.textViewIdCity);
        Date = (TextView) findViewById(R.id.textViewIdDate);
        Description = (TextView) findViewById(R.id.textViewIdDes);

        find_weather();
    }

    public void find_weather()
    {
        String url = "https://samples.openweathermap.org/data/2.5/weather?q=islamabad,pakistan&appid=90ebdc57172a838d0fce0bbc044df8e";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try{
                    JSONObject main_object = jsonObject.getJSONObject("main");
                    JSONArray array = jsonObject.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = jsonObject.getString("name");

                    //temperature.setText(temp);
                    City.setText(city);
                    Description.setText(description);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE=104-dd");
                    String formatted_date = sdf.format(calendar.getTime());

                    Date.setText(formatted_date);

                    Double temp_int = Double.parseDouble(temp);
                    long centi = (long) ((temp_int - 32) / 1.8000);
                    centi = Math.round(centi);
                    int i = (int) centi;

                    temperature.setText(String.valueOf(i));

                }catch (JSONException e){
                    Toast.makeText(Weather.this,"Exception"+e,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }
}
