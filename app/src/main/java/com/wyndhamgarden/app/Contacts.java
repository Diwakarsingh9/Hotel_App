package com.wyndhamgarden.app;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wyndhamgarden.app.Parsing.parsingforcontacts;

public class Contacts extends Activity {
        public  static TextView submit;
    public static ImageView back,emailval;
    public  static EditText name1, email, enquiry;
    public  static ProgressBar pb;
    public  static ScrollView scrollView;
    String email1;
    String emailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        submit=(TextView)findViewById(R.id.submit);
        back=(ImageView)findViewById(R.id.back);
        emailval=(ImageView)findViewById(R.id.emailvalidat);
        name1=(EditText)findViewById(R.id.namesss);
        email=(EditText)findViewById(R.id.emailsss);
        enquiry=(EditText)findViewById(R.id.enquiryss);
        scrollView=(ScrollView)findViewById(R.id.scroll);
        pb=(ProgressBar)findViewById(R.id.pb);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parsingforcontacts.parsing(getApplicationContext(),name1.getText().toString().trim(),email.getText().toString().trim(),enquiry.getText().toString());

            }

        });




        emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-zA-Z0-9._-]+";

        email .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                email1 = email.getText().toString().trim();
                if (email1.matches(emailPattern) && s.length() > 0) {
                   emailval.setImageResource(R.drawable.right);


                } else {
                    emailval.setImageResource(R.drawable.cross);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = Contacts.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                finish();
            }

        });
        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //
            scrollView.setNextFocusDownId(0);
            }

        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // submit.requestFocus();
            }

        });
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  submit.requestFocus();
            }

        });
    }
        @Override
        protected void onDestroy(){
         super.onDestroy();
            View view = Contacts.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

}
