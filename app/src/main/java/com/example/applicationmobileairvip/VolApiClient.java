package com.example.applicationmobileairvip;

import android.util.Log;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VolApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/vols"; // 10.0.2.2
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public interface VolCallback {
        void onSuccess(List<Vol> vols);
        void onError(Exception e);
    }

    public static void getVols(VolCallback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError(new IOException("Erreur HTTP: " + response.code()));
                    return;
                }

                String json = response.body().string();
                try {
                    List<Vol> vols = mapper.readValue(json, new TypeReference<List<Vol>>() {});
                    callback.onSuccess(vols);
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        });
    }
}
