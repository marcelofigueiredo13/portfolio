package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    private Database db = new Database();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        db.getRanking(this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                ArrayList<String> data = (ArrayList<String>) response;

                ArrayList<String> name = new ArrayList<>();
                ArrayList<Integer> points = new ArrayList<>();
                ArrayList<Integer> avatar = new ArrayList<>();

                for (int i = 0 ; i < data.size() ; i += 3) {
                    name.add(data.get(i));
                }

                for (int i = 1 ; i < data.size() ; i += 3) {
                    points.add(Integer.valueOf(data.get(i)));
                }

                for (int i = 2 ; i < data.size() ; i += 3) {
                    avatar.add(Integer.parseInt(data.get(i)));
                }

                ArrayList<ListItemRanking> items = new ArrayList<>();

                for(int i = 0; i < name.size(); ++i){
                    items.add(new ListItemRanking(avatar.get(i), name.get(i), points.get(i)));
                    System.out.println("User " + i + "\n" + name.get(i) + " | " + points.get(i) + " | " + avatar.get(i));
                }

                listView = findViewById(R.id.list);
                CustomAdapterRanking customAdapter = new CustomAdapterRanking(Ranking.this,R.layout.ranking_list_item,items);
                listView.setAdapter(customAdapter);
            }
        });
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
