package com.example.hashem.refed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hashem.refed.Models.Connection;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText un;
    EditText pw;
    Connection conn;
    String success;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        un = (EditText) findViewById(R.id.un);
        pw = (EditText) findViewById(R.id.pw);
        }
        public void login_btn(View view) {
            String un_login = un.getText().toString().trim();
            String pw_login = pw.getText().toString().trim();
            String url = conn.toString() + "/refed/login.php?un_login="+ un_login +"&pw_login=" + pw_login;
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);


            StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                success = jsonObject.getString("success");
                                id = jsonObject.getString("id");
                                }
                                catch (JSONException e) {
                                e.printStackTrace();
                                }
                            if(!result.isEmpty() && success == "true"){
                                Intent i = new Intent(LoginActivity.this, ModulesActivity.class);
                                i.putExtra("id", id);

                                startActivity(i);
                            }
                            else Toast.makeText(LoginActivity.this, "Username or Password are invalid!", Toast.LENGTH_SHORT).show();
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
