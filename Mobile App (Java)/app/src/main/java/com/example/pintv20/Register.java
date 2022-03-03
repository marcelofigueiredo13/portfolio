package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhone;

    private Button bt;

    private String name;
    private String email;
    private String password;
    private String telefone;
    private Integer phone;

    private Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * Botao de voltar para tras na action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etNome = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etPhone = (EditText) findViewById(R.id.telefone);

        bt = (Button) findViewById(R.id.confirmButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer phone;
                name = etNome.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                telefone = etPhone.getText().toString();
                if(telefone.equals(""))
                    phone = 0;
                else
                    phone = Integer.valueOf(telefone);

                System.out.println("USER DATA\n" + name + " " + email + " " + password + " " + phone);
                if(name.equals("") || email.equals("") || password.equals("") || phone == 0)
                    Toast.makeText(Register.this, "PREECHA OS CAMPOS", Toast.LENGTH_SHORT).show();
                else{
                    db.registerUser(Register.this, new Database.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            System.out.println(message);
                        }

                        @Override
                        public void onResponse(Object response) {
                            Boolean register = (Boolean) response;
                            if(register) {
                                Toast.makeText(Register.this, "REGISTO EFETUADO", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                            else
                                Toast.makeText(Register.this, "REGISTO FALHOU", Toast.LENGTH_SHORT).show();
                        }
                    }, name, email, phone, password);
                }
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
