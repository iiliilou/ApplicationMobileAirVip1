package com.example.applicationmobileairvip.api;

import com.example.applicationmobileairvip.model.Vol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
public class VolApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8081/vols"; // ← pas localhost mais 10.0.2.2 pour Android Emulator

    private static VolApiClient instance;

    public static VolApiClient getInstance() {
        if (instance == null) {
            instance = new VolApiClient();
        }
        return instance;
    }

    public List<Vol> getVols() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lire la réponse
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStr.append(line);
            }
            reader.close();

            // Désérialisation
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseStr.toString(), new TypeReference<List<Vol>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

