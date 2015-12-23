package com.wyndhamgarden.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Facility extends Activity {
public static ImageView back;
    public static FrameLayout confw,wedw,chrisw;
    public static LinearLayout confb,wedb,chrisb;
    public static TextView moreinfoconf,moreinfowed,moreinfochrist,cnftxt,wedtxt,chrtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        back=(ImageView)findViewById(R.id.back);
        moreinfoconf=(TextView)findViewById(R.id.moreinfoconf);
        moreinfowed=(TextView)findViewById(R.id.weddingmoreinfo);
        moreinfochrist=(TextView)findViewById(R.id.moreinfochrist);
        cnftxt=(TextView)findViewById(R.id.cntxt);
        wedtxt=(TextView)findViewById(R.id.wedtxt);
        chrtxt=(TextView)findViewById(R.id.chrtxt);
        confw=(FrameLayout)findViewById(R.id.conferencewyt);
        wedw=(FrameLayout)findViewById(R.id.weddingwyt);
        chrisw=(FrameLayout)findViewById(R.id.christmaswyt);
        confb=(LinearLayout)findViewById(R.id.conferenceblack);
        wedb=(LinearLayout)findViewById(R.id.weddingsblack);
        chrisb=(LinearLayout)findViewById(R.id.christmasblack);




        moreinfoconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confb.getVisibility()==View.GONE) {
                    Intent i = new Intent(Facility.this, Conference.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                }

            }

        });

        moreinfowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wedb.getVisibility()==View.GONE) {
                    Intent i = new Intent(Facility.this, Wedding.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);
                }
            }

        });
        moreinfochrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chrisb.getVisibility() == View.GONE) {
                    Intent i = new Intent(Facility.this, Christmas.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slideinup, R.anim.slideoutup);

                }
            }

        });
        confw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               setblack(confb);
                setvisible(moreinfoconf, cnftxt);
              //  setclick(moreinfoconf);
            }

        });
        wedw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setblack(wedb);
                setvisible(moreinfowed, wedtxt);
               // setclick(moreinfowed);
            }

        });
        chrisw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setblack(chrisb);
                setvisible(moreinfochrist, chrtxt);
               // setclick(moreinfochrist);
            }

        });
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
    }

    private void setvisible(TextView abutn, TextView txtv) {
        chrtxt.setVisibility(View.GONE);
        cnftxt.setVisibility(View.GONE);
        wedtxt.setVisibility(View.GONE);
        moreinfoconf.setVisibility(View.GONE);
        moreinfochrist.setVisibility(View.GONE);
        moreinfowed.setVisibility(View.GONE);
        abutn.setVisibility(View.VISIBLE);
        txtv.setVisibility(View.VISIBLE);

    }

    private void setclick(TextView tt) {
        moreinfoconf.setClickable(false);
        moreinfowed.setClickable(false);
        moreinfochrist.setClickable(false);
        tt.setClickable(true);
    }

    private void setblack(LinearLayout confb11) {
       confb.setVisibility(View.VISIBLE);
        wedb.setVisibility(View.VISIBLE);
        chrisb.setVisibility(View.VISIBLE);
        confb11.setVisibility(View.GONE);
    }

}
