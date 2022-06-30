package com.lslm.marketplacebff.clients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lslm.marketplacebff.adapters.requests.ProductCreationRequest;
import com.lslm.marketplacebff.adapters.responses.ProductCreationResponse;
import com.squareup.okhttp.*;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Component
public class ProductClient {

    private final String BASE_URL = "http://localhost:8081/api/products";

    private final OkHttpClient client;
    private final Gson gson;

    public ProductClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public ProductCreationResponse createProduct(ProductCreationRequest newProduct) throws IOException {
        String json = gson.toJson(newProduct);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful())
            return gson.fromJson(response.body().string(), ProductCreationResponse.class);

        return null;
    }

    public List<ProductCreationResponse> listProducts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Response response = client.newCall(request).execute();

        Type productListType = new TypeToken<List<ProductCreationResponse>>(){}.getType();
        return gson.fromJson(response.body().string(), productListType);
    }
}
