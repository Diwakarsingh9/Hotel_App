package com.wyndhamgarden.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class MyAdapter extends FragmentPagerAdapter {
    ArrayList<String> a = new ArrayList<String>();;
    ArrayList<String> bdd = new ArrayList<String>();;
    ArrayList<String> a22 = new ArrayList<String>();;
        int b;


    public MyAdapter(FragmentManager fm, ArrayList<String> arrayB, ArrayList<String> tagline, ArrayList<String> postid) {
        super(fm);

        a=arrayB;
        bdd=tagline;
        a22=postid;

    }



    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        frag= Basefragmentforoffers.newInstance(a.get(position),bdd.get(position),a22.get(position));

        return frag;
    }

    @Override
    public int getCount() {
        return a.size();
    }
}