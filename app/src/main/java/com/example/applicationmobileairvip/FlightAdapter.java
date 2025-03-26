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

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flight> flightList;

    public FlightAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);

        holder.departTime.setText(flight.getDepartureTime());
        holder.arrivalTime.setText(flight.getArrivalTime());
        holder.airportRoute.setText(flight.getDeparture() + " ➡ " + flight.getArrival());
        holder.flightDuration.setText(flight.getStops() + " escale(s) - " + flight.getDuration());
        holder.flightDetails.setText(flight.getCompany() + " · " + flight.getEmissions());
        holder.flightWarnings.setText(flight.getWarnings());
        holder.flightPrice.setText(flight.getPrice() + " $CA");
        holder.flightType.setText(flight.getTicketType());

        Glide.with(holder.itemView.getContext())
                .load(flight.getLogoUrl())
                .placeholder(R.drawable.ic_plane)
                .into(holder.companyLogo);
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    static class FlightViewHolder extends RecyclerView.ViewHolder {
        ImageView companyLogo;
        TextView departTime, arrivalTime, airportRoute, flightDuration,
                flightDetails, flightWarnings, flightPrice, flightType;

        public FlightViewHolder(@NonNull View itemView) {
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
