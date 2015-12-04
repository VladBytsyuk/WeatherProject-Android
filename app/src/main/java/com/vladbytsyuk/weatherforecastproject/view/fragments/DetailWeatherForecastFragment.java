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

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class DetailWeatherForecastFragment extends Fragment {
    private Context context;
    private DBManager dbManager;

    ImageView imageViewDescription;
    TextView textViewTemperature;
    ListView listViewDetail;
    ImageView imageViewWind;
    TextView textViewWind;
    ImageView imageViewPressure;
    TextView textViewPressure;
    ImageView imageViewHumidity;
    TextView textViewHumidity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast ,container, false);
        context = getActivity();
        dbManager = new DBManager(context);

        rootView = viewInit(rootView);



        return rootView;
    }

    private View viewInit(View rootView) {
        imageViewDescription = (ImageView) rootView.findViewById(R.id.imageViewDetailDescription);
        imageViewWind = (ImageView) rootView.findViewById(R.id.imageViewDetailWind);
        imageViewPressure = (ImageView) rootView.findViewById(R.id.imageViewDetailPressure);
        imageViewHumidity = (ImageView) rootView.findViewById(R.id.imageViewDetailHumidity);
        textViewTemperature = (TextView) rootView.findViewById(R.id.textViewDetailTemperature);
        textViewWind = (TextView) rootView.findViewById(R.id.textViewDetailWind);
        textViewPressure = (TextView) rootView.findViewById(R.id.textViewDetailPressure);
        textViewHumidity = (TextView) rootView.findViewById(R.id.textViewDetailHumidity);
        listViewDetail = (ListView) rootView.findViewById(R.id.listViewDetail);

        return  rootView;
    }
}
