package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        // Liaison des vues
        tvNumeroVol = findViewById(R.id.numeroVol);
        tvDepart = findViewById(R.id.aeroportDepart);
        tvArrivee = findViewById(R.id.aeroportArrivee);
        tvPrix = findViewById(R.id.textPrix);
        btnReserver = findViewById(R.id.btn_reserver);

        findViewById(R.id.btn_retour).setOnClickListener(v -> finish());

        //  Récupération du vol_id
        int volId = getIntent().getIntExtra("vol_id", -1);
        Log.d("DEBUG_VOL_ID", "Reçu dans FlightStatusActivity : vol_id = " + volId);

        if (volId != -1) {
            chargerStatutVol(volId);
        } else {
            Toast.makeText(this, "Aucun ID de vol reçu", Toast.LENGTH_SHORT).show();
        }

        //  Bouton Réserver
        btnReserver.setOnClickListener(v -> {
            if (currentVol != null) {
                Intent intent = new Intent(FlightStatusActivity.this, ConfirmationActivity.class);
                intent.putExtra("vol_id", currentVol.getVolId());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Vol non chargé", Toast.LENGTH_SHORT).show();
            }
        });

        //  Barre de navigation inférieure
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_status) {
                // ne rien faire : on est déjà ici
                return true;
            } else if (id == R.id.nav_reserver) {
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
    }

    private void chargerStatutVol(int volId) {
        ApiClient.get("/vols/" + volId, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    currentVol = mapper.readValue(response, Vol.class);

                    runOnUiThread(() -> {
                        tvNumeroVol.setText("Vol #" + currentVol.getVolId());
                        tvDepart.setText("Départ : " + currentVol.getAeroportDepart().getCodeIATA());
                        tvArrivee.setText("Arrivée : " + currentVol.getAeroportArrive().getCodeIATA());
                        tvPrix.setText("Prix : " + currentVol.getPrix() + " $CA");
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(FlightStatusActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(FlightStatusActivity.this, "Erreur API : " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
