package com.wyndhamgarden.app;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Conference extends Activity {
    public static ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
    }


}
