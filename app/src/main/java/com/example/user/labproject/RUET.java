package com.example.user.labproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RUET extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruet);

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
                    doc = (Document) Jsoup.connect( "http://www.ruet.ac.bd/admission/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements div = doc.getElementsByClass("col-md-4");
                Elements links = div.select("a[href]");

                for(Element link : links) {
                    builder.append("Text : ").append(link.text()).append("\n");
                    builder.append("Link : ").append(link.attr("abs:href")).append("\n").append("\n");
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
