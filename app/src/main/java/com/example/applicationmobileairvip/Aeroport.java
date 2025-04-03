package com.example.applicationmobileairvip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aeroport {
    @JsonProperty("id_aeroport")
    private int idAeroport;

    @JsonProperty("code_IATA")
    private String codeIATA;

    @JsonProperty("ville")
    private String ville;

    @JsonProperty("pays")
    private String pays;

    @JsonProperty("distance_montreal")
    private int distanceMontreal;

    // Getters et setters

    public int getIdAeroport() { return idAeroport; }
    public void setIdAeroport(int idAeroport) { this.idAeroport = idAeroport; }

    public String getCodeIATA() { return codeIATA; }
    public void setCodeIATA(String codeIATA) { this.codeIATA = codeIATA; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    public int getDistanceMontreal() { return distanceMontreal; }
    public void setDistanceMontreal(int distanceMontreal) { this.distanceMontreal = distanceMontreal; }
}
