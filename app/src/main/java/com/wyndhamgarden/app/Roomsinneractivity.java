package com.wyndhamgarden.app;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Roomsinneractivity extends Activity {
    public  static WebView mwb;
    public static ImageView back;
    public static TextView roomname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomsinneractivity);
        mwb=(WebView)findViewById(R.id.webView);
        back=(ImageView)findViewById(R.id.back);
        roomname=(TextView)findViewById(R.id.roomsname);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
        Bundle bundle = getIntent().getExtras();
        String name= bundle.getString("nameroom", null);
        String s= bundle.getString("urlid",null);
        roomname.setText(""+name);
        Log.e("msgssssssss", s);

        mwb.loadUrl(s);
    }


}
