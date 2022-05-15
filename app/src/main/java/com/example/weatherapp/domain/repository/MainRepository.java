package com.example.weatherapp.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;

import java.util.List;

public interface MainRepository {

    MutableLiveData<Resource<MainResponse>> getWeather(String lat, String lon);

    LiveData<List<MainResponse>> getWeather(List<MainResponse> mainResponses);
}
