package com.example.hashem.refed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hashem.refed.Models.Connection;
import com.example.hashem.refed.Models.Module;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModulesActivity extends AppCompatActivity {
    Module[] modules;
    Connection conn  = new Connection();
    RelativeLayout rl;
    String name;
    GridLayout gl;
    String id;
    VideoView modvid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);



        String url = conn.toString() + "/refed/getModules.php";
        RequestQueue queue = Volley.newRequestQueue(ModulesActivity.this);

        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {


                            JSONArray jArray = new JSONArray(result);
                            modules = new Module[jArray.length()];

                            for (int k=0; k < jArray.length(); k++)
                            {
                                try {
                                    JSONObject mod = jArray.getJSONObject(k);
                                    // Pulling items from the array
                                    modules[k] = new Module(Integer.parseInt(mod.getString("id")), mod.getString("name"),Integer.parseInt(mod.getString("lang")), mod.getString("file"));
                                } catch (JSONException e) {
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        modvid = (VideoView)findViewById(R.id.modvidView);
                        MediaController mc = new MediaController(ModulesActivity.this);
                        modvid.setMediaController(mc);



                        String str = conn.toString() + "/refed/modvids/m.mp4";
                        Uri uri = Uri.parse(str);
                        modvid.setVideoURI(uri);
                        modvid.requestFocus();
                        modvid.start();



                        GridLayout gridLayout = (GridLayout)findViewById(R.id.modulesGrid);
                        gridLayout.removeAllViews();
                        int total = modules.length;
                        int column = 3;
                        int row = total / column;
                        gridLayout.setColumnCount(column);
                        gridLayout.setRowCount(row + 1);
                        for(int i =0, c = 0, r = 0; i < total; i++, c++)

                        {
                            final int par = i;
                            if(c == column)
                            {
                                c = 0;
                                r++;
                            }

                            ImageButton mod_btn = new ImageButton(ModulesActivity.this);
                            String path = conn.toString() + "/refed/" + modules[i].getFile();
                            Picasso.with(ModulesActivity.this).load(path).resize(200,200).into(mod_btn);

                            GridLayout.LayoutParams param = new GridLayout.LayoutParams();

                            param.height = TableLayout.LayoutParams.WRAP_CONTENT;
                            param.width = TableLayout.LayoutParams.WRAP_CONTENT;
                            param.rightMargin = 5;
                            param.topMargin = 5;
                            param.setGravity(Gravity.CENTER);
                            param.columnSpec = GridLayout.spec(c);
                            param.rowSpec = GridLayout.spec(r);

                            mod_btn.setLayoutParams(param);
                            gridLayout.addView(mod_btn);

                            mod_btn.setOnClickListener(new ModuleOnClickListener(par){
                                @Override
                                public void onClick(View v) {
                                    super.onClick(v);
                                    Intent intent = new Intent(ModulesActivity.this, SectionsActivity.class);
                                    intent.putExtra("modid", modules[par].getId());
                                    intent.putExtra("modname", modules[par].getName());
                                    intent.putExtra("modlang", modules[par].getLang());
                                    startActivity(intent);
                                }
                            });

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                    }
                });


        //Toast.makeText(ModulesActivity.this, modules[0].toString(), Toast.LENGTH_SHORT).show();
        queue.add(jsonRequest);


    }
}
