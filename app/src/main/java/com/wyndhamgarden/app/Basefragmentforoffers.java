package com.wyndhamgarden.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by saifi45 on 8/9/2015.
 */

    public class Basefragmentforoffers extends Fragment {
        static String ds2;
        public  static  int y;

public  static TextView tv,tv3,tv4;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank, null);

            tv = (TextView)root.findViewById(R.id.tv1);
            tv3 = (TextView)root.findViewById(R.id.tv3);
            tv4 = (TextView)root.findViewById(R.id.tvgghg);
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = MainActivity.viewPager.getCurrentItem();
                    String s = MainActivity.postid.get(i);
                    Intent in = new Intent(getActivity(), Offersinneractivity.class);
                    in.putExtra("urlid", s);
                    in.putExtra("nameoff",MainActivity.titles.get(i));
                    in.putExtra("bg", MainActivity.imagesss.get(i));
                    startActivity(in);
                }
            });
            tv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = MainActivity.viewPager.getCurrentItem();
                    String s = MainActivity.postid.get(i);
                    Intent in = new Intent(getActivity(), Offersinneractivity.class);
                    in.putExtra("urlid", s);
                    in.putExtra("nameoff",MainActivity.titles.get(i));
                    in.putExtra("bg", MainActivity.imagesss.get(i));
                    startActivity(in);
                }
            });
            String d= getArguments().getString("msg");
            String d33= getArguments().getString("msg2");
            tv.setText(""+d);
            tv3.setText(""+d33);
            return root;
        }


        public static Basefragmentforoffers newInstance(String dev1,String dev22, String dev33) {
            Basefragmentforoffers f = new Basefragmentforoffers();
            Bundle b = new Bundle();
            b.putString("msg", String.valueOf(dev1));
            b.putString("msg2", String.valueOf(dev22));
            b.putString("msg3", String.valueOf(dev33));
            // y=ber;
            f.setArguments(b);

            return f;
        }
    }

