package com.vladbytsyuk.weatherforecastproject.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.WeatherForecast;

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
        viewHolder.textViewDayTemperature.setText(getItem(position).getTemperature().getDayTemperature().toString());
        viewHolder.textViewNightTemperature.setText(getItem(position).getTemperature().getNightTemperature().toString());
        viewHolder.imageViewDescription.setImageResource(R.drawable.icon_menu_refresh);         // <------------------------------------- Place image here
        viewHolder.textViewDate.setText(getItem(position).getTime().getDate());
        viewHolder.textViewWeekday.setText(getItem(position).getTime().getWeekDay());
        return convertView;
    }

    private ViewHolder viewHolderInit(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewDayTemperature = (TextView) convertView.findViewById(R.id.textViewWFDayTemperature);
        viewHolder.textViewNightTemperature = (TextView) convertView.findViewById(R.id.textViewWFNightTemperature);
        viewHolder.imageViewDescription = (ImageView) convertView.findViewById(R.id.imageViewWFDescription);
        viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewWFDate);
        viewHolder.textViewWeekday = (TextView) convertView.findViewById(R.id.textViewWFWeekday);
        return viewHolder;
    }

    static class ViewHolder {
        TextView textViewDayTemperature;
        TextView textViewNightTemperature;
        ImageView imageViewDescription;
        TextView textViewDate;
        TextView textViewWeekday;
    }

}
