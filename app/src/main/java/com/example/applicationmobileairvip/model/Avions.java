package com.example.applicationmobileairvip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Avions {

    @JsonProperty("avion_id")
    private int id;

    @JsonProperty("modele")
    private String modele;

    @JsonProperty("capacite")
    private int capacite;

    public String getModele() { return modele; }
    public int getCapacite() { return capacite; }
}
