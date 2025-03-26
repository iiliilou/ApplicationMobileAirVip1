package com.example.applicationmobileairvip;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private EditText fromText, toText;
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
        typeVols = findViewById(R.id.typeVols);
        layoutRetour = findViewById(R.id.layoutRetour);
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
                layoutRetour.setVisibility(selected.equals("Aller simple") ? View.GONE : View.VISIBLE);
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

            // Vérification rapide des champs requis   ajout rayan
            if (from.isEmpty() || to.isEmpty() || depart.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir les champs de départ, d'arrivée et de date", Toast.LENGTH_SHORT).show();
                return;
            }
            // fin ajout rayan

            String type = typeVols.getText().toString();
            String result = "Recherche : " + from + " ➡ " + to + "\nDépart : " + depart;

            if (!type.equals("Aller simple")) {
                result += " | Retour : " + retour;
            }

            Toast.makeText(this, result, Toast.LENGTH_LONG).show();


            // Création de l'intent pour démarrer FlightListActivity    ajout rayan
            Intent intent = new Intent(this, FlightListActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("to", to);
            intent.putExtra("departDate", depart);
            intent.putExtra("returnDate", retour);
            startActivity(intent);    // fin ajout rayan
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
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

        // Gestion du calendrier au clic
        departureDateEditText.setOnClickListener(v -> showDatePickerDialog(departureDateEditText));
        returnDateEditText.setOnClickListener(v -> showDatePickerDialog(returnDateEditText));
    }

    // Méthode séparée pour le calendrier
    private void showDatePickerDialog(EditText targetEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}



