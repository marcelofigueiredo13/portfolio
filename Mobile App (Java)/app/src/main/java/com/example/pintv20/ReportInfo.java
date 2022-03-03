package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportInfo extends AppCompatActivity {

    private String value;
    private Integer reportID;
    private Integer reportLevel = 0;

    private String date;
    private String time;
    private String level;

    private TextView tv;
    private Button button;
    private ImageView iv;
    private Database db = new Database();

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_info);

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
            value = extras.getString("reportID");
            reportID = Integer.valueOf(value);
        }
        //Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

        db.getReportInfo(this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                System.out.println(response);
                ArrayList<String> data = (ArrayList<String>) response;

                reportLevel = Integer.parseInt(data.get(0));
                date = data.get(1);
                time = data.get(2);

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
                }
                if(reportLevel == 3){
                    tv.setText("Extremamente Populado");
                    tv.setBackgroundResource(R.drawable.report_dialog_red);
                }

                /**
                 * Mostra a data numa textView
                 */
                tv = (TextView) findViewById(R.id.date);
                tv.setText(date);

                /**
                 * Mostra o tempo numa textView
                 */
                tv = (TextView) findViewById(R.id.time);
                //System.out.println(db.getReportTime(reportID));
                tv.setText(time);

                tv = (TextView) findViewById(R.id.like_white_shape);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReportInfo.this, ReportEvaluations.class);
                        intent.putExtra("reportID", reportID);
                        System.out.println("ReportInfo: Report sent: " + reportID);
                        getReportEvaluations();
                        startActivity(intent);
                    }
                });

                iv = (ImageView) findViewById(R.id.likeGreen);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReportInfo.this, ReportEvaluations.class);
                        intent.putExtra("reportID", reportID);
                        System.out.println("ReportInfo: Report sent: " + reportID);
                        getReportEvaluations();
                        startActivity(intent);
                    }
                });

                iv = (ImageView) findViewById(R.id.likeRed);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReportInfo.this, ReportEvaluations.class);
                        intent.putExtra("reportID", reportID);
                        System.out.println("ReportInfo: Report sent: " + reportID);
                        getReportEvaluations();
                        startActivity(intent);
                    }
                });

                button = (Button) findViewById(R.id.commentButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(ReportInfo.this, "CLICK", Toast.LENGTH_SHORT).show();
                        showDialog();
                    }
                });

                getReportLikes();
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

    /**
     * Dialog para avaliar um report, tem click listeners para ir mudando o like
     */
    private void showDialog(){
        //Toast.makeText(MapsActivity.this, "DIALOG !!!!", Toast.LENGTH_LONG).show();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReportInfo.this);
        View mView = getLayoutInflater().inflate(R.layout.evaluation_dialog, null);

        final EditText comment = (EditText) mView.findViewById(R.id.local);
        ImageButton ibY = (ImageButton) mView.findViewById(R.id.yesButton);
        ImageButton ibN = (ImageButton) mView.findViewById(R.id.noButton);

        ibY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReportInfo.this, "AVALIAÇÃO CONCLUÍDA", Toast.LENGTH_SHORT).show();
                //db.Evaluate(reportID, comment.getText().toString(), true);
                db.evaluateReport(ReportInfo.this, reportID, comment.getText().toString(), true);
                alertDialog.dismiss();
            }
        });

        ibN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReportInfo.this, "AVALIAÇÃO CONCLUÍDA", Toast.LENGTH_SHORT).show();
                //db.Evaluate(reportID, comment.getText().toString(), false);
                db.evaluateReport(ReportInfo.this, reportID, comment.getText().toString(), false);
                alertDialog.dismiss();
            }
        });

        mBuilder.setView(mView);
        alertDialog = mBuilder.create();
        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getReportLikes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReportLikes();
    }

    void getReportLikes(){
        db.getReportLikes(ReportInfo.this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                ArrayList<Integer> likes = (ArrayList<Integer>) response;

                tv = (TextView) findViewById(R.id.likes);
                tv.setText(String.valueOf(likes.get(0)));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReportInfo.this, ReportEvaluations.class);
                        intent.putExtra("reportID", reportID);
                        System.out.println("ReportInfo: Report sent: " + reportID);
                        startActivity(intent);
                    }
                });

                tv = (TextView) findViewById(R.id.dislikes);
                tv.setText(String.valueOf(likes.get(1)));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReportInfo.this, ReportEvaluations.class);
                        intent.putExtra("reportID", reportID);
                        System.out.println("ReportInfo: Report sent: " + reportID);
                        startActivity(intent);
                    }
                });
            }
        }, reportID);
    }

    void getReportEvaluations(){
        db.getReportEvaluations(ReportInfo.this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                System.out.println(response);
            }
        }, reportID);
    }
}
