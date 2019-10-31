package com.example.hashem.refed;

import android.content.Context;
import android.util.Log;
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

import java.util.Arrays;

public class Check extends Thread{
    Connection conn = new Connection();
    int type;
    int oid;
    String toEvaluate;
    Context context;
    String rightAnswer;
    Answer[] alternatives;
    boolean evaluation;

    public Check(int type, int oid, String toEvaluate, Context context) {
        this.type = type;
        this.oid = oid;
        this.toEvaluate = toEvaluate;
        this.context = context;
    }

    @Override
    public void run() {

        String url = conn.toString() + "/refed/getContentDetails.php?oid=" + oid;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            JSONArray jArray = new JSONArray(result);
                            alternatives = new Answer[jArray.length() - 1];
                            //Toast.makeText(context, String.valueOf(jArray.length() - 1), Toast.LENGTH_SHORT).show();
                            for (int k=1; k < jArray.length(); k++) {

                                JSONObject contentjson = jArray.getJSONObject(k);
                                alternatives[k-1] = new Answer(Integer.parseInt(contentjson.getString("id")),Integer.parseInt(contentjson.getString("objectid")),contentjson.getString("atext"),Integer.parseInt(contentjson.getString("ord")),Integer.parseInt(contentjson.getString("correct")));

                            }

                            if (type == 3){

                                Toast.makeText(context, alternatives[0].atext, Toast.LENGTH_SHORT).show();

                                setRightAnswer(alternatives[0].atext);

                                //Toast.makeText(context, rightAnswer, Toast.LENGTH_SHORT).show();
                                if ((toEvaluate.trim()).equals(getRightAnswer())){

                                    evaluation = true;
                                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();

                                }

                                else {

                                    evaluation = false;
                                    ShowHint sh = new ShowHint(oid, context);
                                    sh.start();

                                }
                            }

                            else if (type == 1){

                                //Toast.makeText(context, alternatives[0].atext, Toast.LENGTH_SHORT).show();
                                setRightAnswer(alternatives[0].atext);

                                //Toast.makeText(context, rightAnswer, Toast.LENGTH_SHORT).show();

                                if ((toEvaluate.trim()).equals(getRightAnswer())){

                                    evaluation = true;
                                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();

                                }

                                else {

                                    evaluation = false;
                                    ShowHint sh = new ShowHint(oid, context);
                                    sh.start();

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

    public Answer[] getAlternatives() {
        return alternatives;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getToEvaluate() {
        return toEvaluate;
    }

    public void setToEvaluate(String toEvaluate) {
        this.toEvaluate = toEvaluate;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setAlternatives(Answer[] alternatives) {
        this.alternatives = alternatives;
    }

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Check{" +
                "conn=" + conn +
                ", type=" + type +
                ", oid=" + oid +
                ", toEvaluate='" + toEvaluate + '\'' +
                ", context=" + context +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", alternatives=" + Arrays.toString(alternatives) +
                ", evaluation=" + evaluation +
                '}';
    }

    public boolean getEvaluation() {
        return evaluation;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}