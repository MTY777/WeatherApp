// region values
package com.example.weatherapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.weatherapp.R;


import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.data.model.MainResponse;
import com.example.weatherapp.databinding.FragmentWeatherBinding;
import com.google.android.gms.maps.GoogleMap;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherFragmentArgs args;
    private FragmentWeatherViewModel viewModel;

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    protected @NonNull
    FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentWeatherViewModel.class);
        args = WeatherFragmentArgs.fromBundle(getArguments());
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {
        viewModel.getWeatherById(args.getLatitude(), args.getLongitude());
    }

    @Override
    protected void setupListeners() {
        binding.cardViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.searchFragment);
            }
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), mainResponseResource -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            switch (mainResponseResource.status) {
                case ERROR: {
                    Toast.makeText(requireActivity(), "Ошибка", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.cardView.setVisibility(View.VISIBLE);
                    binding.cardViewTwo.setVisibility(View.VISIBLE);
                    binding.imageView.setVisibility(View.VISIBLE);
                    localBind();
                    break;
                }
                case LOADING: {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.cardView.setVisibility(View.GONE);
                    binding.cardViewTwo.setVisibility(View.GONE);
                    binding.imageView.setVisibility(View.GONE);
                    Toast.makeText(requireActivity(), "Загрузка", Toast.LENGTH_SHORT).show();
                    break;
                }
                case SUCCESS: {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.cardView.setVisibility(View.VISIBLE);
                    binding.cardViewTwo.setVisibility(View.VISIBLE);
                    binding.imageView.setVisibility(View.VISIBLE);
                    binds(mainResponseResource.data);
                    break;
                }
            }
        });
    }

    private void localBind() {
        List<MainResponse>RoomWeather = viewModel.getWeatherFromRoom();
        double temp = RoomWeather.get(RoomWeather.size() -1).getMain().getTemp();
        double tempe = RoomWeather.get(RoomWeather.size() -1).getWind().getSpeed();
        int num = RoomWeather.get(RoomWeather.size() -1).getMain().getPressure();
        int tem = (int) tempe;
        double nu = num;
        int temps = (int) temp;
        String getCityName = RoomWeather.get(RoomWeather.size() -1).getSys().getCountry();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDeteFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        String date = simpleDateFormat.format(RoomWeather.get(RoomWeather.size() -1).getDt());
        String diti = simpleDeteFormat.format(RoomWeather.get(RoomWeather.size() -1).getSys().getSunset());
        String dete = simpleDataFormat.format(RoomWeather.get(RoomWeather.size() -1).getSys().getSunrise());
        binding.tvSunset.setText(date);
        binding.tvDaytime.setText(diti);
        binding.tvSunsure.setText(dete);
//        binding.tvCity.setText(data.getSys().getCountry() + "," + data.getName());
        binding.tvCity.setText(getCityName);
        binding.tvGradus.setText(temps + "\u00B0c");
        binding.tvHamidity.setText(RoomWeather.get(RoomWeather.size() -1).getMain().getHumidity() + "%");
        binding.tvPressure.setText(nu + "mBar");
        binding.tvWind.setText(tem + "km/h");

    }

    private void binds(MainResponse data) {
        double temp = data.getMain().getTemp();
        double tempe = data.getWind().getSpeed();
        int num = data.getMain().getPressure();
        int tem = (int) tempe;
        double nu = num;
        int temps = (int) temp;
        String getCityName = data.getSys().getCountry();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDeteFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        String date = simpleDateFormat.format(data.getDt());
        String diti = simpleDeteFormat.format(data.getSys().getSunset());
        String dete = simpleDataFormat.format(data.getSys().getSunrise());
        binding.tvSunset.setText(date);
        binding.tvDaytime.setText(diti);
        binding.tvSunsure.setText(dete);
//        binding.tvCity.setText(data.getSys().getCountry() + "," + data.getName());
        binding.tvCity.setText(getCityName);
        binding.tvGradus.setText(temps + "\u00B0c");
        binding.tvHamidity.setText(data.getMain().getHumidity() + "%");
        binding.tvPressure.setText(nu + "mBar");
        binding.tvWind.setText(tem + "km/h");

    }

}
//endregion