package com.example.applicationmobileairvip;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
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



/*
import java.util.ArrayList;
import java.util.List;

public class VolApiClient {

    private static VolApiClient instance;
    private List<Vol> volList;

    private VolApiClient() {
        volList = new ArrayList<>();

        // ✈️ Données fictives simulant des vols reçus depuis une API
        volList.add(new Vol(1, 30500.0, "10 places", 12,
                1, 2, 3, 9.5));
        volList.add(new Vol(2, 17000.0, "3 places", 7,
                2, 1, 2, 6.0));
        volList.add(new Vol(3, 14000.0, "8 places", 15,
                1, 3, 1, 3.5));
    }

    public static VolApiClient getInstance() {
        if (instance == null) {
            instance = new VolApiClient();
        }
        return instance;
    }

    public List<Vol> getVols() {
        return volList;
    }
}

 */

/*

  //vrai fichier VolApiClient pour quand on va utiliser l'api
 public class VolApiClient {
     private static final String URL_API = "http://10.0.2.2:8080/vols"; // Utiliser 10.0.2.2 pour l'émulateur Android
      private static VolApiClient instance;

      public static VolApiClient getInstance() {
          if (instance == null) {
              instance = new VolApiClient();
          }
          return instance;
      }

      public List<Vol> getVols() {
          List<Vol> vols = new ArrayList<>();

          try {
              URL url = new URL(URL_API);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setRequestMethod("GET");

              BufferedReader reader = new BufferedReader(
                  new InputStreamReader(connection.getInputStream())
              );

              ObjectMapper mapper = new ObjectMapper();
              vols = Arrays.asList(mapper.readValue(reader, Vol[].class));
 *
              reader.close();
              connection.disconnect();

          } catch (IOException e) {
             e.printStackTrace();
         }

          return vols;
      }
 }

*/