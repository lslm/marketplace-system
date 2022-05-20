package com.lslm.stockapi.clients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lslm.stockapi.entities.Product;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Component
public class ProductClient {
    final String BASE_URL = "http://localhost:8081/api/products";

    private final OkHttpClient client;
    private final Gson gson;

    public ProductClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public Product getById(UUID id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id.toString())
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return gson.fromJson(response.body().string(), Product.class);
        }

        return null;
    }

    public List<Product> getAll() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Response response = client.newCall(request).execute();

        Type productListType = new TypeToken<List<Product>>(){}.getType();
        return gson.fromJson(response.body().string(), productListType);
    }
}
