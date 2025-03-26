package com.example.applicationmobileairvip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightAdapter flightAdapter;
    private FlightDao flightDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String departDate = intent.getStringExtra("departDate");

        flightDao = FlightDatabase.getInstance(getApplicationContext()).flightDao();

        new Thread(() -> {
            List<Flight> flights = flightDao.findFlights(from, to, departDate);
            runOnUiThread(() -> {
                if (flights.isEmpty()) {
                    Toast.makeText(this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
                }
                flightAdapter = new FlightAdapter(flights);
                recyclerView.setAdapter(flightAdapter);
            });
        }).start();
    }
}
