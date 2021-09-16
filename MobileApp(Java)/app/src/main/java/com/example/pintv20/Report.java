package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Report extends AppCompatActivity {

    private Integer reportLevel;
    private String value;
    private TextView tv;
    private Button button;
    private Double latitude;
    private Double longitude;
    private Database db = new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**
         * Obtem o nivel, latitude e longitude da localizacao atual do utilizador enviados pelo intent
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("level");
            reportLevel = Integer.valueOf(value);
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
        }
        System.out.println("Report: Latitude " + latitude + " Longitude " + longitude);

        /**
         * Dependendo do nivel escolhido no dialog, o background do nivel muda de cor e de texto
         */
        tv = (TextView) findViewById(R.id.nivel);

        if(reportLevel == 1){
            tv.setText("Pouco Populado");
            tv.setBackgroundResource(R.drawable.report_dialog_green);
        }
        if(reportLevel == 2){
            tv.setText("Muito Populado");
            tv.setBackgroundResource(R.drawable.report_dialog_orange);
        }if(reportLevel == 3){
            tv.setText("Extremamente Populado");
            tv.setBackgroundResource(R.drawable.report_dialog_red);
        }

        /**
         * Mostra a data numa textView
         */
        tv = (TextView) findViewById(R.id.date);
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");//formating according to my need
        final String date = formatter.format(today);
        tv.setText(date);

        /**
         * Mostra o tempo numa textView
         */
        tv = (TextView) findViewById(R.id.time);
        final Time time = new Time(Calendar.getInstance().getTimeInMillis());
        Calendar c = Calendar.getInstance();
        int min = c.get(Calendar.MINUTE);
        int hour=c.get(Calendar.HOUR);
        //tv.setText(String.valueOf(hour) + ":" +String.valueOf(min));
        //LocalDateTime.now().getHour();
        tv.setText(time.toString());

        /**
         * Efetuar o report, enviar os dados para a base de dados e mostrar um toast
         * e volta ao mapa
         */
        button = (Button) findViewById(R.id.commentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(db.Report(time.toString(), date, reportLevel, latitude,longitude) == true)
                    Toast.makeText(Report.this, "REPORT EFETUADO", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Report.this, "REPORT FALHADO", Toast.LENGTH_SHORT).show();*/
                db.reportLocation(Report.this, reportLevel, latitude, longitude);
                Toast.makeText(Report.this, "REPORT EFETUADO", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Report.this, MapsActivity.class);
                startActivity(intent);
                /**
                 * Se utilizar finish, o mapa nao atualiza entao se comecar a atividade de novo, o mapa da reset e vai buscar o novo pino ao hashmap*/
                //finish();
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