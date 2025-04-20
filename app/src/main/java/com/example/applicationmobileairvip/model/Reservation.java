package com.example.applicationmobileairvip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reservation {

    @JsonProperty("reservationId")
    private int reservationId;

    @JsonProperty("vol")
    private Vol vol;

    // (Optionnel) Si l’API renvoie aussi l’utilisateur
    // @JsonProperty("utilisateur")
    // private Utilisateur utilisateur;

    @JsonProperty("dateReservation")
    private String dateReservation;

    public int getReservationId() {
        return reservationId;
    }

    public Vol getVol() {
        return vol;
    }

    public String getDateReservation() {
        return dateReservation;
    }
}
