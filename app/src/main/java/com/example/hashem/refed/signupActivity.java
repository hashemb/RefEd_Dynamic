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

import org.json.JSONException;
import org.json.JSONObject;

public class signupActivity extends AppCompatActivity {
    EditText un;
    EditText pw;
    EditText cpw;
    //String URL_IP = "192.168.1.106";
    Connection conn;
    String success;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        un = (EditText) findViewById(R.id.un_signup);
        pw = (EditText) findViewById(R.id.pw_signup);
        cpw = (EditText) findViewById(R.id.cpw_signup);
    }
    public void signup_btn(View view) {
        String un_signup = un.getText().toString().trim();
        String pw_signup = pw.getText().toString().trim();
        String cpw_signup = cpw.getText().toString().trim();
        if (!pw_signup.equalsIgnoreCase(cpw_signup)){Toast.makeText(signupActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();}
        else {
            conn = new Connection();
            String url = conn.toString() + "/refed/signup.php?un_signup="+ un_signup +"&pw_signup=" + pw_signup;

            RequestQueue queue = Volley.newRequestQueue(signupActivity.this);


            StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                success = jsonObject.getString("success");
                                id = jsonObject.getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (!result.isEmpty() && success == "true"){
                                Intent i = new Intent(signupActivity.this, modulesActivity.class);
                                i.putExtra("id", id);
                                startActivity(i);
                            } else if(success == "false"){
                                Toast.makeText(signupActivity.this, "Username Exists!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(signupActivity.this, result, Toast.LENGTH_SHORT).show();

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

    }
}
