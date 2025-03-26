package com.example.applicationmobileairvip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class FlightListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightAdapter flightAdapter;

    // Critères de recherche reçus
    private String fromLocation;
    private String toLocation;
    private String departDate;
    private String returnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        // Récupération des critères de recherche depuis l'Intent
        Intent intent = getIntent();
        fromLocation = intent.getStringExtra("from");
        toLocation = intent.getStringExtra("to");
        departDate = intent.getStringExtra("departDate");
        returnDate = intent.getStringExtra("returnDate");

        // Initialisation du RecyclerView et de son adapter
        recyclerView = findViewById(R.id.recyclerViewFlights);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightAdapter = new FlightAdapter();  // liste vide initialement
        recyclerView.setAdapter(flightAdapter);

        // Chargement des données de vols en arrière-plan
        new LoadFlightsTask().execute();
    }

    /** Tâche asynchrone interne pour charger les vols depuis la base de données selon les critères */
    private class LoadFlightsTask extends AsyncTask<Void, Void, List<Flight>> {
        @Override
        protected List<Flight> doInBackground(Void... voids) {
            // Obtention de l'instance de la base de données
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "AirVipDB")
                    .fallbackToDestructiveMigration()
                    .build();
            FlightDao flightDao = db.flightDao();
            // Si la base est vide, insérer des données fictives de vol
            if (flightDao.countFlights() == 0) {
                // Création de quelques vols fictifs (données pré-remplies)
                Flight vol1 = new Flight("Paris", "Londres", "1/8/2025 09:00", "1/8/2025 09:55", "Cessna Citation X", 8, 5000, "Wi-Fi, Champagne");
                Flight vol2 = new Flight("Paris", "New York", "1/8/2025 08:00", "1/8/2025 11:00", "Falcon 8X", 12, 12000, "Wi-Fi, Repas gourmet");
                Flight vol3 = new Flight("Londres", "Paris", "5/8/2025 16:00", "5/8/2025 18:00", "Cessna Citation X", 8, 5200, "Wi-Fi, Champagne");
                Flight vol4 = new Flight("New York", "Los Angeles", "5/7/2025 07:00", "5/7/2025 10:00", "Learjet 75", 9, 7000, "Wi-Fi, Bar");
                Flight vol5 = new Flight("Toronto", "Paris", "30/7/2025 15:00", "31/7/2025 05:00", "Bombardier Global 7500", 14, 15000, "Wi-Fi, Chef à bord");
                Flight vol6 = new Flight("Londres", "Dubai", "10/8/2025 10:00", "10/8/2025 18:00", "Gulfstream G650", 12, 10000, "Wi-Fi, Accès lounge");
                // Insérer les vols dans la base
                flightDao.insertFlights(vol1, vol2, vol3, vol4, vol5, vol6);
            }
            // Requête des vols correspondant aux critères de recherche
            List<Flight> results = flightDao.searchFlights(fromLocation, toLocation, departDate);
            // Si un retour est spécifié, récupérer aussi les vols correspondants (trajet inverse à la date de retour)
            if (returnDate != null && !returnDate.isEmpty()) {
                List<Flight> retourResults = flightDao.searchFlights(toLocation, fromLocation, returnDate);
                results.addAll(retourResults);
            }
            return results;
        }

        @Override
        protected void onPostExecute(List<Flight> flights) {
            // Mise à jour de l'adaptateur avec les données récupérées
            flightAdapter.setFlights(flights);
            flightAdapter.notifyDataSetChanged();
        }
    }
}
