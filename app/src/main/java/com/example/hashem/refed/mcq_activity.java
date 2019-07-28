package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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

public class mcq_activity extends AppCompatActivity {
    TextView qtextview;
    Content content;
    Connection conn = new Connection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);


        qtextview = findViewById(R.id.qtextview);
        Intent i = getIntent();
        String url = conn.toString() + "/refed/getContentDetails.php?oid=" + i.getExtras().get("conid").toString();
        RequestQueue queue = Volley.newRequestQueue(mcq_activity.this);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONArray jArray = new JSONArray(result);
                            for (int k=0; k < jArray.length(); k++) {
                                try {
                                    
                                    JSONObject contentjson = jArray.getJSONObject(k);
                                    content = new Content(Integer.parseInt(contentjson.getString("id")), Integer.parseInt(contentjson.getString("topicid")), contentjson.getString("qtext"), contentjson.getString("file"), Integer.parseInt(contentjson.getString("ord")), Integer.parseInt(contentjson.getString("type")), Integer.parseInt(contentjson.getString("qtype")), contentjson.getString("hint"), contentjson.getString("hintpic"));
                                    populatePage(content.getQtext(), content.getFile(), content.getHint(), content.getHintpic());
                                    //Toast.makeText(sectionsActivity.this, sections[0].toString(), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


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
                public void populatePage(String text, String file, String hint, String hintPic){


                }
}
