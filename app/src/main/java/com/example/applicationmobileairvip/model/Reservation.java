package com.example.applicationmobileairvip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.applicationmobileairvip.model.Vol;

@JsonIgnoreProperties(ignoreUnknown = true) // ← ignore les champs inutiles comme "utilisateur"
public class Reservation {

    @JsonProperty("id") // ← correspond à la clé réelle dans ton JSON
    private int reservationId;

    @JsonProperty("vol")
    private Vol vol; // ← assure-toi que la classe Vol est bien importée

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
