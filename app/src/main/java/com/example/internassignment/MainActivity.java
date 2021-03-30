package com.example.internassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecycler;
    ArrayList<ListItem> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();

        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(false);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));



        String Url = "https://api.spacexdata.com/v4/crew";

        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest js = new JsonArrayRequest(
                Request.Method.GET,
                Url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String name = object.getString("name").toString();
                                String agency = object.getString("agency").toString();
                                String active = object.getString("status").toString();
                                String wiki = object.getString("wikipedia").toString();
                                String image = object.getString("image").toString();

                                Log.e("response", name);

                                list.add(new ListItem(name,active,agency,wiki,image));

                            } catch (JSONException e) {

                                Log.e("Error",e.toString());

                            }

                        }
                        mRecycler.setAdapter(new ListAdapter(MainActivity.this,list));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        );

        rq.add(js);

    }
}