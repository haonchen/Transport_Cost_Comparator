package com.example.transportcostcomparator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_results);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar)); getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Transport t = new DatabaseHelper(this).getTransport(getIntent().getLongExtra("CALCULATION_ID", -1));
        if (t == null) { finish(); return; }
        Recommendation recommendation = new Recommendation();
        setText(R.id.tvMode, "Mode of Transport: " + t.getModeOfTransport()); setText(R.id.tvType, "Transport Type: " + t.getTransportType());
        setText(R.id.tvDailyCost, String.format(Locale.getDefault(), "Daily Transport Cost: R%.2f", t.getDailyCost()));
        setText(R.id.tvMonthlyCost, String.format(Locale.getDefault(), "Monthly Transport Cost: R%.2f", t.getMonthlyCost()));
        setText(R.id.tvMonthlyDistance, String.format(Locale.getDefault(), "Monthly Distance Travelled: %.2f km", t.getMonthlyDistance()));
        setText(R.id.tvCategory, "Transport Cost Category: " + recommendation.getCostCategory(t.getMonthlyCost()));
        setText(R.id.tvRecommendation, "Recommendation: " + recommendation.getRecommendation(t.getMonthlyCost()));
        findViewById(R.id.btnAgain).setOnClickListener(v -> startActivity(new Intent(this, InputActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        findViewById(R.id.btnExit).setOnClickListener(v -> finishAffinity());
    }
    private void setText(int id, String value) { ((TextView) findViewById(id)).setText(value); }
    @Override public boolean onSupportNavigateUp() { startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)); return true; }
}
