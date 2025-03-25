package com.example.applicationmobileairvip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchDarkMode = findViewById(R.id.switch_dark_mode);
        LinearLayout layoutAccount = findViewById(R.id.layout_account);
        LinearLayout layoutLogout = findViewById(R.id.layout_logout);

        // SharedPreferences
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);
        switchDarkMode.setChecked(isDark);

        // Mode sombre
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate();
        });

        // Mon compte
        layoutAccount.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, CompteActivity.class);
            startActivity(intent);
        });

        // Déconnexion
        layoutLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Déconnecté avec succès", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        LinearLayout layoutLanguage = findViewById(R.id.layout_language);
        TextView tvCurrentLanguage = findViewById(R.id.tv_current_language);

        layoutLanguage.setOnClickListener(v -> {
            String[] languages = {"Français", "English"};

            new android.app.AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("Choisir une langue")
                    .setItems(languages, (dialog, which) -> {
                        String selectedLanguage = languages[which];
                        tvCurrentLanguage.setText(selectedLanguage);

                        // TODO : Appliquer réellement la langue à l'app (facultatif)
                        Toast.makeText(this, "Langue sélectionnée : " + selectedLanguage, Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });


        // Navigation bas
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_settings);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_reserver) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_trips) {
                startActivity(new Intent(this, TripsActivity.class));
                return true;
            } else return itemId == R.id.nav_settings; // On est déjà ici
        });

    }
}

