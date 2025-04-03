package com.example.applicationmobileairvip;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        loadVols();
    }

    private void loadVols() {
        ApiClient.get("/vols", new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    // Conversion de la réponse JSON en objets Vol
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Vol> vols = objectMapper.readValue(response, 
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Vol.class));
                    
                    runOnUiThread(() -> {
                        volAdapter = new VolAdapter(vols);
                        recyclerView.setAdapter(volAdapter);
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> 
                        Toast.makeText(VolListActivity.this, "Erreur de traitement: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                    Toast.makeText(VolListActivity.this, "Erreur de chargement: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        });
    }

    private void registerUser(String nom, String prenom, String email, String motdepasse) {
        try {
            JSONObject userData = new JSONObject();
            userData.put("nom", nom);
            userData.put("prenom", prenom);
            userData.put("adresse_courriel", email);
            userData.put("mot_de_passe", motdepasse);

            ApiClient.post("/utilisateurs", userData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    runOnUiThread(() -> {
                        Toast.makeText(VolListActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                        // Ne pas appeler finish() ici, car ce n'est pas RegisterActivity
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> 
                        Toast.makeText(VolListActivity.this, "Erreur lors de l'inscription: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void searchVols(String depart, String arrivee) {
        String searchEndpoint = "/vols/search?depart=" + depart + "&arrivee=" + arrivee;
        
        ApiClient.get(searchEndpoint, new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Vol> vols = objectMapper.readValue(response, 
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Vol.class));
                    
                    runOnUiThread(() -> {
                        volAdapter = new VolAdapter(vols);
                        recyclerView.setAdapter(volAdapter);
                        
                        if (vols.isEmpty()) {
                            Toast.makeText(VolListActivity.this, "Aucun vol trouvé", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> 
                        Toast.makeText(VolListActivity.this, "Erreur de traitement: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() ->
                    Toast.makeText(VolListActivity.this, "Erreur de recherche: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        });
    }

    private void reserverVol(int volId, int userId) {
        try {
            JSONObject reservationData = new JSONObject();
            reservationData.put("vol_id", volId);
            reservationData.put("user_id", userId);

            ApiClient.post("/reservations", reservationData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Réservation effectuée avec succès !", Toast.LENGTH_SHORT).show();
                        // Naviguer vers l'écran de confirmation ou historique des réservations
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> 
                        Toast.makeText(getApplicationContext(), "Erreur lors de la réservation: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUserPreferences(int userId, JSONObject preferencesData) {
        try {
            ApiClient.put("/utilisateurs/" + userId + "/preferences", preferencesData, new ApiClient.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Préférences mises à jour avec succès !", Toast.LENGTH_SHORT).show();
                    });
                }

                @Override
                public void onError(Exception e) {
                    runOnUiThread(() -> 
                        Toast.makeText(getApplicationContext(), "Erreur lors de la mise à jour des préférences: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                    );
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
