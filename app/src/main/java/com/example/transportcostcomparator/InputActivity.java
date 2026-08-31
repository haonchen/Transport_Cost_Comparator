package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InputActivity extends AppCompatActivity {
    private EditText mode, distance, cost, days;
    private Spinner type;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_input);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar)); getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mode = findViewById(R.id.etMode); distance = findViewById(R.id.etDistance); cost = findViewById(R.id.etCost); days = findViewById(R.id.etDays); type = findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.transport_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); type.setAdapter(adapter);
        findViewById(R.id.btnClear).setOnClickListener(v -> { mode.setText(""); distance.setText(""); cost.setText(""); days.setText(""); type.setSelection(0); });
        findViewById(R.id.btnCalculateSave).setOnClickListener(v -> calculateAndSave());
    }
    private void calculateAndSave() {
        if (TextUtils.isEmpty(mode.getText().toString().trim())) { mode.setError("Mode of transport is required"); mode.requestFocus(); return; }
        if (TextUtils.isEmpty(distance.getText()) || TextUtils.isEmpty(cost.getText()) || TextUtils.isEmpty(days.getText())) { Toast.makeText(this, "Please complete all numeric fields.", Toast.LENGTH_SHORT).show(); return; }
        try {
            double distanceValue = Double.parseDouble(distance.getText().toString()); double costValue = Double.parseDouble(cost.getText().toString()); int daysValue = Integer.parseInt(days.getText().toString());
            if (distanceValue <= 0 || costValue < 0 || daysValue <= 0) throw new NumberFormatException();
            TransportCalculator calculator = new TransportCalculator();
            double daily = calculator.calculateDailyTransportCost(distanceValue, costValue);
            double monthly = calculator.calculateMonthlyTransportCost(daily, daysValue);
            double monthlyDistance = calculator.calculateMonthlyTravelDistance(distanceValue, daysValue);
            Transport transport = new Transport(mode.getText().toString().trim(), type.getSelectedItem().toString(), distanceValue, costValue, daysValue, daily, monthly, monthlyDistance);
            long id = new DatabaseHelper(this).saveTransport(transport);
            if (id == -1) { Toast.makeText(this, "Could not save calculation.", Toast.LENGTH_SHORT).show(); return; }
            startActivity(new Intent(this, ResultsActivity.class).putExtra("CALCULATION_ID", id));
        } catch (NumberFormatException e) { Toast.makeText(this, "Distance, cost per kilometre and travel days must be valid numeric values.", Toast.LENGTH_LONG).show(); }
    }
    @Override public boolean onSupportNavigateUp() { finish(); return true; }
}
