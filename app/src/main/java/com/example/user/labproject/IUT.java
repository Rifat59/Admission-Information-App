package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import java.io.IOException;

public class IUT extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iut);

        textView = (TextView) findViewById(R.id.textviewid);
        getWebsite();
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                org.jsoup.nodes.Document doc = null;
                try {
                    doc = (org.jsoup.nodes.Document) Jsoup.connect( "http://admission.iutoic-dhaka.edu/admission_notices").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements table = doc.select("table");
                Elements rows= table.select("tr");
                for(org.jsoup.nodes.Element row : rows){
                    Elements datas = row.select("td");
                    for(org.jsoup.nodes.Element data: datas) {

                        Elements links = row.select("a[href]");
                        for(org.jsoup.nodes.Element link:links)
                        {
                            builder.append("Text : ").append(link.text()).append("\n");
                            builder.append("Link : ").append(link.attr("abs:href")).append("\n").append("\n");
                            break;
                        }
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
