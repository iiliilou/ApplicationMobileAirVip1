package com.example.applicationmobileairvip;


import com.fasterxml.jackson.annotation.JsonProperty;

//permet de stocker donner recu de l'Api (vol_id etc)

//utilisation de jsonproprety pour mapper les noms json aux atributs java
public class Vol {
    @JsonProperty("vol_id")
    private int volId;

    @JsonProperty("prix")
    private double prix;

    @JsonProperty("disponibilite")
    private String disponibilite;

    @JsonProperty("nb_place")
    private int nbPlace;

    @JsonProperty("FKAeroport_arrive")
    private int fkAeroportArrive;

    @JsonProperty("FK_Aeroport_depart")
    private int fkAeroportDepart;

    @JsonProperty("FK_avion")
    private int fkAvion;

    @JsonProperty("temps")
    private double temps;

    public Vol(int volId, double prix, String disponibilite, int NbPlace,
               int fkAeroportArrive, int fkAeroportDepart, int fkAvion, double temps) {

        this.volId = volId;
        this.prix  = prix;
        this.disponibilite = disponibilite;
        this.nbPlace = nbPlace;
        this.fkAeroportArrive =fkAeroportArrive;
        this.fkAeroportDepart = fkAeroportDepart;
        this.fkAvion = fkAvion;
        this.temps = temps;
    }

    //getters et setters
    public int getVolId(){return volId;}
    public void setVolId(int volId) { this.volId = volId; }

    public double getPrix(){return prix;}
    public void setPrix(double prix){this.prix = prix; }

    public String getDisponibilite(){return disponibilite; }
    public void setDisponibilite(String disponibilite){this.disponibilite = disponibilite; }

    public int getNbPlace(){return nbPlace;}
    public void setNbPlace(int nbPlace){this.nbPlace = nbPlace;}

    public int getFkAeroportArrive(){return fkAeroportArrive;}
    public void setFkAeroportArrive(int fkAeroportArrive){this.fkAeroportArrive = fkAeroportArrive;}

    public int getFkAeroportDepart(){return fkAeroportDepart;}
    public void setFkAeroportDepart(int fkAeroportDepart){this.fkAeroportDepart = fkAeroportDepart;}

    public int getFkAvion(){return fkAvion;}
    public void setFkAvion(int fkAvion){this.fkAvion = fkAvion;}

    public double getTemps(){return temps;}
    public void setTemps(double temps){this.temps = temps;}



}
