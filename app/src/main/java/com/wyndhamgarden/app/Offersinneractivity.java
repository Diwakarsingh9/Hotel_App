package com.wyndhamgarden.app;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyndhamgarden.app.imageloading.ImageLoader;

public class Offersinneractivity extends Activity {
public  static WebView mwb;
    public static ImageView back;
    public static TextView offername;

    ImageLoader il;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offersinneractivity);
        mwb=(WebView)findViewById(R.id.webView);
        back=(ImageView)findViewById(R.id.back);
        offername=(TextView)findViewById(R.id.offername);

        il=new ImageLoader(Offersinneractivity.this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
        Bundle bundle = getIntent().getExtras();
        String s= bundle.getString("urlid", null);
        String name= bundle.getString("nameoff",null);
        String imgs = bundle.getString("bg",null);
        offername.setText(""+name);

        Log.e("msgssssssss", s);
        String str="http://wyndhamgardengrantham.co.uk/?page_id=1664&post_id="+s;
        mwb.getSettings().setJavaScriptEnabled(true);

        mwb.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // Handle the error
                Toast.makeText(Offersinneractivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        mwb.loadUrl(str);

    }


}
