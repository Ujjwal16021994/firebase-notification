package com.example.firebase6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class intentActivity extends AppCompatActivity {
    TextView textview1,textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        textview1 = findViewById(R.id.title);
        textview2 = findViewById(R.id.text);
        String title = getIntent().getStringExtra("title");
        String text = getIntent().getStringExtra("text");
        textview1.setText(title);
        textview2.setText(text);
    }
}