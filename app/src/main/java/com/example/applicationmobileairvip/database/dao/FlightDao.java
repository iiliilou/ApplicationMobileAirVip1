package com.example.applicationmobileairvip.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.applicationmobileairvip.database.entities.Flight;
import java.util.List;
@Dao
public interface FlightDao {
    @Query("SELECT * FROM flights WHERE departureAirport LIKE :departure AND arrivalAirport LIKE :arrival AND date = :date")
    List<Flight> searchFlights(String departure, String arrival, String date);
}