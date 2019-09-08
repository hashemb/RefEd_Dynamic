package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class topicsActivity extends AppCompatActivity {


    Connection conn = new Connection();
    Topic [] topics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        final ListView topicsList = findViewById(R.id.topicsList);
        Intent i = getIntent();
        String url = conn.toString() + "/refed/getTopics.php?secid=" + i.getExtras().get("secid").toString();
        RequestQueue queue = Volley.newRequestQueue(topicsActivity.this);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONArray jArray = new JSONArray(result);
                            topics = new Topic[jArray.length()];

                            for (int k=0; k < jArray.length(); k++)
                            {
                                try {
                                    JSONObject sec = jArray.getJSONObject(k);
                                    // Pulling items from the array
                                    topics[k] = new Topic(Integer.parseInt(sec.getString("id")), sec.getString("name"),Integer.parseInt(sec.getString("ord")), Integer.parseInt(sec.getString("secid")));
                                    //ArrayList<Topic> topicsArr = new ArrayList<>(Arrays.asList(topics));
                                    //Toast.makeText(topicsActivity.this, topics[k].toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    catch (JSONException e) {
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final ArrayAdapter<Topic> arrayAdapter = new ArrayAdapter<Topic>(topicsActivity.this,  android.R.layout.simple_list_item_1, topics);
                        topicsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(topicsActivity.this, contentsActivity.class);
                                intent.putExtra("topid", arrayAdapter.getItem(position).getId());
                                intent.putExtra("topname", arrayAdapter.getItem(position).getName());
                                //Toast.makeText(topicsActivity.this, arrayAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        });
                        topicsList.setAdapter(arrayAdapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                    }
                });
        queue.add(jsonRequest);
    }
}
