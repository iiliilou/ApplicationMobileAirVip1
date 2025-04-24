package com.example.applicationmobileairvip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.applicationmobileairvip.model.Vol;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

    @JsonProperty("id")
    private int reservationId;

    @JsonProperty("vol")
    private Vol vol;

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
