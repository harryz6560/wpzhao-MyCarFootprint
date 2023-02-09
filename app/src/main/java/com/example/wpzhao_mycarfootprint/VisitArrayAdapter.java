package com.example.wpzhao_mycarfootprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class VisitArrayAdapter extends ArrayAdapter<Visit> {
    //VisitArrayAdapter is responsible adapting the visits arrays into the views

    // for setting the date
    private DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DecimalFormat df = new DecimalFormat("0.00");
    public VisitArrayAdapter(Context context, ArrayList<Visit> visits) {
        super(context, 0, visits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(super.getContext()).inflate(R.layout.content, parent, false);
        } else {
            view = convertView;
        }
        Visit visit = super.getItem(position);

        // find all the text views
        TextView gasStation = view.findViewById(R.id.gas_station_name_text);
        TextView fuelType = view.findViewById(R.id.fuel_type_name_text);
        TextView carbonFootprint = view.findViewById(R.id.carbon_footprint_text);
        TextView fuel_cost = view.findViewById(R.id.fuel_cost_text);
        TextView date = view.findViewById(R.id.date_text);

        // set all the given data to the views
        gasStation.setText(visit.getStationName());
        fuelType.setText(visit.getFuelType());
        int rounded_footprint = (int) Math.round(visit.getCarbonFootprint());
        carbonFootprint.setText(String.valueOf(rounded_footprint));

        // change it to two decimal format
        String finalValue = df.format(visit.getFuelCost());
        fuel_cost.setText(finalValue);
        date.setText(formatDate.format(visit.getDate()));

        return view;
    }
}
