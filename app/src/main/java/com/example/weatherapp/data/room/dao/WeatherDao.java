package com.example.weatherapp.data.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weatherapp.data.model.MainResponse;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert
    void insert(MainResponse mainResponse);

    @Query("SELECT * FROM mainresponse")
    List<MainResponse> getAll();


}
