package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class mcq_activity extends AppCompatActivity {
    RadioGroup radioGroup;
    ImageView qfile;
    TextView qtextview;
    Content content;
    RadioButton rb;
    Answer[] alternatives;
    Connection conn = new Connection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);
        radioGroup = findViewById(R.id.alts_group);
        qtextview = findViewById(R.id.qtextview);
        qfile = findViewById(R.id.qfile);
        Intent i = getIntent();
        String url = conn.toString() + "/refed/getContentDetails.php?oid=" + i.getExtras().get("conid").toString();
        RequestQueue queue = Volley.newRequestQueue(mcq_activity.this);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONArray jArray = new JSONArray(result);
                            alternatives = new Answer[jArray.length() - 1];
                            for (int k=0; k < jArray.length(); k++) {

                                    JSONObject contentjson = jArray.getJSONObject(k);

                                    if(k == 0){
                                    content = new Content(Integer.parseInt(contentjson.getString("id")), Integer.parseInt(contentjson.getString("topicid")), contentjson.getString("qtext"), contentjson.getString("file"), Integer.parseInt(contentjson.getString("ord")), Integer.parseInt(contentjson.getString("type")), Integer.parseInt(contentjson.getString("qtype")), contentjson.getString("hint"), contentjson.getString("hintpic"));}
                                    else {
                                    alternatives[k-1] = new Answer(Integer.parseInt(contentjson.getString("id")),Integer.parseInt(contentjson.getString("objectid")),contentjson.getString("atext"),Integer.parseInt(contentjson.getString("ord")),Integer.parseInt(contentjson.getString("correct")));
                                    }
                                    //Toast.makeText(sectionsActivity.this, sections[0].toString(), Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        qtextview.setText(content.getQtext());
                        Picasso.with(mcq_activity.this).load(conn.toString() + "/refed/" + content.getFile()).into(qfile);

                        shuffleArray(alternatives);
                        for(int i = 0; i < alternatives.length ;i++){

                            rb = new RadioButton(mcq_activity.this);
                            rb.setText(alternatives[i].getAtext());
                            radioGroup.addView(rb);
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
            static void shuffleArray(Answer[] ar)
            {
                // If running on Java 6 or older, use `new Random()` on RHS here
                Random rnd = new Random();
                for (int i = ar.length - 1; i > 0; i--)
                {
                    int index = rnd.nextInt(i + 1);
                    // Simple swap
                    Answer a = ar[index];
                    ar[index] = ar[i];
                    ar[i] = a;
                }
            }
}