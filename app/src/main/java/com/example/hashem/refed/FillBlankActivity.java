package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hashem.refed.FunctionsClasses.Check;
import com.example.hashem.refed.FunctionsClasses.ShowHint;
import com.example.hashem.refed.Models.Answer;
import com.example.hashem.refed.Models.Connection;
import com.example.hashem.refed.Models.Content;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;


public class FillBlankActivity extends AppCompatActivity {
    int oid;
    ImageView qfile;
    TextView qtextview;
    Content content;
    Answer[] alternatives;
    EditText et;
    Button btn1;
    Button btn2;
    Connection conn = new Connection();
    Check check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillblank);
        btn1 = findViewById(R.id.btnCheckBlank);
        btn2 = findViewById(R.id.btnHintBlank);
        qtextview = findViewById(R.id.qtextview3);
        qfile = findViewById(R.id.qfile3);
        et = findViewById(R.id.answerText);

        final Intent i = getIntent();

        String url = conn.toString() + "/refed/getContentDetails.php?oid=" + i.getExtras().get("conid").toString();
        RequestQueue queue = Volley.newRequestQueue(FillBlankActivity.this);
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

                            }
                            oid = Integer.parseInt(i.getExtras().get("conid").toString());
                            //check = new Check(3, oid, et.getText().toString(), FillBlankActivity.this);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        qtextview.setText(content.getQtext());
                        Picasso.with(FillBlankActivity.this).load(conn.toString() + "/refed/" + content.getFile()).into(qfile);

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

                check = new Check(3, oid, et.getText().toString(), FillBlankActivity.this);
                check.start();

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowHint sh = new ShowHint(oid, FillBlankActivity.this);
                sh.start();

            }
        });
    }
}