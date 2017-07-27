package com.chadbet.codechallenge.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chadbet.codechallenge.Network.NetworkRequest;
import com.chadbet.codechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button queryButton;
    private Button deleteButton;

    private List<GObject> gObjectList = new ArrayList<>();

    private RecyclerView recyclerView;
    private GListAdapter gListAdapter;

    private String name;
    private String startDate;
    private String endDate;
    private String url;
    private String venue;
    private String city;
    private String state;
    private String iconURL;

    private final String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryButton = (Button) findViewById(R.id.button_query);
        deleteButton = (Button) findViewById(R.id.button_delete);
        recyclerView = (RecyclerView) findViewById(R.id.rv_g_queries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });



        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryAPI();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gObjectList.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Oops, you should try querying the API first",
                            Toast.LENGTH_LONG).show();

                }
                gObjectList.clear();
                gListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void queryAPI() {
        try {
            NetworkRequest.httpGET(URL, new NetworkRequest.HttpGETCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("Json Response", response.toString());
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for(int i = 0; i < jsonArray.length() -1; i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            name = obj.getString("name");
                            startDate = obj.getString("startDate");
                            endDate = obj.getString("endDate");
                            url = obj.getString("url");

                            //it appears venue is empty
                            iconURL = obj.getString("icon");
                            addGObject(new GObject(name, startDate, endDate, url,
                                    venue, city, state, iconURL));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    gListAdapter = new GListAdapter(getApplicationContext(), gObjectList);
                    recyclerView.setAdapter(gListAdapter);
                }

                @Override
                public void onError(Throwable t) {
                    Log.d("Volley Error", t.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGObject(GObject gObject) {
        if (gObject == null) {
            return;
        }
        gObjectList.add(gObject);
    }

}
