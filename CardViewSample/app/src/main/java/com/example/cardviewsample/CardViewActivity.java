package com.example.cardviewsample;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, CardViewFragment.newInstance())
                    .commit();
        }
    }
}