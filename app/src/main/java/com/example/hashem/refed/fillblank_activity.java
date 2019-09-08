package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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


public class fillblank_activity extends AppCompatActivity {

    ImageView qfile;
    TextView qtextview;
    Content content;
    Answer[] alternatives;
    EditText et;
    Connection conn = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillblank);

        qtextview = findViewById(R.id.qtextview3);
        qfile = findViewById(R.id.qfile3);
        et = findViewById(R.id.answerText);
        Intent i = getIntent();
        String url = conn.toString() + "/refed/getContentDetails.php?oid=" + i.getExtras().get("conid").toString();
        RequestQueue queue = Volley.newRequestQueue(fillblank_activity.this);
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
                        Picasso.with(fillblank_activity.this).load(conn.toString() + "/refed/" + content.getFile()).into(qfile);

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
