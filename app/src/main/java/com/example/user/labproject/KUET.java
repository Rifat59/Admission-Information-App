package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class KUET extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuet);

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
                    doc = (Document) Jsoup.connect( "http://www.kuet.ac.bd/admission/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements table = doc.select("table");
                Elements rows = table.select("tr");

                for(int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements data = row.select("td");
                        builder.append("Text : ").append(data.get(0).text()).append("\n");
                    Elements links = data.select("a[href]");
                    Element link;
                    link = links.get(0);
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
