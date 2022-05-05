package com.example.weatherapp.data.room.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapp.data.converter.Converter;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.data.room.dao.WeatherDao;

@Database(entities = {MainResponse.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class AppDataBase extends RoomDatabase {
    public abstract WeatherDao dao();


}
