// region values
package com.example.weatherapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;

import com.example.weatherapp.data.model.MainResponse;

import com.example.weatherapp.databinding.FragmentWeatherBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherFragmentArgs args;
    private FragmentWeatherViewModel viewModel;

    @Override
    protected FragmentWeatherBinding bind() {
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
        viewModel.getWeatherById(args.getCity());
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
        viewModel.localLiveData.observe(getViewLifecycleOwner(), mainResponses -> {
            binds(mainResponses.get(mainResponses.size()-1));
        });
    }

    private void binds(MainResponse data) {
        double temp = data.getMain().getTemp();
        double tempe = data.getWind().getSpeed();
        int num = data.getMain().getPressure();
        int tem = (int) tempe;
        double nu = num;
        int temps = (int) temp;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDeteFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm", Locale.ROOT);
        String date = simpleDateFormat.format(data.getDt());
        String diti = simpleDeteFormat.format(data.getSys().getSunset());
        String dete = simpleDataFormat.format(data.getSys().getSunrise());
        binding.tvSunset.setText(date);
        binding.tvDaytime.setText(diti);
        binding.tvSunsure.setText(dete);
        binding.tvCity.setText(data.getSys().getCountry() + "," + data.getName());
        binding.tvGradus.setText(String.valueOf(temps));
        binding.tvHamidity.setText(data.getMain().getHumidity() + "%");
        binding.tvPressure.setText(nu + "mBar");
        binding.tvWind.setText(tem + "km/h");

    }

}
//endregion