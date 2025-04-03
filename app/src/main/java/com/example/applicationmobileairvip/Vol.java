package com.example.applicationmobileairvip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vol {
    @JsonProperty("vol_id")
    private int volId;

    @JsonProperty("prix")
    private double prix;

    @JsonProperty("disponibilite")
    private String disponibilite;

    @JsonProperty("nb_place")
    private int nbPlace;

    @JsonProperty("temps")
    private double temps;

    @JsonProperty("aeroportDepart")
    private Aeroport aeroportDepart;

    @JsonProperty("aeroportArrive")
    private Aeroport aeroportArrive;

    @JsonProperty("avion")
    private Avion avion;

    // Getters et setters

    public int getVolId() { return volId; }
    public void setVolId(int volId) { this.volId = volId; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getDisponibilite() { return disponibilite; }
    public void setDisponibilite(String disponibilite) { this.disponibilite = disponibilite; }

    public int getNbPlace() { return nbPlace; }
    public void setNbPlace(int nbPlace) { this.nbPlace = nbPlace; }

    public double getTemps() { return temps; }
    public void setTemps(double temps) { this.temps = temps; }

    public Aeroport getAeroportDepart() { return aeroportDepart; }
    public void setAeroportDepart(Aeroport aeroportDepart) { this.aeroportDepart = aeroportDepart; }

    public Aeroport getAeroportArrive() { return aeroportArrive; }
    public void setAeroportArrive(Aeroport aeroportArrive) { this.aeroportArrive = aeroportArrive; }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }
}
