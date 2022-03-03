package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportEvaluations extends AppCompatActivity {

    private Database db = new Database();

    private String value;
    private Integer reportID;

    private  ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_evaluations);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            reportID = extras.getInt("reportID");
            //reportID = Integer.valueOf(value);
            System.out.println("ReportEvaluations: Report Received: " + reportID);
        }

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**
         * Obter um array com os comentarios do report
         */
        db.getReportEvaluations(this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                ArrayList<String> data = (ArrayList<String>) response;

                ArrayList<String> comments = new ArrayList<>();
                ArrayList<Boolean> likes = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();
                ArrayList<String> time = new ArrayList<>();

                System.out.println(data);

                for (int i = 0 ; i < data.size() ; i += 4) {
                    comments.add(data.get(i));
                }

                for (int i = 1 ; i < data.size() ; i += 4) {
                    likes.add(Boolean.valueOf(data.get(i)));
                }

                for (int i = 2 ; i < data.size() ; i += 4) {
                    date.add(data.get(i));
                }

                for (int i = 3 ; i < data.size() ; i += 4) {
                    time.add(data.get(i));
                }

                //System.out.println("COMMENTS = " + comments);
                //System.out.println("LIKES = " + likes);

                ArrayList<ListItem> items = new ArrayList<>();

                for(int i = 0; i < comments.size(); ++i){
                    if(likes.get(i) == true){
                        items.add(new ListItem(R.drawable.like_green, comments.get(i), date.get(i), time.get(i)));
                    }
                    else
                        items.add(new ListItem(R.drawable.like_red_invertido, comments.get(i), date.get(i), time.get(i)));
                }

                listView = findViewById(R.id.list);
                CustomAdapter customAdapter = new CustomAdapter(ReportEvaluations.this,R.layout.evaluation_list_item,items);
                listView.setAdapter(customAdapter);
            }
        }, reportID);
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
