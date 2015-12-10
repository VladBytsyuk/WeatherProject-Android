package com.vladbytsyuk.weatherforecastproject.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.DBManager;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class SettingsFragment extends Fragment {
    EditText editTextCity;
    Button buttonSaveCity;
    Spinner spinnerMetric;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        rootView = viewInit(rootView);
        return rootView;
    }

    private View viewInit(View rootView) {
        rootView = editTextCityInit(rootView);
        rootView = buttonSaveCityInit(rootView);
        rootView = spinnerMetricInit(rootView);
        return rootView;
    }

    private View editTextCityInit(View rootView) {
        editTextCity = (EditText) rootView.findViewById(R.id.editTextSettingsCity);
        return rootView;
    }
    private View buttonSaveCityInit(View rootView) {
        buttonSaveCity = (Button) rootView.findViewById(R.id.buttonSettingsSaveCity);
        buttonSaveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editTextCity.getText().toString();
                setSharedPreference(R.string.city, city);
            }
        });
        return rootView;
    }
    private View spinnerMetricInit(View rootView) {
        spinnerMetric = (Spinner) rootView.findViewById(R.id.spinnerSettingsMetric);
        final String[] metrics = getResources().getStringArray(R.array.metrics);
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, metrics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetric.setAdapter(adapter);
        spinnerMetric.setPrompt(getResources().getString(R.string.metric_title));
        if (getSharedPreference(R.string.metric).equals(getString(R.string.celsium))) {
            spinnerMetric.setSelection(0);
        } else {
            spinnerMetric.setSelection(1);
        }
        spinnerMetric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentMetric = adapter.getItem(position);
                if (currentMetric.equals(metrics[0])) {
                    setSharedPreference(R.string.metric, getActivity().getString(R.string.celsium));
                } else {
                    setSharedPreference(R.string.metric, getActivity().getString(R.string.farenheit));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }


    private void setSharedPreference(int key, String value) {
        SharedPreferences settings = getActivity()
                .getSharedPreferences(getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(key), value).apply();
    }

    private String getSharedPreference(int preference) {
        SharedPreferences settings = getActivity()
                .getSharedPreferences(getActivity().getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(this.getString(preference), null);
    }
}
