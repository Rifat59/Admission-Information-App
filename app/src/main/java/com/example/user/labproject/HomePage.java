package com.example.user.labproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    private CardView varsityCardview , weatherCardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        varsityCardview = findViewById(R.id.varsityCardviewId);
        weatherCardview = findViewById(R.id.weatherCardviewId);

        varsityCardview.setOnClickListener(this);
        weatherCardview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.varsityCardviewId){
            Intent intent = new Intent(HomePage.this,MainActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.weatherCardviewId){
            Intent intent = new Intent(HomePage.this,Weather.class);
            startActivity(intent);
        }
    }
}
