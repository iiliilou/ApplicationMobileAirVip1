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
                        volAdapter = new VolAdapter(finalVols, vol -> {
                            Intent intent = new Intent(VolListActivity.this, FlightStatusActivity.class); // ✅ et non ConfirmationActivity
                            intent.putExtra("vol_id", vol.getVolId()); // 🔥 clé correcte
                            startActivity(intent);
                        });

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
}
