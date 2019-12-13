package com.example.user.labproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SUST extends AppCompatActivity {

    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sust);

        textView = (TextView) findViewById(R.id.textViewId);

        getWebsite();
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();


                Document doc = null;
                try {
                    doc = (Document) Jsoup.connect( "http://admission.sust.edu/noticeboard").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements div1 =doc.getElementsByClass("media align-items-center");
                for(Element div2 : div1)
                {
                    Elements divs = div2.getElementsByClass("media-body p-4");
                    for(Element div : divs)
                    {
                        Elements links = div.select("a[href]");
                        for(Element link : links )
                        {
                            builder.append("Text: ").append(link.text()).append("\n");
                            builder.append("Link : ").append(link.attr("abs:href")).append("\n").append("\n");
                            break;
                        }
                        break;

                    }


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
