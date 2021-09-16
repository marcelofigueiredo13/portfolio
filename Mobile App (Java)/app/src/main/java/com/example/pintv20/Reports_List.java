package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Reports_List extends AppCompatActivity {

    private ListView listView;
    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports__list);

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);


        /**
         * Obter um array com as avaliacoes do utilizador
         * Futuramente enviar o id do utilizador e receber apenas as avaliacoes dele
         */
        db.getUserReports(Reports_List.this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                ArrayList<String> data = (ArrayList<String>) response;

                ArrayList<String> date = new ArrayList<>();
                ArrayList<String> time = new ArrayList<>();
                ArrayList<Integer> level = new ArrayList<>();
                ArrayList<String> local = new ArrayList<>();

                System.out.println(data);

                for (int i = 0 ; i < data.size() ; i += 4) {
                    date.add(data.get(i));
                }

                for (int i = 1 ; i < data.size() ; i += 4) {
                    time.add(data.get(i));
                }

                for (int i = 2 ; i < data.size() ; i += 4) {
                    level.add(Integer.parseInt(data.get(i)));
                }

                for (int i = 3 ; i < data.size() ; i += 4) {
                    local.add(data.get(i));
                }

                //System.out.println("COMMENTS = " + comments);
                //System.out.println("LIKES = " + likes);

                ArrayList<ListItemReports> items = new ArrayList<>();

                for(int i = 0; i < date.size(); ++i){
                    items.add(new ListItemReports(date.get(i), time.get(i), level.get(i), local.get(i)));
                    System.out.println("Report " + i + "\n" + local.get(i) + " | " + date.get(i) + " | " + time.get(i) + " | " + level.get(i));
                }

                System.out.println("Array List Item Reports \n" + items);

                listView = findViewById(R.id.list);
                CustomAdapterReports customAdapter = new CustomAdapterReports(Reports_List.this,R.layout.report_list_item,items);
                listView.setAdapter(customAdapter);
            }
        });
    }

    /**
     * Tem a haver com o botao de voltar atras na toolbar
     */
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