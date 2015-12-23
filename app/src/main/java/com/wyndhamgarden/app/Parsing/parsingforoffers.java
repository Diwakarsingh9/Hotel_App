package com.wyndhamgarden.app.Parsing;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wyndhamgarden.app.Offers;
import com.wyndhamgarden.app.adapter.Myadapterforoffers;
import com.wyndhamgarden.app.settergetter.Inneroffers;
import com.wyndhamgarden.app.settergetter.Offerssettergetter;
import com.wyndhamgarden.app.singleton.VolleySingleton;
import com.wyndhamgarden.app.urlapi.Api_s;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 9/17/2015.
 */
public class parsingforoffers {

    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Inneroffers> data_list1;
    public static ArrayList<String> titles = new ArrayList<String>();
    public static ArrayList<String> tagline = new ArrayList<String>();
    public static ArrayList<String> imagesss = new ArrayList<String>();
    public static ArrayList<String> postid = new ArrayList<String>();

    public static void parsing(final Context activity){



        String locationurl2 = Api_s.Offers;
        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Offers.pb.setVisibility(View.GONE);
                Offers.lv.setVisibility(View.VISIBLE);
                titles.clear();
                tagline.clear();
                imagesss.clear();
                postid.clear();


                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Offerssettergetter received2 = new Offerssettergetter();
                    received2 = gson.fromJson(response, Offerssettergetter.class);


                    data_list1=received2.inneroffers;
                    for(int i=0;i<data_list1.size();i++){
                        titles.add(data_list1.get(i).titles);
                        tagline.add(data_list1.get(i).tag_line);
                        imagesss.add(data_list1.get(i).image);
                        postid.add(data_list1.get(i).post_id);

                    }

                    Offers.adp = new Myadapterforoffers(activity,titles,tagline,imagesss);
                    Offers.lv.setAdapter(Offers.adp);


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
        Offers.pb.setVisibility(View.VISIBLE);
        Offers.lv.setVisibility(View.GONE);


    }
}
