package com.example.retro_fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Script;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView active,total_cases,total_recovered,total_deaths,critical,today_cases,today_recovered,today_deaths,affected_countries;
    SimpleArcLoader arcLoader;
    PieChart pieChart;
    ScrollView scrollView;
    DecimalFormat df = new DecimalFormat("##,###,###.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            findView();
            fetchData();
    }

    private void fetchData()
    {
        String url="https://disease.sh/v3/covid-19/all";
        arcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonObject =new JSONObject(response.toString());

                    int active_int = Integer.parseInt(jsonObject.getString("active"));
                    String active1 = df.format(Integer.parseInt(jsonObject.getString("active")));
                    active.setText(active1);

                    String today_cases1 = df.format(Integer.parseInt((jsonObject.getString("todayCases"))));
                    today_cases.setText(today_cases1);

                    String total_deaths1 = df.format(Integer.parseInt((jsonObject.getString("deaths"))));
                    total_deaths.setText(total_deaths1);

                    int total_cases_int = Integer.parseInt((jsonObject.getString("cases")));
                    String total_cases1 = df.format(Integer.parseInt((jsonObject.getString("cases"))));
                    total_cases.setText(total_cases1);

                    int total_deaths_int = Integer.parseInt((jsonObject.getString("todayDeaths")));
                    String today_deaths1 = df.format(Integer.parseInt((jsonObject.getString("todayDeaths"))));
                    today_deaths.setText(today_deaths1);

                    int total_recovered_int = Integer.parseInt((jsonObject.getString("recovered")));
                    String total_recovered1 =  df.format(Integer.parseInt((jsonObject.getString("recovered"))));
                    total_recovered.setText(total_recovered1);

                    String today_recovered1 = df.format(Integer.parseInt((jsonObject.getString("todayRecovered"))));
                    today_recovered.setText(today_recovered1);


                    String critical1 = df.format(Integer.parseInt((jsonObject.getString("critical"))));
                    critical.setText(critical1);

                    String affected_countries1 = df.format(Integer.parseInt((jsonObject.getString("affectedCountries"))));
                    affected_countries.setText(affected_countries1);




                    pieChart.addPieSlice(new PieModel("Cases",total_cases_int, Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Recovered",total_recovered_int, Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("Deaths",total_deaths_int, Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Active",active_int, Color.parseColor("#29B6F6")));
                    pieChart.startAnimation();


                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"Some Error Occured", Toast.LENGTH_LONG).show();
                Log.d("Error",error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTraackCountries(View view)
    {
        Intent intent = new Intent(this,Countries.class);
        startActivity(intent);
    }
    public void findView()
    {
        active = findViewById(R.id.total_active);
        total_cases = findViewById(R.id.total_cases);
        total_recovered = findViewById(R.id.total_recovered);
        total_deaths = findViewById(R.id.total_deaths);
        critical = findViewById(R.id.total_critical);
        today_cases= findViewById(R.id.today_cases);
        affected_countries = findViewById(R.id.afftected_countries);
        today_recovered=findViewById(R.id.today_recovered);
        today_deaths=findViewById(R.id.today_deaths);

        scrollView = findViewById(R.id.Scroll_View);

        arcLoader = findViewById(R.id.loader);
        pieChart = findViewById(R.id.stackedbarchart);


    }
}