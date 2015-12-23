package com.wyndhamgarden.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyndhamgarden.app.R;
import com.wyndhamgarden.app.imageloading.ImageLoader;

import java.util.ArrayList;

public class Myadapterforoffers extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    ArrayList<String> ad= new ArrayList<String>();
    ArrayList<String> ad2= new ArrayList<String>();
    ArrayList<String> ad3= new ArrayList<String>();
    ImageLoader il;

    public Myadapterforoffers(Context context, ArrayList<String> names, ArrayList<String> descp, ArrayList<String> gallery_grid_Images) {
        ctc=context;
        ad=names;
        ad2=descp;
        ad3=gallery_grid_Images;
        inflater = LayoutInflater.from(this.ctc);
        il=new ImageLoader(ctc.getApplicationContext());
    }

    @Override
    public int getCount() {
        return ad.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{

        TextView tv1,tv2,tv3;
        ImageView f1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutforoffers, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tv1 = (TextView)convertView.findViewById(R.id.titletext);
        holder.tv2 = (TextView)convertView.findViewById(R.id.descptext);
        holder.tv3 = (TextView)convertView.findViewById(R.id.tapto);
        holder.f1 = (ImageView)convertView.findViewById(R.id.f11);
        Typeface font=Typeface.createFromAsset(ctc.getAssets(), "fonts/JosefinSans-Bold.ttf");
        Typeface font1=Typeface.createFromAsset(ctc.getAssets(), "fonts/JosefinSans-SemiBold.ttf");
        holder.tv1.setTypeface(font);
        holder.tv2.setTypeface(font1);
        holder.tv3.setTypeface(font1);
        il.DisplayImage(ad3.get(position), holder.f1);
        holder.f1.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.tv1.setText(""+ad.get(position));
        holder.tv2.setText(""+ad2.get(position));
        return convertView;
    }
}
