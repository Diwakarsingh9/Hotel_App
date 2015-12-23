package com.wyndhamgarden.app.Parsing;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wyndhamgarden.app.Contacts;
import com.wyndhamgarden.app.settergetter.Contactssettergetter;
import com.wyndhamgarden.app.singleton.VolleySingleton;
import com.wyndhamgarden.app.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by saifi45 on 9/17/2015.
 */
public class parsingforcontacts {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;


    public static void parsing(final Context activity,String s1,String s2, String s3){



        String locationurl2 = Api_s.Contacts1.concat(s1).concat(Api_s.contacts2).concat(s2).concat(Api_s.contacts3).concat(s3);
       locationurl2=locationurl2.replace(" ","%20");

        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Contacts.pb.setVisibility(View.GONE);
                Contacts.submit.setVisibility(View.VISIBLE);


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Contactssettergetter received2 = new Contactssettergetter();
                    received2 = gson.fromJson(response, Contactssettergetter.class);

                    String result1= received2.result;
                    Log.e("resss", "" + result1);

                        String msg1= received2.msg;
                        Toast.makeText(activity, ""+msg1, Toast.LENGTH_SHORT).show();




                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Log.e("exception", "" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr2);
        Contacts.pb.setVisibility(View.VISIBLE);
        Contacts.submit.setVisibility(View.GONE);


    }
}
