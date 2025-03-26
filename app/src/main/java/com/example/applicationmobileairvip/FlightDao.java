package com.example.applicationmobileairvip;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FlightDao {

    // Insère une liste de vols dans la base de données
    @Insert
    void insertFlights(Flight... flights);

    // Compte le nombre de vols dans la table (pour vérifier si la base est pré-remplie)
    @Query("SELECT COUNT(*) FROM Flight")
    int countFlights();

    // Récupère la liste des vols correspondant aux critères (lieux et date)
    @Query("SELECT * FROM Flight " +
            "WHERE departure = :from AND arrival = :to " +
            "AND (:date IS NULL OR :date = '' OR departureTime LIKE :date || '%' )")
    List<Flight> searchFlights(String from, String to, String date);
}
