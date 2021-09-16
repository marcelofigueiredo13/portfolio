package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private Integer avatar;
    private String username;
    private String email;
    private String phone;
    private Integer points;

    private ImageView iv;
    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etPassword;
    private Button bt;
    private TextView tvAtual;
    private TextView tvProximo;

    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        db.getUserData(Profile.this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Object response) {
                ArrayList<String> data = (ArrayList<String>) response;
                username = data.get(0);
                System.out.println("Name "+data.get(0));
                email = data.get(1);
                System.out.println("Email "+data.get(1));
                phone = data.get(2);
                System.out.println("Phone number "+data.get(2));
                avatar = Integer.valueOf(data.get(3));
                System.out.println("Avatar"+avatar.toString());
                points = Integer.valueOf(data.get(4));
                System.out.println("Points" + points);

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

                tvAtual = (TextView) findViewById(R.id.atual);
                tvAtual.setText(String.valueOf(points));

                tvProximo = (TextView) findViewById(R.id.proximo);

                System.out.println("PONTOS = " + points);
                iv = (ImageView) findViewById(R.id.avatar);
                if(points >= 50){
                    System.out.println("NIVEL 3");
                    iv.setImageResource(R.drawable.avatar_3);
                    progressBar.setMax(100);
                    progressBar.setMin(50);
                    progressBar.setProgress(points);
                    tvProximo.setText(String.valueOf(100));
                }
                else{
                    System.out.println("NIVEL 1 OU 2");
                    if(points >= 25){
                        System.out.println("NIVEL 2");
                        iv.setImageResource(R.drawable.avatar_2);
                        progressBar.setMax(50);
                        progressBar.setMin(25);
                        progressBar.setProgress(points);
                        tvProximo.setText(String.valueOf(50));
                    }
                    else{
                        System.out.println("NIVEL 1");
                        iv.setImageResource(R.drawable.avatar_1);
                        progressBar.setMax(25);
                        progressBar.setMin(0);
                        progressBar.setProgress(points);
                        tvProximo.setText(String.valueOf(25));
                    }
                }

                etName = (EditText) findViewById(R.id.name);
                etName.setText(username);

                etEmail = (EditText) findViewById(R.id.email);
                etEmail.setText(email);

                etPhone = (EditText) findViewById(R.id.phoneNumber);
                etPhone.setText(phone);

                etPassword = (EditText) findViewById(R.id.password);
                etPassword.setText(db.getCurrentUserPassword());

                bt = (Button) findViewById(R.id.confirmButton);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!etName.getText().toString().equals(db.getCurrentUserName()) || !etPhone.getText().toString().equals(db.getCurrentUserPhone()) || !etEmail.getText().toString().equals(db.getCurrentUserEmail()) || !etPassword.getText().toString().equals(db.getCurrentUserPassword())){
                            Toast.makeText(Profile.this, "ALTERAÇÕES GUARDADAS", Toast.LENGTH_SHORT).show();
                            db.updateProfile(Profile.this, etName.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), Integer.parseInt(etPhone.getText().toString()));
                        }
                        else
                            Toast.makeText(Profile.this, "NÃO HOUVE ALTERAÇÕES", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        /*
        username = db.getUserName(db.getCurrentUser());
        email = db.getUserEmail(db.getCurrentUser());
        phone = db.getUserPhone(db.getCurrentUser());*/
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