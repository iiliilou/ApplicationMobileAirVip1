package com.example.applicationmobileairvip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flight> flightList = new ArrayList<>();

    // Met à jour la liste des vols dans l'adaptateur
    public void setFlights(List<Flight> flights) {
        this.flightList = flights;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infle le layout d'un item de vol
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);
        return new FlightViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        // Récupère le vol courant
        Flight flight = flightList.get(position);

        // Remplit les vues de l'item avec les données du vol
        // 1. En-tête AirVip (texte et icône sont statiques définis dans le layout)
        // 2. Route et horaires
        String depTime = flight.getDepartureTime();
        String arrTime = flight.getArrivalTime();
        // Extraire uniquement l'heure (supposant que departureTime inclut la date et l'heure)
        if (depTime.contains(" ")) {
            depTime = depTime.substring(depTime.indexOf(" ") + 1);
        }
        if (arrTime.contains(" ")) {
            arrTime = arrTime.substring(arrTime.indexOf(" ") + 1);
        }
        // Format "LieuDep ➡ LieuArr | hh:mm - hh:mm"
        String routeTime = holder.itemView.getContext().getString(
                R.string.flight_route_time_format,
                flight.getDeparture(), flight.getArrival(), depTime, arrTime);
        holder.routeTimeText.setText(routeTime);

        // 3. Modèle et capacité
        String modelCapacity = holder.itemView.getContext().getString(
                R.string.model_capacity_format,
                flight.getModel(), flight.getCapacity());
        holder.modelCapacityText.setText(modelCapacity);

        // 4. Services VIP
        String services = holder.itemView.getContext().getString(
                R.string.services_label, flight.getServices());
        holder.servicesText.setText(services);

        // 5. Prix
        holder.priceText.setText(flight.getPrice() + " €");
        // (Le textColor orange est déjà défini dans le layout XML)

        // 6. Bouton "Réserver"
        // (Le bouton n'a pas de logique de clic implémentée ici, il pourrait ouvrir une page de réservation)
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    // ViewHolder interne pour représenter les vues d'un item de vol
    static class FlightViewHolder extends RecyclerView.ViewHolder {
        ImageView flightIcon;
        TextView airvipTitle;
        TextView routeTimeText;
        TextView modelCapacityText;
        TextView servicesText;
        TextView priceText;
        Button btnReserver;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            flightIcon = itemView.findViewById(R.id.flightIcon);
            airvipTitle = itemView.findViewById(R.id.airvipTitle);
            routeTimeText = itemView.findViewById(R.id.routeTimeText);
            modelCapacityText = itemView.findViewById(R.id.modelCapacityText);
            servicesText = itemView.findViewById(R.id.servicesText);
            priceText = itemView.findViewById(R.id.priceText);
            btnReserver = itemView.findViewById(R.id.btnReserver);
        }
    }
}
