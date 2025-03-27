package com.example.applicationmobileairvip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class VolListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolAdapter volAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String departDate = intent.getStringExtra("departDate");

        fetchVolsFromApi(from, to);
    }

    private void fetchVolsFromApi(String from, String to) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                String apiUrl = "https://ton-api.azurewebsites.net/api/vols?from=" + from + "&to=" + to; // à adapter

                Request request = new Request.Builder()
                        .url(apiUrl)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful() && response.body() != null) {
                    String json = response.body().string();
                    ObjectMapper mapper = new ObjectMapper();
                    List<Vol> vols = mapper.readValue(json, new TypeReference<List<Vol>>() {});

                    runOnUiThread(() -> {
                        if (vols.isEmpty()) {
                            Toast.makeText(this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
                        }
                        volAdapter = new VolAdapter(vols);
                        recyclerView.setAdapter(volAdapter);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Erreur de chargement des vols", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}