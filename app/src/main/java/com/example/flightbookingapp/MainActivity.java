package com.example.flightbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.flightbookingapp.Model.SavedFlight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity<doubleBackToExitPressedOnce> extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView search_button;
    EditText search_text;

    LinearLayoutCompat search_layout,all_flights_layout;

    TextView flight_date, d_airport, d_schedule, a_airport, a_schedule,flight_nu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SavedFlight> list = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        search_button = findViewById(R.id.search_button);
        search_text = findViewById(R.id.search_text);

        flight_date = findViewById(R.id.flight_date);
        d_airport = findViewById(R.id.d_airport);
        d_schedule = findViewById(R.id.d_schedule);
        a_airport = findViewById(R.id.a_airport);
        a_schedule = findViewById(R.id.a_schedule);
        flight_nu=findViewById(R.id.flight_num);

        search_layout=findViewById(R.id.search_output);
        all_flights_layout=findViewById(R.id.all_flights_layout);

        String url = "http://api.aviationstack.com/v1/flights?access_key=e815b88dd5d02f9d1b910a7749358436";

        RequestQueue queue = Volley.newRequestQueue(this);

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    search_button.setVisibility(View.VISIBLE);
                else {
                    search_button.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final boolean[] found = {false};
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = search_text.getText().toString().trim();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String d_dat, d_locatio, a_dat, a_locatio, flight_dat,fligh_num;
                        try {
                            JSONArray array = response.getJSONArray("data");
                            for (int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);

                                JSONObject flight=object.getJSONObject("flight");
                                String flighnumber=flight.getString("number");

                                //search with flight number

                                if (flighnumber.equals(input)){

                                    all_flights_layout.setVisibility(View.GONE);
                                    search_layout.setVisibility(View.VISIBLE);
                                    flight_nu.setText("Flight number : "+ flighnumber);

                                    flight_dat = object.getString("flight_date");
                                    JSONObject departure = object.getJSONObject("departure");
                                    JSONObject arrival = object.getJSONObject("arrival");

                                    flight_date.setText("Flight Date: "+flight_dat);

                                    d_locatio = departure.getString("airport");

                                    d_airport.setText("Departure : "+ d_locatio);
                                    d_dat = departure.getString("scheduled");
                                    d_schedule.setText("Departure Date : "+d_dat);
                                    a_locatio = arrival.getString("airport");
                                    a_airport.setText("Arrival :"+a_locatio);
                                    a_dat = arrival.getString("scheduled");
                                    a_schedule.setText("Arrival Date : "+a_dat);

                                    found[0] =true;
                                    break;

                                }
                            }

                            //search with source
                            if (found[0]==false){
                                for (int i=0;i<array.length();i++){
                                    JSONObject object=array.getJSONObject(i);

                                    JSONObject flight=object.getJSONObject("flight");
                                    String flighnumber=flight.getString("number");

                                    JSONObject depar=object.getJSONObject("departure");
                                    String src=depar.getString("airport");

                                    if (src.equals(input)){

                                        all_flights_layout.setVisibility(View.GONE);
                                        search_layout.setVisibility(View.VISIBLE);

                                        flight_nu.setText("Flight number : "+ flighnumber);

                                        flight_dat = object.getString("flight_date");
                                        JSONObject departure = object.getJSONObject("departure");
                                        JSONObject arrival = object.getJSONObject("arrival");

                                        flight_date.setText("Flight Date: "+flight_dat);

                                        d_locatio = departure.getString("airport");

                                        d_airport.setText("Departure : "+ d_locatio);
                                        d_dat = departure.getString("scheduled");
                                        d_schedule.setText("Departure Date : "+d_dat);
                                        a_locatio = arrival.getString("airport");
                                        a_airport.setText("Arrival :"+a_locatio);
                                        a_dat = arrival.getString("scheduled");
                                        a_schedule.setText("Arrival Date : "+a_dat);

                                        found[0] =true;
                                        break;

                                    }
                                }
                            }

                            //search with destination
                            if (found[0]==false){
                                for (int i=0;i<array.length();i++){
                                    JSONObject object=array.getJSONObject(i);

                                    JSONObject flight=object.getJSONObject("flight");
                                    String flighnumber=flight.getString("number");

                                    JSONObject depar=object.getJSONObject("arrival");
                                    String src=depar.getString("airport");

                                    if (src.equals(input)){

                                        all_flights_layout.setVisibility(View.GONE);
                                        search_layout.setVisibility(View.VISIBLE);

                                        flight_nu.setText("Flight number : "+ flighnumber);

                                        flight_dat = object.getString("flight_date");
                                        JSONObject departure = object.getJSONObject("departure");
                                        JSONObject arrival = object.getJSONObject("arrival");

                                        flight_date.setText("Flight Date: "+flight_dat);

                                        d_locatio = departure.getString("airport");

                                        d_airport.setText("Departure : "+ d_locatio);
                                        d_dat = departure.getString("scheduled");
                                        d_schedule.setText("Departure Date : "+d_dat);
                                        a_locatio = arrival.getString("airport");
                                        a_airport.setText("Arrival :"+a_locatio);
                                        a_dat = arrival.getString("scheduled");
                                        a_schedule.setText("Arrival Date : "+a_dat);

                                        found[0] =true;
                                        break;

                                    }
                                }

                            }
                            found[0]=false;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);

            }
        });



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String d_date, d_location, a_date, a_location, id, f_num;
                try {
                    JSONArray array = response.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        JSONObject departure = object1.getJSONObject("departure");
                        JSONObject arrival = object1.getJSONObject("arrival");
                        JSONObject flight = object1.getJSONObject("flight");

                        f_num = flight.getString("number");

                        d_location = departure.getString("airport");
                        d_date = departure.getString("scheduled");

                        a_location = arrival.getString("airport");
                        a_date = arrival.getString("scheduled");

                        id = String.valueOf(i);

                        SavedFlight filght = new SavedFlight(d_date, a_date, id, d_location, a_location, f_num);

                        list.add(filght);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                mAdapter = new ExampleAdapter(list);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Intent i = new Intent(getApplicationContext(), OpenFlight.class);
                        i.putExtra("id", list.get(position).getId());
                        startActivity(i);

                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
            return;
        }else {
            all_flights_layout.setVisibility(View.VISIBLE);
            search_layout.setVisibility(View.GONE);
 }

        mBackPressed = System.currentTimeMillis();

       // super.onBackPressed();
    }

}