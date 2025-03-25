package com.example.applicationmobileairvip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private TextView fromText, toText;
    private EditText departureDateEditText, returnDateEditText;
    private TextView typeVols;
    private View layoutRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Récupération des vues
        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        departureDateEditText = findViewById(R.id.editDateDepart);
        returnDateEditText = findViewById(R.id.editDateRetour);
        typeVols = findViewById(R.id.typeVols); // ton TextView cliquable
        layoutRetour = findViewById(R.id.layoutRetour); // parent du champ "date de retour"
        Button btnRechercher = findViewById(R.id.btnRechercher);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Sélection du type de vol
        typeVols.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, typeVols);
            popupMenu.getMenu().add("Aller-retour");
            popupMenu.getMenu().add("Aller simple");
            popupMenu.getMenu().add("Multi-voyage");

            popupMenu.setOnMenuItemClickListener(item -> {
                String selected = Objects.requireNonNull(item.getTitle()).toString();
                typeVols.setText(selected);

                // Affiche ou masque la date de retour
                if (selected.equals("Aller simple")) {
                    layoutRetour.setVisibility(View.GONE);
                } else {
                    layoutRetour.setVisibility(View.VISIBLE);
                }

                return true;
            });

            popupMenu.show();
        });

        // Bouton de recherche
        btnRechercher.setOnClickListener(v -> {
            String from = fromText.getText().toString().trim();
            String to = toText.getText().toString().trim();
            String depart = departureDateEditText.getText().toString().trim();
            String retour = returnDateEditText.getText().toString().trim();

            String type = typeVols.getText().toString();
            String result = "Recherche : " + from + " ➡ " + to + "\nDépart : " + depart;

            if (!type.equals("Aller simple")) {
                result += " | Retour : " + retour;
            }

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        });

        // Navigation du bas
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_reserver) {
                Toast.makeText(this, "Réserver", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_status) {
                Toast.makeText(this, "Statut vol", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_trips) {
                Toast.makeText(this, "Voyages", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });
    }
}


