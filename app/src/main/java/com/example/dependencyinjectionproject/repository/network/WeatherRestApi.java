package com.example.dependencyinjectionproject.repository.network;

import com.example.dependencyinjectionproject.model.weather.WeatherList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRestApi {

  @GET("daily")
  Call<WeatherList> weatherList(
    @Query("q") String q,
    @Query("units") String units,
    @Query("cnt") String cnt,
    @Query("mode") String mode,
    @Query("apikey") String apikey
  );
}
