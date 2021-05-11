package com.example.retro_fit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries extends AppCompatActivity {

    EditText edt_search;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<Country_Model> country_modelList = new ArrayList<>();
    Country_Model country_model;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        edt_search = findViewById(R.id.search_edt);
        listView = findViewById(R.id.list_countries);
        simpleArcLoader = findViewById(R.id.arcloader);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fetchData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                startActivity(new Intent(Countries.this,Detail_Activity.class).putExtra("position",position));
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            myAdapter.getFilter().filter(s);
            myAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData()
    {
        String url="https://disease.sh/v3/covid-19/countries";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       String countryName =  jsonObject.getString("country");
                       String cases = jsonObject.getString("cases");
                       String todayCases = jsonObject.getString("todayCases");
                       String deaths = jsonObject.getString("deaths");
                       String todayDeaths = jsonObject.getString("todayDeaths");
                       String active = jsonObject.getString("active");
                       String recovered = jsonObject.getString("recovered");
                       String todayRecovered = jsonObject.getString("todayRecovered");
                       String activePerOneMillion = jsonObject.getString("activePerOneMillion");
                       String recoveredPerOneMillion = jsonObject.getString("recoveredPerOneMillion");

                       JSONObject jsonObject1 = jsonObject.getJSONObject("countryInfo");
                       String flagUrl = jsonObject1.getString("flag");

                       country_model = new Country_Model(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,recovered,todayRecovered,active,activePerOneMillion,recoveredPerOneMillion);
                       country_modelList.add(country_model);
                    }
                    myAdapter = new MyAdapter(Countries.this,country_modelList);
                    listView.setAdapter(myAdapter);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Log.d("Error",error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}