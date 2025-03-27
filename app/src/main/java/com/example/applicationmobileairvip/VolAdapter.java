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

        holder.departTime.setText("--:--");
        holder.arrivalTime.setText("--:--");
        holder.airportRoute.setText("Départ ID: " + vol.getFkAeroportDepart() + " ➡ Arrivée ID: " + vol.getFkAeroportArrive());
        holder.flightDuration.setText(vol.getTemps() + "h - " + vol.getNbPlace() + " places");
        holder.flightDetails.setText("Avion ID: " + vol.getFkAvion());
        holder.flightWarnings.setText(vol.getDisponibilite());
        holder.flightPrice.setText(vol.getPrix() + " $CA");
        holder.flightType.setText("Vol ID: " + vol.getVolId());

        Glide.with(holder.itemView.getContext())
                .load(R.drawable.ic_plane)
                .placeholder(R.drawable.ic_plane)
                .into(holder.companyLogo);
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
