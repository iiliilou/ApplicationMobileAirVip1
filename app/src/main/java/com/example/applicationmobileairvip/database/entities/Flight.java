package com.example.applicationmobileairvip.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flights")
public class Flight {
    @PrimaryKey
    public int id;

    public String flightNumber;
    public String departureAirport;
    public String arrivalAirport;
    public String departureTime;
    public String arrivalTime;
    public String airline;
    public double price;
    public String date;
}