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

public class contentsActivity extends AppCompatActivity {
    ListView contentsList;
    Content[] contents;
    Connection conn = new Connection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        contentsList = (ListView) findViewById(R.id.contentsList);
        Intent i = getIntent();

        String url = conn.toString() + "/refed/getContents.php?tid=" + i.getExtras().get("topid").toString();
        //String url = conn.toString() + "/refed/getContents.php?tid=1";
        RequestQueue queue = Volley.newRequestQueue(contentsActivity.this);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {


                            JSONArray jArray = new JSONArray(result);
                            contents = new Content[jArray.length()];

                            for (int k=0; k < jArray.length(); k++)
                            {
                                try {
                                    JSONObject con = jArray.getJSONObject(k);
                                    if (Integer.parseInt(con.getString("type")) == 1){ // a question
                                        contents[k] = new Content(Integer.parseInt(con.getString("id")), Integer.parseInt(con.getString("topicid")),con.getString("qtext"), con.getString("file"),Integer.parseInt(con.getString("ord")),Integer.parseInt(con.getString("type")),Integer.parseInt(con.getString("qtype")),con.getString("hint"), con.getString("hintpic"));
                                        //Toast.makeText(contentsActivity.this, contents[k].toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    else if(Integer.parseInt(con.getString("type")) == 2){ // a video
                                        contents[k] = new Content(Integer.parseInt(con.getString("id")), Integer.parseInt(con.getString("topicid")), con.getString("file"),Integer.parseInt(con.getString("ord")),Integer.parseInt(con.getString("type")));
                                    //    Toast.makeText(contentsActivity.this, contents[k].toString(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final ArrayAdapter<Content> arrayAdapter = new ArrayAdapter<Content>(contentsActivity.this,  android.R.layout.simple_list_item_1, contents);
                        contentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent;
                                int type = arrayAdapter.getItem(position).getType();
                                int qtype = arrayAdapter.getItem(position).getQtype();
                                int actType = activityType(type, qtype);
                                switch(actType){
                                    case 1:
                                        intent = new Intent(contentsActivity.this, mcq_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                    case 2:
                                        intent = new Intent(contentsActivity.this, mcqall_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                    case 3:
                                        intent = new Intent(contentsActivity.this, fillblank_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                    case 4:
                                        intent = new Intent(contentsActivity.this, table_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                    case 5:
                                        intent = new Intent(contentsActivity.this, match_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                    case 10:
                                        intent = new Intent(contentsActivity.this, video_activity.class);
                                        intent.putExtra("conid", arrayAdapter.getItem(position).getId());
                                        startActivity(intent);
                                        break;
                                }
                                //Toast.makeText(contentsActivity.this, arrayAdapter.getItem(position).getId(), Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            }
                        });
                        contentsList.setAdapter(arrayAdapter);
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

    public int activityType(int type,int qtype){
        if(type == 1) { // question
            return qtype;
            }
        else{ // video
        return 10;
        }
    }

}
