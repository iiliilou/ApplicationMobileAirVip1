package com.example.applicationmobileairvip.api;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8081"; // Adresse de l'API REST

    public interface ApiCallback {
        void onSuccess(String response);
        void onError(Exception e);
    }

    public static void get(String endpoint, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();
                connection.disconnect();

                new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(responseBuilder.toString()));

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        }).start();
    }

    public static void post(String endpoint, JSONObject data, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
                outputStream.write(data.toString().getBytes());
                outputStream.flush();
                outputStream.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();
                connection.disconnect();

                new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(responseBuilder.toString()));

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        }).start();
    }

    public static void put(String endpoint, JSONObject data, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
                outputStream.write(data.toString().getBytes());
                outputStream.flush();
                outputStream.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();
                connection.disconnect();

                new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(responseBuilder.toString()));

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e));
            }
        }).start();
    }
}
