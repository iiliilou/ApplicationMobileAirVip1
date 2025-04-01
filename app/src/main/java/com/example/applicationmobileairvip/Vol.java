package com.example.applicationmobileairvip;

public class Vol {
    private int vol_id;
    private double prix;
    private String disponibilite;
    private int nb_place;
    private double temps;

    private Aeroport aeroportDepart;
    private Aeroport aeroportArrive;
    private Avions avion;

    // Constructeur vide requis pour le parsing
    public Vol() {}

    // Constructeur complet (optionnel)
    public Vol(int vol_id, double prix, String disponibilite, int nb_place, double temps,
               Aeroport aeroportDepart, Aeroport aeroportArrive, Avions avion) {
        this.vol_id = vol_id;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.nb_place = nb_place;
        this.temps = temps;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrive = aeroportArrive;
        this.avion = avion;
    }

    // Getters
    public int getVol_id() {
        return vol_id;
    }

    public double getPrix() {
        return prix;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public int getNb_place() {
        return nb_place;
    }

    public double getTemps() {
        return temps;
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }

    public Aeroport getAeroportArrive() {
        return aeroportArrive;
    }

    public Avions getAvion() {
        return avion;
    }

    // Méthodes utiles pour l'affichage
    public String getVilleDepart() {
        return aeroportDepart != null ? aeroportDepart.getVille() : "Inconnu";
    }

    public String getVilleArrivee() {
        return aeroportArrive != null ? aeroportArrive.getVille() : "Inconnu";
    }
}
