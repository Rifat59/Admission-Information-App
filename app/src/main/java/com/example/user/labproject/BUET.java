package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BUET extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buet);

        textView = (TextView) findViewById(R.id.textviewid);
        getWebsite();
    }
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                Document doc = null;
                try {
                    doc = (Document) Jsoup.connect( "http://ugadmission.buet.ac.bd").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element div = doc.getElementById("menu");

                    Elements line = div.select("li");
                    for(Element lin : line){
                        Elements links = lin.select("a[href]");
                        for(Element link : links){
                            builder.append("Text : ").append(link.text()).append("\n");
                            builder.append("Link : ").append(link.attr("abs:href")).append("\n");
                        }
                        builder.append("\n").append("\n");
                    }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(builder.toString());
                    }
                });
            }
        }).start();
    }
}
