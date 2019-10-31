package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class mcqall_activity extends AppCompatActivity {

    //RadioGroup radioGroup;
    ImageView qfile;
    TextView qtextview;
    Content content;
    CheckBox cb;
    Button btn1, btn2;
    Answer[] alternatives;
    LinearLayout ll;
    Connection conn = new Connection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcqall);

                ll = findViewById(R.id.linearLayout);
                qtextview = findViewById(R.id.qtextview2);
                qfile = findViewById(R.id.qfile2);

                btn1 = findViewById(R.id.btnCheckMcqall);
                btn2 = findViewById(R.id.btnHintMcqall);

                Intent i = getIntent();
                String url = conn.toString() + "/refed/getContentDetails.php?oid=" + i.getExtras().get("conid").toString();
                RequestQueue queue = Volley.newRequestQueue(mcqall_activity.this);
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
                                else{
                                    alternatives[k-1] = new Answer(Integer.parseInt(contentjson.getString("id")),Integer.parseInt(contentjson.getString("objectid")),contentjson.getString("atext"),Integer.parseInt(contentjson.getString("ord")),Integer.parseInt(contentjson.getString("correct")));
                                }
                                //Toast.makeText(sectionsActivity.this, sections[0].toString(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                        qtextview.setText(content.getQtext());
                        Picasso.with(mcqall_activity.this).load(conn.toString() + "/refed/" + content.getFile()).into(qfile);
                        shuffleArray(alternatives);
                        for(int i = 0; i < alternatives.length ;i++){

                            cb = new CheckBox(mcqall_activity.this);
                            cb.setText(alternatives[i].getAtext());
                            ll.addView(cb);
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

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check check = new Check(2, oid, ,mcqall_activity);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });



    }


                static void shuffleArray(Answer[] ar)
                {
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