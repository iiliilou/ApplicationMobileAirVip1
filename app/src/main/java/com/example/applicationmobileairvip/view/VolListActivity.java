package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.model.Vol;
import com.example.applicationmobileairvip.adapter.VolAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolAdapter volAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadVols();

        // Barre de navigation
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
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            return false;
        });
    }

    private void loadVols() {
        ApiClient.get("/vols", new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Vol> vols = objectMapper.readValue(response,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Vol.class));

                    // On récupère les IATA passés par HomeActivity
                    String departIata = getIntent().getStringExtra("DEPART_IATA");
                    String arriveeIata = getIntent().getStringExtra("ARRIVEE_IATA");

                    if (departIata != null && arriveeIata != null &&
                            !departIata.isEmpty() && !arriveeIata.isEmpty()) {

                        List<Vol> volsFiltres = new ArrayList<>();
                        for (Vol vol : vols) {
                            if (vol.getAeroportDepart() != null && vol.getAeroportArrive() != null &&
                                    departIata.equalsIgnoreCase(vol.getAeroportDepart().getCodeIATA()) &&
                                    arriveeIata.equalsIgnoreCase(vol.getAeroportArrive().getCodeIATA())) {
                                volsFiltres.add(vol);
                            }
                        }
                        vols = volsFiltres;
                    }

                    List<Vol> finalVols = vols;
                    runOnUiThread(() -> {
                        volAdapter = new VolAdapter(finalVols, vol -> reserverVol(vol.getVolId()));
                        recyclerView.setAdapter(volAdapter);
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(VolListActivity.this, "Erreur de traitement : " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(VolListActivity.this, "Erreur de chargement : " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        });
    }

    private void reserverVol(int volId) {
        try {
            SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
            int userId = prefs.getInt("user_id", -1);

            if (userId == -1) {
                Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject reservationData = new JSONObject();
            reservationData.put("vol_id", volId);
            reservationData.put("user_id", userId);

            ApiClient.post("/reservations", reservationData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    runOnUiThread(() ->
                            Toast.makeText(VolListActivity.this, "Réservation effectuée avec succès !", Toast.LENGTH_SHORT).show()
                    );
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(VolListActivity.this, "Erreur lors de la réservation : " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
