package com.example.wpzhao_mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements AddVisitDialogListener{
    // MainActivity is responsible for storing information of the user as well as interaction with the user using the app

    private User user;
    private ListView visitList;
    private VisitArrayAdapter visitAdapter;
    private AddVisitFragment firstFrag;

    private DecimalFormat df = new DecimalFormat("0.00");


    @Override
    public void addVisit(Visit visit){
        visitAdapter.add(visit);
        visitAdapter.notifyDataSetChanged();
        user.addStation(visit);
        updateUser();
    }

    @Override
    public void editVisit(Visit visit, String name, Date date, String type, int amount, float price){
        visitAdapter.remove(visit);
        visitAdapter.add(new Visit(name, date, type, amount, price));
        visitAdapter.notifyDataSetChanged();
        user.editDetails();
        updateUser();
    }

    @Override
    public void deleteVisit(Visit visit){
        visitAdapter.remove(visit);
        visitAdapter.notifyDataSetChanged();
        user.deleteStation(visit);
        updateUser();
    }

    // update the total carbon footprint and the fuel cost of the user
    public void updateUser(){
        // find the view and set the values and set it to the screen
        TextView totalFuelCost = (TextView) findViewById(R.id.total_fuel_cost_text);
        TextView totalCarbonFootprint = (TextView) findViewById(R.id.total_carbon_footprint_txt);

        // change it to two decimal format
        String finalValue = df.format(user.getTotalFuelCost());
        totalFuelCost.setText(finalValue);
        totalCarbonFootprint.setText(String.valueOf(user.getTotalCarbonFootprint()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the user
        user = new User();

        visitList = findViewById(R.id.visit_list);
        visitAdapter = new VisitArrayAdapter(this, user.visits);
        visitList.setAdapter(visitAdapter);

        FloatingActionButton fab = findViewById(R.id.add_vist_button);
        fab.setOnClickListener(v -> {
            new AddVisitFragment().show(getSupportFragmentManager(), "Add Visit");
        });

        // set the on click for list view item movement
        visitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visit visit = (Visit) parent.getItemAtPosition(position);
                // create a new one and update
                firstFrag = firstFrag.newInstance(visit);
                firstFrag.show(getSupportFragmentManager(), "Add Visit");
            }
        });
    }
}