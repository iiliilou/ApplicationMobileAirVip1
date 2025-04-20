package com.example.applicationmobileairvip.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.adapter.VolAdapter;
import com.example.applicationmobileairvip.api.ApiClient;
import com.example.applicationmobileairvip.model.Reservation;
import com.example.applicationmobileairvip.model.Vol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class TripsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTrips;
    private VolAdapter volAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        recyclerViewTrips = findViewById(R.id.recyclerViewTrips);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(this));

        // Barre de navigation inférieure
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_trips);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_status) {
                startActivity(new Intent(this, VolListActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
            return false;
        });

        // Charger les réservations de l'utilisateur
        loadReservationsFromApi();
    }

    private void loadReservationsFromApi() {
        SharedPreferences prefs = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show();
            return;
        }

        String endpoint = "/reservations/utilisateur/" + userId;

        ApiClient.get(endpoint, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    List<Reservation> reservations = mapper.readValue(response, new TypeReference<List<Reservation>>() {});
                    List<Vol> vols = new ArrayList<>();

                    for (Reservation reservation : reservations) {
                        Vol vol = reservation.getVol();
                        if (vol != null) {
                            vols.add(vol);
                        }
                    }

                    runOnUiThread(() -> {
                        if (vols.isEmpty()) {
                            Toast.makeText(TripsActivity.this, "Aucune réservation trouvée", Toast.LENGTH_SHORT).show();
                        }
                        volAdapter = new VolAdapter(vols, vol -> {
                            Intent intent = new Intent(TripsActivity.this, FlightStatusActivity.class);
                            intent.putExtra("vol_id", vol.getVolId());
                            startActivity(intent);
                        });
                        recyclerViewTrips.setAdapter(volAdapter);
                    });

                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(TripsActivity.this, "Erreur JSON : " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(TripsActivity.this, "Erreur API : " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }
}
