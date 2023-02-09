package com.example.wpzhao_mycarfootprint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AddVisitFragment extends DialogFragment{
    /* AddVisitFragment is responsible for presenting the fragment that
    the user can enter the information about their visit */
    private EditText editGasStationName;
    private EditText editFuelType;
    private EditText editAmount;
    private EditText editPricePerLitre;
    private EditText editDate;
    private AddVisitDialogListener listener;

    private DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddVisitDialogListener) {
            listener = (AddVisitDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement AddCityListener");
        }
    }

    // to create a new instance of fragment to save and return the Visit that the user choose
    static AddVisitFragment newInstance(Visit visit){
        Bundle args = new Bundle();
        args.putSerializable("visit", visit);
        AddVisitFragment fragment = new AddVisitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_visit, null);
        editGasStationName = view.findViewById(R.id.edit_text_gas_station_text);
        editFuelType = view.findViewById(R.id.edit_text_fuel_type_text);
        editAmount = view.findViewById(R.id.edit_text_amount_text);
        editPricePerLitre = view.findViewById(R.id.edit_text_price_per_litre_text);
        editDate = view.findViewById(R.id.edit_text_date_text);

        if (getArguments() != null){
            Visit visit = (Visit) getArguments().getSerializable("visit");
            editGasStationName.setText(visit.getStationName());
            editFuelType.setText(visit.getFuelType());
            editAmount.setText(String.valueOf(visit.getAmount()));
            editPricePerLitre.setText(String.valueOf(visit.getPrice()));
            editDate.setText(formatDate.format(visit.getDate()));

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/edit")
                .setNegativeButton("Cancel", null)
                .setNeutralButton("Delete", (dialog, which) -> {
                    // call the methode and delete the visit
                    if (getArguments() != null) {
                        listener.deleteVisit((Visit) getArguments().getSerializable("visit"));
                    }
                })
                .setPositiveButton("Add/update", (dialog, which) -> {
                    // create the add/update button while catching errors
                    boolean noError = true;

                    String gasStationName = editGasStationName.getText().toString();
                    String fuelType = editFuelType.getText().toString();

                    if (fuelType.equalsIgnoreCase("Gasoline") || fuelType.equalsIgnoreCase("Diesel")){
                        // the user entered the right ones
                    }
                    else{
                        noError = false;
                        Toast.makeText(getActivity(), "Please Enter Gasoline or Diesel for the fuel type!",
                                Toast.LENGTH_LONG).show();
                    }

                    // see if the amount entered is a integer
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(editAmount.getText().toString());
                    } catch (Exception e) {
                        noError = false;
                        Toast.makeText(getActivity(), "The amount entered is not in the correct format!",
                                Toast.LENGTH_LONG).show();
                    }

                    // see if the price entered is a float
                    float pricePerLitre = 0;
                    try {
                        pricePerLitre = Float.parseFloat(editPricePerLitre.getText().toString());
                    } catch (Exception e) {
                        noError = false;
                        Toast.makeText(getActivity(), "The price per litre entered is not in the correct format!",
                                Toast.LENGTH_LONG).show();
                    }

                    // see if the date entered is in the correct format
                    Date date;
                    try {
                        date = formatDate.parse(editDate.getText().toString());
                    } catch (ParseException e) {
                        // the user did not enter the correct date
                        // we auto take the current date for them
                        date = new Date();
                        Toast.makeText(getActivity(), "The date entered is not in the correct format! so we auto filled for you :)",
                                Toast.LENGTH_LONG).show();
                    }

                    // if there was no errors we will add or edit
                    if (noError){
                        // if there is no returning arguments then it means
                        // that we have clicked on a visit object and trying edit it
                        if (getArguments() != null){
                            listener.editVisit((Visit) getArguments().getSerializable("visit"), gasStationName, date, fuelType, amount, pricePerLitre);
                        }
                        else {
                            listener.addVisit(new Visit(gasStationName, date, fuelType, amount, pricePerLitre));
                        }
                    }
                })

                .create();
    }
}
