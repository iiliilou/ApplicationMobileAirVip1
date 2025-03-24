package com.example.applicationmobileairvip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applicationmobileairvip.database.entities.Flight;
import java.util.List;


public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private final List<Flight> flights;

    public FlightAdapter(List<Flight> flights) {
        this.flights = flights;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight = flights.get(position);

        holder.airlineText.setText(flight.airline);
        holder.priceText.setText(String.format("%.2f $", flight.price));
        holder.departureText.setText(String.format("%s * $s", flight.departureAirport, flight.departureTime));
        holder.arrivalText.setText(String.format("%s * %s", flight.arrivalAirport, flight.arrivalTime));
        holder.flightNumberText.setText(flight.flightNumber);
    }


    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class ViewHolder extends RecyclerView {
        TextView airlineText, priceText, departureText, arrivalText, flightNumberText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            airlineText = itemView.findViewById(R.id.airlineText);
            priceText = itemView.findViewById(R.id.priceText);
            departureText = itemView.findViewById(R.id.departureText);
            arrivalText = itemView.findViewById(R.id.arrivalText);
            flightNumberText = itemView.findViewById(R.id.flightNumberText);
        }
    }


}
