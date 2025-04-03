package com.example.applicationmobileairvip;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Avion {
    @JsonProperty("avion_id")
    private int avionId;

    @JsonProperty("modele")
    private String modele;

    @JsonProperty("capacite")
    private int capacite;

    @JsonProperty("images")
    private List<ImageAvion> images;

    // Getters et setters

    public int getAvionId() { return avionId; }
    public void setAvionId(int avionId) { this.avionId = avionId; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<ImageAvion> getImages() { return images; }
    public void setImages(List<ImageAvion> images) { this.images = images; }
}
