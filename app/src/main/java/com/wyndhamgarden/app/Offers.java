package com.wyndhamgarden.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wyndhamgarden.app.Parsing.parsingforoffers;
import com.wyndhamgarden.app.adapter.Myadapterforoffers;

public class Offers extends Activity {
public static ListView lv;
    public static ImageView back;
    public  static ProgressBar pb;

    public  static Myadapterforoffers adp;
    public  static  int gallery_grid_Images[]={R.drawable.capture1,R.drawable.capture2,R.drawable.capture3,R.drawable.capture};
    String names[] ={"APP INSTALLATION - SAVE 10%","FESTIVE LUNCH","MURDER MYSTERY","BURNS NIGHT"};
    String descp[]={"INSTALL OUR NEW APP AND SAVE UP TO 10%! ALIQUAM ADIPISCING ELIT. PORTA ALIQUAM ORCI EX, PORTA ET VARIUS.",
    "SED BLANDIT, TELLUS NEC VOLUTPAT TINCIDUNT, QUAM VELIT TEMPUS RISUS, AC FINIBUS NISI SEM AT EST.","One night bed and breakfast in a standard room. Entry in our murder mystery event.",
            "Join us on Saturday 23rd January 2016 when we celebrate the life and poems of the poet Robert Burns."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        lv=(ListView)findViewById(R.id.lvforoffers);

        back=(ImageView)findViewById(R.id.back);
        pb=(ProgressBar)findViewById(R.id.pb22);
        parsingforoffers.parsing(getApplicationContext());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });

         adp = new Myadapterforoffers(Offers.this,parsingforoffers.titles,parsingforoffers.tagline,parsingforoffers.imagesss);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Offers.this,Offersinneractivity.class);
                in.putExtra("urlid",parsingforoffers.postid.get(position));
                in.putExtra("nameoff", parsingforoffers.titles.get(position));
                in.putExtra("bg",parsingforoffers.imagesss.get(position));
                startActivity(in);
            }
        });
    }


}
