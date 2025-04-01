package com.example.applicationmobileairvip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aeroport {

    @JsonProperty("id_aeroport")
    private int id;

    @JsonProperty("code_IATA")
    private String code;

    @JsonProperty("ville")
    private String ville;

    @JsonProperty("pays")
    private String pays;

    @JsonProperty("distance_montreal")
    private int distanceMontreal;

    public String getVille() { return ville; }
    public String getPays() { return pays; }
    public String getCode() { return code; }
}
