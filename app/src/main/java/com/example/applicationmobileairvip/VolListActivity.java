package com.example.applicationmobileairvip;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VolListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolAdapter volAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Récupération des vols directement de l'API REST
        new Thread(() -> {
            List<Vol> vols = VolApiClient.getInstance().getVols();
            runOnUiThread(() -> {
                if (vols == null || vols.isEmpty()) {
                    Toast.makeText(this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    VolAdapter adapter = new VolAdapter(vols);
                    recyclerView.setAdapter(adapter);
                }
            });
        }).start();
    }
}



/*
public class VolListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolAdapter volAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        // Simulation de récupération des vols
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String departDate = intent.getStringExtra("departDate");

        List<Vol> vols = VolApiClient.getInstance().getVols();
        if (vols.isEmpty()) {
            Toast.makeText(this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
        }
        volAdapter = new VolAdapter(vols);
        recyclerView.setAdapter(volAdapter);
    }
}

*/
/**
 * new Thread(() -> {
 *     List<Vol> vols = VolApiClient.getInstance().getVols();
 *     runOnUiThread(() -> {
 *         if (vols.isEmpty()) {
 *             Toast.makeText(this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
 *         }
 *         VolAdapter adapter = new VolAdapter(vols);
 *         recyclerView.setAdapter(adapter);
 *     });
 * }).start();
 *
 * */
