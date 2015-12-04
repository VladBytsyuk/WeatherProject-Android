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
 * Created by VladBytsyuk on 29.11.2015.
 */
public class DetailWeatherForecastAdapter extends BaseAdapter {
    Context context;
    ArrayList<WeatherForecast> weatherForecastList;

    public DetailWeatherForecastAdapter(Context context, ArrayList<WeatherForecast> weatherForecastList) {
        this.context = context;
        this.weatherForecastList = weatherForecastList;
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
        viewHolder.textViewDetailMaxTemperature.setText(getItem(position).getTemperature().getMaxTemperature());
        //TODO: Place image
        viewHolder.imageViewDetailDescription.setImageResource(R.drawable.icon_menu_refresh);
        viewHolder.textViewDetailDayTime.setText(setDayTime(position));
        return convertView;
    }

    private int setDayTime(int position) {
        switch (position) {
            case 0:
                return R.string.morning_time;
            case 1:
                return R.string.day_time;
            case 2:
                return R.string.evening_time;
            case 3:
                return R.string.night_time;
            default:
                return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public WeatherForecast getItem(int position) {
        return weatherForecastList.get(position);
    }

    @Override
    public int getCount() {
        return weatherForecastList.size();
    }

    private ViewHolder viewHolderInit(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewDetailMaxTemperature = (TextView) convertView.findViewById(R.id.textViewDetailMaxTemperature);
        viewHolder.imageViewDetailDescription = (ImageView) convertView.findViewById(R.id.imageViewDetailDescription);
        viewHolder.textViewDetailDayTime = (TextView) convertView.findViewById(R.id.textViewDetailDayTime);
        return viewHolder;
    }

    static class ViewHolder {
        TextView textViewDetailMaxTemperature;
        ImageView imageViewDetailDescription;
        TextView textViewDetailDayTime;
    }
}
