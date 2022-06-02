package com.lslm.ordersapi.clients;

import com.google.gson.Gson;
import com.lslm.ordersapi.entities.ProductStock;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class StockClient {
    final String BASE_URL = "http://localhost:8080/api/stocks";

    private final Gson gson;
    private final OkHttpClient client;

    public StockClient() {
        gson = new Gson();
        client = new OkHttpClient();
    }

    public ProductStock getProductStock(UUID productId) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/products/" + productId.toString() + "/available")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return gson.fromJson(response.body().string(), ProductStock.class);
        }

        return null;
    }


}
