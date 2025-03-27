package com.example.applicationmobileairvip;
/**
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FlightDao {

    @Insert
    void insertFlights(Flight... flights);

    @Query("SELECT * FROM Flight WHERE departure = :from AND arrival = :to AND departureTime LIKE :date || '%'")
    List<Flight> findFlights(String from, String to, String date);

    @Query("SELECT COUNT(*) FROM Flight")
    int countFlights();
}
*/