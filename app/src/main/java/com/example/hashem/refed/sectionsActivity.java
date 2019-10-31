package com.example.hashem.refed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class sectionsActivity extends AppCompatActivity {
    TextView tv;
    Connection conn = new Connection();
    Section [] sections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        final ListView sectionsList = findViewById(R.id.sectionsList);
        Intent i = getIntent();

        String url = conn.toString() + "/refed/getSections.php?mod=" + i.getExtras().get("modid").toString();
        RequestQueue queue = Volley.newRequestQueue(sectionsActivity.this);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONArray jArray = new JSONArray(result);
                            sections = new Section[jArray.length()];

                            for (int k=0; k < jArray.length(); k++)
                            {
                                try {
                                    JSONObject sec = jArray.getJSONObject(k);
                                    // Pulling items from the array
                                    sections[k] = new Section(Integer.parseInt(sec.getString("id")), Integer.parseInt(sec.getString("modid")),sec.getString("name"), Integer.parseInt(sec.getString("ord")));
                                    //Toast.makeText(sectionsActivity.this, sections[0].toString(), Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final ArrayAdapter<Section> arrayAdapter = new ArrayAdapter<Section>(sectionsActivity.this,  android.R.layout.simple_list_item_1, sections);
                        sectionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent(sectionsActivity.this, topicsActivity.class);
                                intent.putExtra("secname", arrayAdapter.getItem(position).getName());
                                intent.putExtra("secid", arrayAdapter.getItem(position).getId());
                                Toast.makeText(sectionsActivity.this, arrayAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }
                        });
                        sectionsList.setAdapter(arrayAdapter);

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
