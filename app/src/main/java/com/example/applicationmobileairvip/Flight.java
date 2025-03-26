package com.example.applicationmobileairvip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Flight {

    @PrimaryKey
    private int flightNumber;

    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private int stops;
    private double price;
    private String company;
    private String ticketType;
    private String services;
    private String warnings;
    private String emissions;
    private String model;
    private int capacity;
    private String logoUrl;

    public Flight() {}

    public Flight(int flightNumber, String departure, String arrival,
                  String departureTime, String arrivalTime, String duration, int stops,
                  double price, String company, String ticketType, String services,
                  String warnings, String emissions, String model, int capacity, String logoUrl) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.stops = stops;
        this.price = price;
        this.company = company;
        this.ticketType = ticketType;
        this.services = services;
        this.warnings = warnings;
        this.emissions = emissions;
        this.model = model;
        this.capacity = capacity;
        this.logoUrl = logoUrl;
    }

    // Getters et setters...
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
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public int getStops() { return stops; }
    public void setStops(int stops) { this.stops = stops; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public String getServices() { return services; }
    public void setServices(String services) { this.services = services; }
    public String getWarnings() { return warnings; }
    public void setWarnings(String warnings) { this.warnings = warnings; }
    public String getEmissions() { return emissions; }
    public void setEmissions(String emissions) { this.emissions = emissions; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}
