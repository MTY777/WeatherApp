package com.example.weatherapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.data.repositories.MainRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragmentWeatherViewModel extends ViewModel {
    public LiveData<Resource<MainResponse>> liveData;
    private MainRepositoryImpl mainRepository;
    public LiveData<List<MainResponse>> localLiveData;

    public void getWeather() {
        localLiveData = mainRepository.getWeather();
    }

    @Inject
    public FragmentWeatherViewModel(MainRepositoryImpl mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getWeatherById(String city) {
        liveData = mainRepository.getWeather(city);
    }
}
