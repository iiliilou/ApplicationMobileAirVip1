package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.model.Vol;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FlightStatusActivity extends AppCompatActivity {

    private TextView tvNumeroVol, tvHeureDecollage, tvDateVol, tvDepart, tvArrivee, tvStatut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status);

        // Lier les vues
        tvNumeroVol = findViewById(R.id.numeroVol);
        tvHeureDecollage = findViewById(R.id.heureDecollage);
        tvDateVol = findViewById(R.id.dateVol);
        tvDepart = findViewById(R.id.aeroportDepart);
        tvArrivee = findViewById(R.id.aeroportArrivee);
        tvStatut = findViewById(R.id.retardStatut);

        // Navigation inférieure
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_status);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, CompteActivity.class));
                return true;
            }
            return false;
        });

        // Récupère l’ID du vol envoyé depuis une autre activité
        int volId = getIntent().getIntExtra("vol_id", -1);
        if (volId != -1) {
            chargerStatutVol(volId);
        } else {
            Toast.makeText(this, "Aucun ID de vol reçu", Toast.LENGTH_SHORT).show();
        }
    }

    private void chargerStatutVol(int volId) {
        ApiClient.get("/vols/" + volId, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Vol vol = mapper.readValue(response, Vol.class);

                    runOnUiThread(() -> {
                        tvNumeroVol.setText("Vol #" + vol.getVolId());
                        tvHeureDecollage.setText("Heure de décollage : --:--"); // peut être mise à jour plus tard
                        tvDateVol.setText("Durée : " + vol.getTemps() + " h");
                        tvDepart.setText("Départ : " + vol.getAeroportDepart().getCodeIATA());
                        tvArrivee.setText("Arrivée : " + vol.getAeroportArrive().getCodeIATA());

                        // Simuler un statut
                        boolean enRetard = false;
                        if (enRetard) {
                            tvStatut.setText("Statut : Retardé de 45 min");
                            tvStatut.setTextColor(Color.parseColor("#D32F2F"));
                        } else {
                            tvStatut.setText("Statut : À l'heure");
                            tvStatut.setTextColor(Color.parseColor("#4CAF50"));
                        }
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
