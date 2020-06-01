package bpawl.lochat.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;
import java.time.LocalDateTime;

import bpawl.lochat.api.utils.InstantAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LochatAPIRetrofitProvider implements IRetrofitProvider {
    static final String BASE_URL = "https://lochat-master.azurewebsites.net/";
    private Retrofit _retrofit;

    public LochatAPIRetrofitProvider() {
        Gson gson = new GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .create();

        _retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

    @Override
    public Retrofit getRetrofit() {
        return _retrofit;
    }
}
