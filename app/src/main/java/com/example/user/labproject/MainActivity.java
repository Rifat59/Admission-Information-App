package com.example.user.labproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    ArrayAdapter<String>adapter;
    String[] varsity_names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        varsity_names = getResources().getStringArray(R.array.varsity_names);

        listView = (ListView) findViewById(R.id.listviewid);

        searchView = (SearchView) findViewById(R.id.searchViewId);
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.sample_view,R.id.textviewid,varsity_names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(MainActivity.this , KUET_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this , BUET_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent(MainActivity.this , RUET_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(MainActivity.this , CUET_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 4){
                    Intent intent = new Intent(MainActivity.this , IUT_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 5){
                    Intent intent = new Intent(MainActivity.this , Medical_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 6){
                    Intent intent = new Intent(MainActivity.this , DU_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 7){
                    Intent intent = new Intent(MainActivity.this , BAU_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 8){
                    Intent intent = new Intent(MainActivity.this , SUST_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 9){
                    Intent intent = new Intent(MainActivity.this , JU_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 10){
                    Intent intent = new Intent(MainActivity.this , RU_Homepage.class);
                    startActivity(intent);
                }
                else if(position == 11){
                    Intent intent = new Intent(MainActivity.this , CU_Homepage.class);
                    startActivity(intent);
                }

            }
        });
    }

}
