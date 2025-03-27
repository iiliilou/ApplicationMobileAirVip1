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

        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        departureDateEditText = findViewById(R.id.editDateDepart);
        returnDateEditText = findViewById(R.id.editDateRetour);
        typeVols = findViewById(R.id.typeVols);
        layoutRetour = findViewById(R.id.layoutRetour);
        Button btnRechercher = findViewById(R.id.btnRechercher);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_reserver);

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

        btnRechercher.setOnClickListener(v -> {
            String from = fromText.getText().toString().trim();
            String to = toText.getText().toString().trim();
            String depart = departureDateEditText.getText().toString().trim();
            String retour = returnDateEditText.getText().toString().trim();

            if (from.isEmpty() || to.isEmpty() || depart.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir les champs de départ, d'arrivée et de date", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, VolListActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("to", to);
            intent.putExtra("departDate", depart);
            intent.putExtra("returnDate", retour);
            startActivity(intent);
        });

        departureDateEditText.setOnClickListener(v -> showDatePickerDialog(departureDateEditText));
        returnDateEditText.setOnClickListener(v -> showDatePickerDialog(returnDateEditText));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_reserver) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_status) {
                startActivity(new Intent(HomeActivity.this, VolListActivity.class));
                return true;
            } else if (id == R.id.nav_trips) {
                startActivity(new Intent(HomeActivity.this, TripsActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                return true;
            }

            return false;
        });
    }

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
