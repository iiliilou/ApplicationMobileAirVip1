package com.example.applicationmobileairvip.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.applicationmobileairvip.database.dao.FlightDao;
import com.example.applicationmobileairvip.database.entities.Flight;
@Database(entities = {Flight.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract FlightDao flightDao();

    public static synchronized  AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "flight_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
