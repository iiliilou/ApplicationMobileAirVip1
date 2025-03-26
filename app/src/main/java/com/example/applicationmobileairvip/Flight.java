package com.example.applicationmobileairvip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Flight {
    @PrimaryKey(autoGenerate = true)
    private int flightNumber;     // Numéro unique du vol

    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String model;
    private int capacity;
    private int price;
    private String services;

    // Constructeur (sans le numéro, généré automatiquement par Room)
    public Flight(String departure, String arrival, String departureTime, String arrivalTime,
                  String model, int capacity, int price, String services) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.model = model;
        this.capacity = capacity;
        this.price = price;
        this.services = services;
    }

    // Getters et setters
    public int getFlightNumber() { return flightNumber; }
    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getArrival() { return arrival; }
    public void setArrival(String arrival) { this.arrival = arrival; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getServices() { return services; }
    public void setServices(String services) { this.services = services; }
}
