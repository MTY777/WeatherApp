package com.example.weatherapp.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;

import java.util.List;

public interface MainRepository {

    MutableLiveData<Resource<MainResponse>> getWeather(String city);

    LiveData<List<MainResponse>> getWeather();
}
