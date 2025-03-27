package com.example.applicationmobileairvip;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VolListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolAdapter volAdapter;

    //recuprere les donne from , to , departDate
    //appel fonction pour recuprerer les vols via http
    //met a jour le recyclerView
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_list);

        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Appel à l'API REST
        VolApiClient.getVols(new VolApiClient.VolCallback() {
            @Override
            public void onSuccess(List<Vol> vols) {
                runOnUiThread(() -> {
                    volAdapter = new VolAdapter(vols);
                    recyclerView.setAdapter(volAdapter);
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(VolListActivity.this, "Erreur de chargement: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        });

    }
}
