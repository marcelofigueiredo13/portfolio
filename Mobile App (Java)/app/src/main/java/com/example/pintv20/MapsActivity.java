package com.example.pintv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback/*, GoogleMap.OnMarkerClickListener */{

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private LatLng currentLocation;
    private GpsTracker gpsTracker;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;

    private AlertDialog alertDialog;
    private ImageButton ib;

    private Database db = new Database();

    protected SupportMapFragment mapFragment;

    private static LinkedHashMap<Integer, HashMap<LatLng, Integer>> pinsTeste = new LinkedHashMap<>();

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getLocation();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);

        /**
         * Layout da sidebar
         */
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toggle = new ActionBarDrawerToggle(MapsActivity.this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /**
         * Listener do menu da sidebar
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.profile:
                            startActivity(new Intent(MapsActivity.this, Profile.class));
                            break;
                        case R.id.reports:
                            startActivity(new Intent(MapsActivity.this, Reports_List.class));
                            break;
                        case R.id.avaliacoes:
                                startActivity(new Intent(MapsActivity.this, Evaluations.class));
                            break;
                        case R.id.ranking:
                            startActivity(new Intent(MapsActivity.this, Ranking.class));
                            break;
                        case R.id.logout:
                            Toast.makeText(MapsActivity.this, "SESSÃO TERMINADA", Toast.LENGTH_SHORT).show();
                            logOut();
                            break;
                    }
                    return true;
            }
        });

        /**
         * Click listener do botao do +
         */
        ib = (ImageButton) findViewById(R.id.plus);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    /**
     * Atualiza o toogle da sidebar
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        Toggle.syncState();
    }

    /**
     * Simula o logout, comeca a primeira atividade do zero
     */
    public void logOut(){
        startActivity(new Intent(MapsActivity.this, MainActivity.class));
    }

    /**
     * Pedir permissoes de localizacao
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                onMapReady(mMap);
            }
        }
    }

    /**
     * Quando o mapa esta pronto para ser mostrado
     * No HashMap, vai busca-lo à base de dados e depois cria markers consoante o nivel e com a localizacao que obtem
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));

        if(ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            if(currentLocation != null){
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));
            }
        }
        else
        {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        db.getMapPins(MapsActivity.this, new Database.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println(message);
            }

            @Override
            public void onResponse(Object response) {
                System.out.println(response);
                pinsTeste = (LinkedHashMap<Integer, HashMap<LatLng, Integer>>) response;

                for(Integer i: pinsTeste.keySet()){
                    HashMap<LatLng, Integer> data = null;
                    Integer idReport = i;
                    //System.out.println("Report numero: " + idReport);
                    data = pinsTeste.get(i);

                    for(LatLng k: data.keySet()){
                        System.out.println("Coordenadas: " + k.latitude + "," + k.longitude + "\nNivel: " + data.get(k));
                        LatLng marker = new LatLng(k.latitude, k.longitude);
                        mMap.addMarker(new MarkerOptions()
                                .position(marker)
                                .icon(bitmapDescriptor(MapsActivity.this, data.get(k)))
                                .title(String.valueOf(idReport)));
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(@NonNull Marker marker) {
                                Intent intent = new Intent(MapsActivity.this, ReportInfo.class);
                                intent.putExtra("reportID", marker.getTitle());
                                startActivity(intent);
                                return false;
                            }
                        });
                    }
                }
            }
        });

    /**
     * Obtem a localizacao atual do utilizador
     * Guarda na variavel currentLocation
     */
    public void getLocation(){
        gpsTracker = new GpsTracker(MapsActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            //Toast.makeText(MapsActivity.this, "Latitude" + latitude + "\nLongitude" + longitude, Toast.LENGTH_LONG).show();
            currentLocation = new LatLng(latitude,longitude);
        }else{
            Toast.makeText(this, "LOCALIZACAO DESATIVADA!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Dialog para reportar, tem click listeners e envia o nivel do report dependendo do selecionado
     */
    private void showDialog(){
        //Toast.makeText(MapsActivity.this, "DIALOG !!!!", Toast.LENGTH_LONG).show();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MapsActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.report_dialog, null);
        TextView pP = (TextView) mView.findViewById(R.id.pouco_populado);
        TextView mP = (TextView) mView.findViewById(R.id.muito_populado);
        TextView eP = (TextView) mView.findViewById(R.id.extremamente_populado);

        final Intent intent = new Intent(MapsActivity.this, Report.class);
        intent.putExtra("latitude", currentLocation.latitude);
        intent.putExtra("longitude", currentLocation.longitude);
        pP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MapsActivity.this, "Pouco Populado", Toast.LENGTH_SHORT).show();
                intent.putExtra("level", "1");
                startActivity(intent);
            }
        });

        mP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MapsActivity.this, "Muito Populado", Toast.LENGTH_SHORT).show();
                intent.putExtra("level", "2");
                startActivity(intent);
            }
        });

        eP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MapsActivity.this, "Extremamente Populado", Toast.LENGTH_SHORT).show();
                intent.putExtra("level", "3");
                startActivity(intent);
            }
        });

        mBuilder.setView(mView);
        alertDialog = mBuilder.create();
        alertDialog.show();
    }

    /**
     * Devolve um pino, dependendo do nivel do report
     * 1 - pino verde
     * 2 - pino laranja
     * 3 - pino vermelho
     */
    private BitmapDescriptor bitmapDescriptor(Context context, int vectorResId){
        Drawable vectorDrawable = null;
        if(vectorResId == 1){
            vectorDrawable = ContextCompat.getDrawable(context, R.drawable.pin_green);
        }
        if(vectorResId == 2){
            vectorDrawable = ContextCompat.getDrawable(context, R.drawable.pin_orange);
        }
        if(vectorResId == 3){
            vectorDrawable = ContextCompat.getDrawable(context, R.drawable.pin_red);
        }
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    /**
     * Ativa o botao menu e seta para tras da sidebar
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
