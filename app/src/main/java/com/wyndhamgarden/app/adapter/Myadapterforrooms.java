package com.wyndhamgarden.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyndhamgarden.app.R;


public class Myadapterforrooms extends BaseAdapter {

    Context ctc;
    LayoutInflater inflater;
    String[] ad;
    String[] ad2;
    int []ad3;
    int []ad4;

    public Myadapterforrooms(Context context, String[] names, String[] descp, int[] gallery_grid_Images, int[] gallery_grid_Images2) {
        ctc=context;
        ad=names;
        ad2=descp;
        ad3=gallery_grid_Images;
        ad4=gallery_grid_Images2;
        inflater = LayoutInflater.from(this.ctc);
    }

    @Override
    public int getCount() {
        return ad.length;
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
        LinearLayout f1;
        FrameLayout f2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.itemlayoutforrooms, null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tv1 = (TextView)convertView.findViewById(R.id.texttitle);
        holder.tv2 = (TextView)convertView.findViewById(R.id.descptext);
        holder.tv3 = (TextView)convertView.findViewById(R.id.viewrooms);
        holder.f1 = (LinearLayout)convertView.findViewById(R.id.f11);
        holder.f2 = (FrameLayout)convertView.findViewById(R.id.fl111);
        holder.f1.setBackgroundResource(ad3[position]);
        holder.f2.setBackgroundResource(ad4[position]);
        Typeface font=Typeface.createFromAsset(ctc.getAssets(), "fonts/JosefinSans-Bold.ttf");
        Typeface font1=Typeface.createFromAsset(ctc.getAssets(), "fonts/JosefinSans-SemiBold.ttf");
        holder.tv1.setTypeface(font);
        holder.tv2.setTypeface(font);
        holder.tv3.setTypeface(font1);
        holder.tv1.setText(""+ad[position]);
        holder.tv2.setText(""+ad2[position]);
        return convertView;
    }
}
