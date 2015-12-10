package com.vladbytsyuk.weatherforecastproject.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.DayTimeWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.view.FormatWeather;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class DetailWeatherForecastAdapter extends BaseAdapter {
    Context context;
    ArrayList<DayTimeWeatherForecast> dayTimeWeatherForecasts;

    public DetailWeatherForecastAdapter(Context context, WeatherForecast weatherForecast) {
        this.context = context;
        ArrayList<DayTimeWeatherForecast> buf = new ArrayList<>();
        buf.add(new DayTimeWeatherForecast(context.getResources().getString(R.string.morning_time), weatherForecast.getTemperature().getMorningTemperature()));
        buf.add(new DayTimeWeatherForecast(context.getResources().getString(R.string.day_time), weatherForecast.getTemperature().getDayTemperature()));
        buf.add(new DayTimeWeatherForecast(context.getResources().getString(R.string.evening_time), weatherForecast.getTemperature().getEveningTemperature()));
        buf.add(new DayTimeWeatherForecast(context.getResources().getString(R.string.night_time), weatherForecast.getTemperature().getNightTemperature()));
        this.dayTimeWeatherForecasts = buf;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_detail, parent, false);
            viewHolder = viewHolderInit(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewDetailTemperature.setText(FormatWeather.temperatureToString(getItem(position).getTempeature()));

        viewHolder.imageViewDetailDescription.setImageResource(FormatWeather.getDayTimeIcon(position));
        viewHolder.textViewDetailDayTime.setText(getItem(position).getDayTime());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public DayTimeWeatherForecast getItem(int position) {
        return dayTimeWeatherForecasts.get(position);
    }

    @Override
    public int getCount() {
        return dayTimeWeatherForecasts.size();
    }

    private ViewHolder viewHolderInit(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewDetailTemperature = (TextView) convertView.findViewById(R.id.textViewDetailMaxTemperature);
        viewHolder.imageViewDetailDescription = (ImageView) convertView.findViewById(R.id.imageViewDetailDescription);
        viewHolder.textViewDetailDayTime = (TextView) convertView.findViewById(R.id.textViewDetailDayTime);
        return viewHolder;
    }

    static class ViewHolder {
        TextView textViewDetailTemperature;
        ImageView imageViewDetailDescription;
        TextView textViewDetailDayTime;
    }
}
