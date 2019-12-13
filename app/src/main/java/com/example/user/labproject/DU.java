package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class DU extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du);

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
                    doc = (Document) Jsoup.connect("http://admission.eis.du.ac.bd/index.php?act=information/get_notices/ALL").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Elements divs = doc.getElementsByClass("noticeFiles");

                for(Element div : divs)
                {
                    Element table = div.select("table").first();

                    Elements datas = table.select("td");

                    for(Element data : datas)
                    {
                        Element link = data.select("a[href]").first();

                        builder.append("Text : ").append(link.text()).append("\n");
                        builder.append("Link : ").append(link.attr("abs:href")).append("\n").append("\n");

                    }
                }


                   /* Elements links = (Elements) div.getElementsByTagName("a[href]");
                    for (org.jsoup.nodes.Element link : links) {

                        builder.append("Text : ").append(link.text()).append("\n");
                        builder.append("Link : ").append(link.attr("abs:href")).append("\n").append("\n");

                    }*/

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
