package com.example.applicationmobileairvip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationmobileairvip.R;
import com.example.applicationmobileairvip.model.Vol;

import java.util.List;

public class VolAdapter extends RecyclerView.Adapter<VolAdapter.VolViewHolder> {

    public interface OnVolClickListener {
        void onVolClick(Vol vol);
    }

    private final List<Vol> volList;
    private final OnVolClickListener clickListener;

    public VolAdapter(List<Vol> volList, OnVolClickListener clickListener) {
        this.volList = volList;
        this.clickListener = clickListener;
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

        holder.airportRoute.setText(
                vol.getAeroportDepart().getVille() + " ➡ " + vol.getAeroportArrive().getVille()
        );

        holder.flightDuration.setText(vol.getTemps() + "h - " + vol.getNbPlace() + " places");
        holder.flightDetails.setText(vol.getAvion().getModele());
        holder.flightWarnings.setText(vol.getDisponibilite());
        holder.flightPrice.setText(vol.getPrix() + " $CA");
        holder.flightType.setText("Vol #" + vol.getVolId());

        // Charger l’image de l’avion
        String imageUrl = null;
        if (vol.getAvion().getImages() != null && !vol.getAvion().getImages().isEmpty()) {
            imageUrl = vol.getAvion().getImages().get(0).getUrl();
        }

        if (imageUrl != null) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_plane)
                    .into(holder.companyLogo);
        } else {
            holder.companyLogo.setImageResource(R.drawable.ic_plane);
        }

        // ➕ Clic sur le vol
        holder.itemView.setOnClickListener(v -> clickListener.onVolClick(vol));
    }

    @Override
    public int getItemCount() {
        return volList.size();
    }

    static class VolViewHolder extends RecyclerView.ViewHolder {
        ImageView companyLogo;
        TextView  airportRoute, flightDuration,
                flightDetails, flightWarnings, flightPrice, flightType;

        public VolViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            airportRoute = itemView.findViewById(R.id.airportRoute);
            flightDuration = itemView.findViewById(R.id.flightDuration);
            flightDetails = itemView.findViewById(R.id.flightDetails);
            flightWarnings = itemView.findViewById(R.id.flightWarnings);
            flightPrice = itemView.findViewById(R.id.flightPrice);
            flightType = itemView.findViewById(R.id.flightType);
        }
    }
}
