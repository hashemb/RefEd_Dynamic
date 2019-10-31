package com.example.hashem.refed;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import org.json.JSONObject;

public class ShowHint extends Thread {


    Connection conn = new Connection();
    int oid;
    Context context;
    Hint hint;

    public ShowHint(int oid, Context context) {
        this.oid = oid;
        this.context = context;
    }

    @Override
    public void run() {

        String url = conn.toString() + "/refed/getHint.php?oid=" + oid;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {

                            JSONArray jArray = new JSONArray(result);
                            hint = new Hint(jArray.getString(0), jArray.getString(1));
                            //Toast.makeText(context, String.valueOf(jArray.length() - 1), Toast.LENGTH_SHORT).show();


                            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View myLayout = inflater.inflate(R.layout.hint,(ViewGroup) ((AppCompatActivity) context).findViewById(R.id.hintconstraint));


                            TextView tv = myLayout.findViewById(R.id.hinttxtview);
                            tv.setText(hint.getTxt());
                            ImageView iv = myLayout.findViewById(R.id.hintpicview);
                            String path = conn.toString() + "/hintpics/" + hint.getPic();
                            Picasso.with(context).load(path).into(iv);

                            Toast toast = new Toast(context);
                            toast.setView(myLayout);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);

                            toast.show();
                            Toast.makeText(context, hint.getTxt(), Toast.LENGTH_SHORT).show();



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

    @Override
    public String toString() {
        return "ShowHint{" +
                "oid=" + oid +
                ", context=" + context +
                '}';
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
