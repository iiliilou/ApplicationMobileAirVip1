package com.example.applicationmobileairvip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VolAdapter extends RecyclerView.Adapter<VolAdapter.VolViewHolder> {

    private List<Vol> volList;

    public VolAdapter(List<Vol> volList) {
        this.volList = volList;
    }

    @NonNull
    @Override
    public VolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);
        return new VolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolViewHolder holder, int position) {
        Vol vol = volList.get(position);

        // 🕒 Horaires fictifs (peuvent être mis à jour avec des vrais si tu les ajoutes)
        holder.departTime.setText("--:--");
        holder.arrivalTime.setText("--:--");

        // 🛫 Affichage villes de départ/arrivée
        holder.airportRoute.setText(
                vol.getAeroportDepart().getVille() + " ➡ " + vol.getAeroportArrive().getVille()
        );

        // ⏱ Durée + nb places
        holder.flightDuration.setText(vol.getTemps() + "h - " + vol.getNbPlace() + " places");

        // ✈️ Modèle avion
        holder.flightDetails.setText(vol.getAvion().getModele());

        // ⚠️ Disponibilité
        holder.flightWarnings.setText(vol.getDisponibilite());

        // 💰 Prix
        holder.flightPrice.setText(vol.getPrix() + " $CA");

        // 📦 ID vol
        holder.flightType.setText("Vol #" + vol.getVolId());

        // 🖼 Charger la première image de l’avion
        String imageUrl = null;
        if (vol.getAvion().getImages() != null && !vol.getAvion().getImages().isEmpty()) {
            imageUrl = vol.getAvion().getImages().get(0).getUrl();
        }

        if (imageUrl != null) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_plane) // Icône par défaut si chargement lent
                    .into(holder.companyLogo);
        } else {
            holder.companyLogo.setImageResource(R.drawable.ic_plane);
        }
    }

    @Override
    public int getItemCount() {
        return volList.size();
    }

    static class VolViewHolder extends RecyclerView.ViewHolder {
        ImageView companyLogo;
        TextView departTime, arrivalTime, airportRoute, flightDuration,
                flightDetails, flightWarnings, flightPrice, flightType;

        public VolViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            departTime = itemView.findViewById(R.id.departTime);
            arrivalTime = itemView.findViewById(R.id.arrivalTime);
            airportRoute = itemView.findViewById(R.id.airportRoute);
            flightDuration = itemView.findViewById(R.id.flightDuration);
            flightDetails = itemView.findViewById(R.id.flightDetails);
            flightWarnings = itemView.findViewById(R.id.flightWarnings);
            flightPrice = itemView.findViewById(R.id.flightPrice);
            flightType = itemView.findViewById(R.id.flightType);
        }
    }
}
