package com.wyndhamgarden.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wyndhamgarden.app.Parsing.parsingfornotification;
import com.wyndhamgarden.app.settergetter.Inneroffers;
import com.wyndhamgarden.app.settergetter.Offerssettergetter;
import com.wyndhamgarden.app.singleton.VolleySingleton;
import com.wyndhamgarden.app.urlapi.Api_s;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;

import net.simonvt.menudrawer.MenuDrawer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
    public  static  int gallery_grid_Images[]={R.drawable.contact_bg,R.drawable.main3,R.drawable.home_bg,R.drawable.main2};
    public  static ViewFlipper viewFlipper;
    public static MenuDrawer mdrawer;
    public static ImageView navi;
    public static int count=0;
    public  static ProgressBar pb;
    boolean previouslyStarted;
    public static FrameLayout viewpagerll;
    public static LinearLayout home,facility,contacts,rooms,offers;
    public static ViewPager viewPager;
    String parameters[]={"WEEKLY OFFER - FESTIVE LUNCH","WAIT FOR SOMETIME","TEXTILES COMING SOON"};
   // ArrayList<String>  abc= new ArrayList<>();
    public static RequestQueue queue;
    public static StringRequest sr1,sr2;
    public static List<Inneroffers> data_list1;
    public static ArrayList<String> titles = new ArrayList<String>();
    public static ArrayList<String> tagline = new ArrayList<String>();
    public static ArrayList<String> imagesss = new ArrayList<String>();
    public static ArrayList<String> postid = new ArrayList<String>();

    ProgressDialog prgDialog;
    RequestParams params = new RequestParams();
    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    static MainActivity activity22;
    String regId = "";

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    AsyncTask<Void, Void, String> createRegIdTask;

    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";
    EditText emailET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdrawer = MenuDrawer.attach(this);
        mdrawer.setContentView(R.layout.activity_main);
        mdrawer.setMenuView(R.layout.navigationdrawer);
        applicationContext = getApplicationContext();
        activity22 = MainActivity.this;
        pb=(ProgressBar)findViewById(R.id.pb);
        mdrawer.setDropShadowSize(1);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        viewpagerll = (FrameLayout) findViewById(R.id.lllllad);
        navi = (ImageView) findViewById(R.id.navi);
        home = (LinearLayout) findViewById(R.id.home);
        facility = (LinearLayout) findViewById(R.id.facility);
        contacts = (LinearLayout) findViewById(R.id.contacts);
        rooms = (LinearLayout) findViewById(R.id.rooms);
        offers = (LinearLayout) findViewById(R.id.offers);
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs2.getBoolean("pref_previously_started", false);
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

        for(int i3=0;i3<parameters.length;i3++){
           // abc.add(parameters[i]);
        }
        viewPager=(ViewPager)findViewById(R.id.view_pager);
            parsing(getApplicationContext());

        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewPager.getCurrentItem();
                String s = postid.get(i);
                Intent in = new Intent(MainActivity.this, Offersinneractivity.class);
                in.putExtra("urlid", s);
                startActivity(in);
            }
        });
        for(int i2=0;i2<gallery_grid_Images.length;i2++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(gallery_grid_Images[i2]);

        }
        viewFlipper.startFlipping();
        navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (count == 0) {

                    mdrawer.openMenu();
                    count = 1;
                } else {

                    mdrawer.closeMenu();
                    count = 0;
                }


            }
        });
        checkPlayServices();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdrawer.closeMenu();
                count = 0;
            }

        });

        facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Facility.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

            }

        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Contacts.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

            }

        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Offers.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

            }

        });
        rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Rooms.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

            }

        });


    }
    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res + "");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);

        viewFlipper.addView(image);
    }


    public void parsing(final Context activity){



        String locationurl2 = Api_s.Offers;
        Log.e("url", "" + locationurl2);
        queue = VolleySingleton.getInstance(activity).getRequestQueue();
        sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MainActivity.pb.setVisibility(View.GONE);
                MainActivity.viewPager.setVisibility(View.VISIBLE);
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

                    viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), titles,tagline,postid));


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
        MainActivity.pb.setVisibility(View.VISIBLE);
        MainActivity.viewPager.setVisibility(View.GONE);


    }
    private void registerInBackground(final String emailID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(applicationContext);
                    }
                    regId = gcmObj
                            .register(ApplicationConstants.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + regId;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(regId)) {
                    storeRegIdinSharedPref(applicationContext, regId, emailID);

                } else {
                    Toast.makeText(MainActivity.this, "Please check your internet connection .....", Toast.LENGTH_SHORT).show();

                }
            }
        }.execute(null, null, null);
    }

    private void storeRegIdinSharedPref(Context context, String regId,
                                        String emailID) {
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putString(EMAIL_ID, emailID);
        editor.commit();
        storeRegIdinServer();

    }

    private void storeRegIdinServer() {
         String android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String dd = mngr.getDeviceId();


        if (!previouslyStarted) {
            parsingfornotification.parsing(MainActivity.this,regId,dd);
        }
        else if (previouslyStarted) {
            //
        }
       // parsingfornotification.parsing(MainActivity.this,regId,dd);

    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
//                Toast.makeText(
//                        applicationContext,
//                        "This device doesn't support Play services, App will not get notifications",
//                        Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        } else {
            registerInBackground("wyndham@gmail.com");
//            Toast.makeText(
//                    applicationContext,
//                    "This device supports Play services, App will work normally",
//                    Toast.LENGTH_LONG).show();
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

}

/*
Project ID
wyndham-1097
Project number
470072746352


key

AIzaSyDqaN3w065K8SB99kUuZZpIDFf0FDrEVjo
 */