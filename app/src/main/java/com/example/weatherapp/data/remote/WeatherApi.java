package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.domain.repository.MainRepository;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Call<MainResponse> getDegree(@Query("lat") String lat,
                                 @Query("lon") String lon
            , @Query("appid") String apiKey
            , @Query("units") String units);

}
