package com.example.nojinx2.nangnok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nojinx2.nangnok.fragment.ChartFragment;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        chartFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, chartFragment).commit();
    }
}
