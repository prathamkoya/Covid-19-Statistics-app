package com.example.retro_fit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Detail_Activity extends AppCompatActivity {
    TextView countryName,totalCases,casesToday,deaths,todayDeaths,recovered,todayRecovered,activePerMill,recoveredPerMill,activecases;
    public int positionCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        findID();
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of "+Countries.country_modelList.get(positionCountry).getCountries());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DecimalFormat df = new DecimalFormat();

        String countryName1 = Countries.country_modelList.get(positionCountry).getCountries();
        countryName.setText(countryName1);

        String totalCases1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getCases()));
        totalCases.setText(totalCases1);

        String casesToday1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getTodayCases()));
        casesToday.setText(casesToday1);

        String deaths1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getDeaths()));
        deaths.setText(deaths1);

        String todayDeaths1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getTodayDeaths()));
        todayDeaths.setText(todayDeaths1);

        String activecases1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getActive()));
        activecases.setText(activecases1);

        String recovered1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getRecovered()));
        recovered.setText(recovered1);

        String todayRecovered1 = df.format(Integer.parseInt(Countries.country_modelList.get(positionCountry).getTodayRecovered()));
        todayRecovered.setText(todayRecovered1);

        activePerMill.setText(Countries.country_modelList.get(positionCountry).getActive_permillion());

        recoveredPerMill.setText(Countries.country_modelList.get(positionCountry).getRecovered_permillion());




    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    private void findID()
    {
        countryName = findViewById(R.id.country__Name);
        totalCases = findViewById(R.id.cases_total_no);
        casesToday = findViewById(R.id.cases_today_no);
        deaths = findViewById(R.id.deaths_intotal);
        todayDeaths=findViewById(R.id.deaths_today_no);
        recovered = findViewById(R.id.cases_recovered_no);
        todayRecovered = findViewById(R.id.cases_todayRecovered_no);
        activePerMill = findViewById(R.id.cases_activePerMill_no);
        recoveredPerMill = findViewById(R.id.cases_recoveredPerMill_no);
        activecases = findViewById(R.id.cases_active_no);

    }

}