package com.example.flightbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenFlight extends AppCompatActivity {

    TextView flight_date, d_airport, d_schedule, a_airport, a_schedule,flight_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_flight);

        flight_date = findViewById(R.id.flight_date);
        d_airport = findViewById(R.id.d_airport);
        d_schedule = findViewById(R.id.d_schedule);
        a_airport = findViewById(R.id.a_airport);
        a_schedule = findViewById(R.id.a_schedule);
        flight_num=findViewById(R.id.flight_num);


        Intent i = getIntent();
        String id = i.getStringExtra("id");

        int flight = Integer.parseInt(id);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://api.aviationstack.com/v1/flights?access_key=e815b88dd5d02f9d1b910a7749358436";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String d_dat, d_locatio, a_dat, a_locatio, f_num,fligh_num;
                try {
                    JSONArray array = response.getJSONArray("data");
                    JSONObject object = array.getJSONObject(flight);
                    f_num = object.getString("flight_date");



                    flight_date.setText("Fligh date: " + f_num);

                    JSONObject departure = object.getJSONObject("departure");
                    JSONObject arrival = object.getJSONObject("arrival");

                    JSONObject flight=object.getJSONObject("flight");
                    fligh_num=flight.getString("number");
                    flight_num.setText("Flight Number : "+ fligh_num);

                    d_locatio = departure.getString("airport");
                    d_airport.setText("Departure location : " + d_locatio);

                    d_dat = departure.getString("scheduled");
                    d_schedule.setText("Departure Schedule: " + d_dat);

                    a_locatio = arrival.getString("airport");
                    a_airport.setText("Arrival Location : " + a_locatio);

                    a_dat = arrival.getString("scheduled");
                    a_schedule.setText("Arrival schedule: " + a_dat);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);


    }
}