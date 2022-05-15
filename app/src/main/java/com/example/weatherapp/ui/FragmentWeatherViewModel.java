package com.example.weatherapp.ui;

import androidx.annotation.Nullable;
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


    @Inject
    public FragmentWeatherViewModel(MainRepositoryImpl mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getWeatherById(@Nullable String lat, String lon) {
        liveData = mainRepository.getWeather(lat, lon);
    }
    public List<MainResponse> getWeatherFromRoom(){
        return mainRepository.getWeatherRoom();
    }
}
