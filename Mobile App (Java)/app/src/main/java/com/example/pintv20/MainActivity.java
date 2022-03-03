package com.example.pintv20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database db = new Database();
    private Button button;

    private EditText email;
    private EditText pw;

    private Integer LOCATION_REQUEST_CODE = 1;
    private Boolean LocationPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocationPermissions();

        /**
         * Login
         */
        button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LocationPermission/*ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED*/){
                    email = (EditText) findViewById(R.id.editTextEmailAddress);
                    pw = (EditText) findViewById(R.id.editTextPassword);
                    Log.i("email", email.getText().toString());
                    Log.i("PASSWORD", pw.getText().toString());
                    final Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    db.Login(MainActivity.this, email.getText().toString(), pw.getText().toString(), new Database.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            System.out.println(message);
                        }
                        @Override
                        public void onResponse(Object response) {
                            System.out.println("Resposta = " + response.toString());
                            if(response.equals(true)){
                                db.getUserId(MainActivity.this, email.getText().toString());
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(MainActivity.this, "AUTENTICAÇÃO FALHOU", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 23);
                }
            }
        });

        button = (Button) findViewById(R.id.registerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }

    private void getLocationPermissions(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                //Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                LocationPermission = true;
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                //Toast.makeText(MainActivity.this, "Permissão Recusada\n"/* + deniedPermissions.toString()*/, Toast.LENGTH_SHORT).show();
                LocationPermission = false;
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setGotoSettingButton(false)
                .setDeniedTitle("LOCALIZAÇÃO")
                .setDeniedMessage("TEM QUE ATIVAR A LOCALIZAÇÃO PARA UTILIZAR A APLICAÇÃO!")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .setDeniedCloseButtonText("OK")
                .check();
    }
}
