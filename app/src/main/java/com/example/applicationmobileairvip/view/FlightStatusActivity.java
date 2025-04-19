package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.model.Vol;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FlightStatusActivity extends AppCompatActivity {

    private TextView tvNumeroVol, tvDepart, tvArrivee, tvPrix;
    private Button btnReserver;
    private Vol currentVol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status);

        tvNumeroVol = findViewById(R.id.numeroVol);
        tvDepart = findViewById(R.id.aeroportDepart);
        tvArrivee = findViewById(R.id.aeroportArrivee);
        tvPrix = findViewById(R.id.textPrix);
        btnReserver = findViewById(R.id.btn_reserver);

        findViewById(R.id.btn_retour).setOnClickListener(v -> finish());

        btnReserver.setOnClickListener(v -> {
            if (currentVol != null) {
                Intent intent = new Intent(FlightStatusActivity.this, ConfirmationActivity.class);
                intent.putExtra("vol_id", currentVol.getVolId());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Vol non chargé", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.nav_status);
        nav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            if (id == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            return false;
        });

        int volId = getIntent().getIntExtra("vol_id", -1);
        if (volId != -1) {
            chargerStatutVol(volId);
        } else {
            Toast.makeText(this, "Aucun vol reçu", Toast.LENGTH_SHORT).show();
        }
    }

    private void chargerStatutVol(int volId) {
        ApiClient.get("/vols/" + volId, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Vol vol = mapper.readValue(response, Vol.class);
                    currentVol = vol;

                    runOnUiThread(() -> {
                        tvNumeroVol.setText("Vol #" + vol.getVolId());
                        tvDepart.setText("Départ : " + vol.getAeroportDepart().getCodeIATA());
                        tvArrivee.setText("Arrivée : " + vol.getAeroportArrive().getCodeIATA());
                        tvPrix.setText("Prix : " + vol.getPrix() + " $CA");
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(FlightStatusActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> Toast.makeText(FlightStatusActivity.this, "Erreur API : " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
