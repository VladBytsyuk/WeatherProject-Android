package com.vladbytsyuk.weatherforecastproject.fragments;

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

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class SettingsFragment extends Fragment {
    EditText editTextCity;
    Button buttonSaveCity;
    Spinner spinnerMetric;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                putSharedPreference(R.string.city, city);
            }
        });
        return rootView;
    }
    private View spinnerMetricInit(View rootView) {
        spinnerMetric = (Spinner) rootView.findViewById(R.id.spinnerSettingsMetric);
        String[] metrics = {"C", "F"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, metrics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetric.setAdapter(adapter);
        spinnerMetric.setPrompt("Metric: ");
        spinnerMetric.setSelection(0);
        spinnerMetric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                putSharedPreference(R.string.metric, adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }


    private void putSharedPreference(int key, String value) {
        SharedPreferences settings = getActivity().getSharedPreferences(getString(R.string.settings), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(key), value).apply();
    }
}
