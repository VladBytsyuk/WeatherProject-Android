package com.vladbytsyuk.weatherforecastproject.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.DBManager;
import com.vladbytsyuk.weatherforecastproject.controller.adapters.DetailWeatherForecastAdapter;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class DetailWeatherForecastFragment extends Fragment {
    private Context context;
    private DBManager dbManager;
    private WeatherForecast weatherForecast;

    ImageView imageViewDescription;
    TextView textViewTemperature;
    ListView listViewDetail;
    DetailWeatherForecastAdapter adapter;
    ImageView imageViewWind;
    TextView textViewWind;
    ImageView imageViewPressure;
    TextView textViewPressure;
    ImageView imageViewHumidity;
    TextView textViewHumidity;

    public void getWeatherForecast(WeatherForecast weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        context = getActivity();
        dbManager = new DBManager(context);

        rootView = viewInit(rootView);

        if (weatherForecast != null) {
            textViewTemperature.setText(weatherForecast.getTemperature().getMaxTemperature().toString() + "\u2103");
            textViewWind.setText(weatherForecast.getDetail().getWindSpeed().toString());
            textViewPressure.setText(weatherForecast.getDetail().getPressure().toString());
            textViewHumidity.setText(weatherForecast.getDetail().getHumidity().toString());
            imageViewDescription.setImageResource(R.drawable.sun_cloud);
            adapter = new DetailWeatherForecastAdapter(context, weatherForecast);
            listViewDetail.setAdapter(adapter);
        }

        return rootView;
    }

    private View viewInit(View rootView) {
        imageViewDescription = (ImageView) rootView.findViewById(R.id.imageViewDetailDescription);
        textViewTemperature = (TextView) rootView.findViewById(R.id.textViewDetailTemperature);
        textViewWind = (TextView) rootView.findViewById(R.id.textViewDetailWind);
        textViewPressure = (TextView) rootView.findViewById(R.id.textViewDetailPressure);
        textViewHumidity = (TextView) rootView.findViewById(R.id.textViewDetailHumidity);
        listViewDetail = (ListView) rootView.findViewById(R.id.listViewDetail);

        return  rootView;
    }
}