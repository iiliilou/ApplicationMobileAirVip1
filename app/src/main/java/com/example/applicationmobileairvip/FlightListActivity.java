package com.example.applicationmobileairvip;


import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applicationmobileairvip.database.AppDatabase;
import com.example.applicationmobileairvip.database.entities.Flight;
import java.util.List;

public class FlightListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlightAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);


    //on recup les parametres

        String departure = getIntent().getStringExtra("DEPARTURE");
        String arrival = getIntent().getStringExtra("ARRIVAL");
        String date= getIntent().getStringExtra("DATE");

        recyclerView = findViewById(R.id.flightRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //charement depuis la Base de donnee
        new LoadFlightTask().execute(departure, arrival, date);
    }



    // Correction de la classe LoadFlightTask
    private class LoadFlightTask extends AsyncTask<String, Void, List<Flight>> {
        @Override
        protected List<Flight> doInBackground(String... params) {
            return AppDatabase.getInstance(FlightListActivity.this)
                    .flightDao()
                    .searchFlights(params[0], params[1], params[2]); // 3 paramètres
        }

        @Override
        protected void onPostExecute(List<Flight> flights) { // Nom corrigé
            adapter = new FlightAdapter(flights); // Variable minuscule
            recyclerView.setAdapter(adapter);
        }
    }


}
