package com.example.asif.qrscanner;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    Button button;
    TextView Title;

    private String Data;

    private ViewPager mviewPager;
    private TabsPagerAdapter tabsPagerAdapter;
    private TabLayout mtabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.app_toolbar);
        Title=(TextView)findViewById(R.id.title) ;
        Title.setText("Qr Scanner");
        button = (Button)findViewById(R.id.Button);
        button.setText("Scan");
        button.setOnClickListener(this);

        mviewPager=(ViewPager)findViewById(R.id.viewPager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(tabsPagerAdapter);
        mtabLayout=(TabLayout)findViewById(R.id.tabLayout);
        mtabLayout.setupWithViewPager(mviewPager);
    }

    @Override
    public void onClick(View view) {
        Intent scan = new Intent(this,ScanActivity.class);
        startActivity(scan);

    }
}
