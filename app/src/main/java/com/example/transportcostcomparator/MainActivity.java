package com.example.transportcostcomparator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.btnStart).setOnClickListener(v -> startActivity(new Intent(this, InputActivity.class)));
        findViewById(R.id.btnExit).setOnClickListener(v -> finishAffinity());
        textToSpeech = new TextToSpeech(this, status -> { if (status == TextToSpeech.SUCCESS) textToSpeech.setLanguage(Locale.getDefault()); });
        findViewById(R.id.btnListen).setOnClickListener(v -> textToSpeech.speak(getString(R.string.welcome_message), TextToSpeech.QUEUE_FLUSH, null, "welcome"));
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) { getMenuInflater().inflate(R.menu.main_menu, menu); return true; }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_home) { startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)); return true; }
        if (item.getItemId() == R.id.menu_details) { startActivity(new Intent(this, InputActivity.class)); return true; }
        Toast.makeText(this, item.getItemId() == R.id.menu_reports ? "Reports are saved in the application database." : "Enter your transport details, then calculate and save.", Toast.LENGTH_LONG).show();
        return true;
    }
    @Override protected void onDestroy() { if (textToSpeech != null) { textToSpeech.stop(); textToSpeech.shutdown(); } super.onDestroy(); }
}
