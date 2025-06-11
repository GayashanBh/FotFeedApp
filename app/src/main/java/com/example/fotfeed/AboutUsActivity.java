package com.example.fotfeed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    private TextView navHome, navAbout, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        navHome = findViewById(R.id.navHome);
        navAbout = findViewById(R.id.navAbout);
        navProfile = findViewById(R.id.navProfile);

        navHome.setOnClickListener(v -> {
            startActivity(new Intent(AboutUsActivity.this, NewsActivity.class));
            finish();
        });

        navAbout.setOnClickListener(v -> {
            // Stay here
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(AboutUsActivity.this, ProfileActivity.class));
            finish();
        });
    }
}
