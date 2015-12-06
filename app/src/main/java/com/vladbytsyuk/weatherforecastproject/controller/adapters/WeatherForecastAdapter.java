package com.vladbytsyuk.weatherforecastproject.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class WeatherForecastAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WeatherForecast> weatherForecastList;

    public WeatherForecastAdapter(Context context, ArrayList<WeatherForecast> weatherForecastList) {
        this.context = context;
        this.weatherForecastList = weatherForecastList;
    }

    @Override
    public int getCount() {
        return weatherForecastList.size();
    }

    @Override
    public WeatherForecast getItem(int position) {
        return weatherForecastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                                        .inflate(R.layout.item_weather_forecast, parent, false);
            viewHolder = viewHolderInit(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String maxTemperature = getItem(position).getTemperature().getMaxTemperature().toString() + "\u2103";
        String minTemperature = getItem(position).getTemperature().getMinTemperature().toString() + "\u2103";
        viewHolder.textViewMaxTemperature.setText(maxTemperature);
        viewHolder.textViewMinTemperature.setText(minTemperature);
        //TODO: Place image
        viewHolder.imageViewDescription.setImageResource(getIcon(getItem(position)));
        viewHolder.textViewDate.setText(getItem(position).getDay().replace('-','.'));
        viewHolder.textViewDescription.setText(getItem(position).getDescription());
        return convertView;
    }

    private ViewHolder viewHolderInit(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewMaxTemperature = (TextView) convertView.findViewById(R.id.textViewWFMaxTemperature);
        viewHolder.textViewMinTemperature = (TextView) convertView.findViewById(R.id.textViewWFMinTemperature);
        viewHolder.imageViewDescription = (ImageView) convertView.findViewById(R.id.imageViewWFDescription);
        viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewWFDate);
        viewHolder.textViewDescription = (TextView) convertView.findViewById(R.id.textViewWFDescription);
        return viewHolder;
    }

    private int getIcon(WeatherForecast weatherForecast) {
        String icon = weatherForecast.getIcon();
        switch (icon.substring(0, 2)) {
            case "01" : return R.drawable.sun;
            case "02" : return R.drawable.sun_cloud;
            case "03" : return R.drawable.cloud;
            case "04" : return R.drawable.light_rain;
            case "09" : return R.drawable.rain;
            case "10" : return R.drawable.hard_rain;
            case "11" : return R.drawable.light;
            case "13" : return R.drawable.snow;
            case "50" : return R.drawable.fog;
            default   : return R.drawable.sun_cloud;
        }
    }

    static class ViewHolder {
        TextView textViewMaxTemperature;
        TextView textViewMinTemperature;
        ImageView imageViewDescription;
        TextView textViewDate;
        TextView textViewDescription;
    }

}
