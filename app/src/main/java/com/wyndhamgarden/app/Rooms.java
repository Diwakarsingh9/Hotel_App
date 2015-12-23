package com.wyndhamgarden.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wyndhamgarden.app.adapter.Myadapterforrooms;

public class Rooms extends Activity {
    public static ListView lv;
    public static ImageView back;
    public  static  int gallery_grid_Images2[]={R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,R.drawable.r5,R.drawable.r6};
    public  static String urlid[]={"http://wyndhamgardengrantham.co.uk/room/standard-room/",
    "http://wyndhamgardengrantham.co.uk/room/deluxe-room/",
    "http://wyndhamgardengrantham.co.uk/room/superior-room/",
    "http://wyndhamgardengrantham.co.uk/room/family-room-superior/",
    "http://wyndhamgardengrantham.co.uk/room/new-wing-executive/",
    "http://wyndhamgardengrantham.co.uk/room/traditional-executive/"};
    public  static  int gallery_grid_Images[]={R.drawable.room1,R.drawable.room2,R.drawable.room3,R.drawable.room4,R.drawable.room5,R.drawable.room6};
    String names[] ={"Standard Rooms","Deluxe Rooms","Superior Rooms","Family Rooms (Superior)","Contemporary Executive","Traditional Executive"};
    String descp[]={"Our standard range includes twin, double and our fully disabled guest bedroom. Prices start from £49.50 per person (based on two guests sharing) including a full English breakfast, free use of the Health Club and VAT. You can check…",
                  "Our deluxe rooms rooms include double rooms and family rooms. Prices start from £69.50 per person (based on two guests sharing) including full English breakfast, free use of the Health Club and VAT   You can check availability and prices…",
            "Our spacious Superior twin and double rooms have been tastefully decorated and many have delightful country views. Prices start from £74.50 per person (based on two guests sharing) including full English breakfast, free use of the Health Club and VAT.…",
            "Our spacious Superior twin and double rooms have been tastefully decorated and many have delightful country views. Prices start from £74.50 per person (based on two guests sharing) including full English breakfast, free use of the Health Club and VAT.…",
    "Our contemporary Executive rooms have a romantic four-poster bed. Prices from £89.50 per person (based on two guests sharing) including full English breakfast, free use of the Health Club and VAT.   You can check availability and prices by calling…",
    "Our traditional Executive rooms consist of twin rooms some with two double beds and romantic four-poster bedrooms with Jacuzzi baths. Our prices for these start at just £89.50 per person (based on two guests sharing) inclusive of Full English breakfast,…"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        lv=(ListView)findViewById(R.id.lvforrooms);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });

        Myadapterforrooms adp = new Myadapterforrooms(Rooms.this,names,descp,gallery_grid_Images,gallery_grid_Images2);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Rooms.this, Roomsinneractivity.class);
                in.putExtra("nameroom", names[position]+"");
                in.putExtra("urlid", urlid[position]+"");
                startActivity(in);
            }
        });
    }

}
