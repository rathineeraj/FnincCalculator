package com.fnincnellinc.fninccalculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Neeraj on 01-03-2018.
 */

public class HomeActivity extends AppCompatActivity {

    Button btnLogin, btnCalculator, btnContact, btnCalender, btnFeedback,btnCapitalGain;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        TextView tv = (TextView) this.findViewById(R.id.tvInfo);
        tv.setSelected(true);  // Set focus to the textview
        Typeface Arial_Narrow = Typeface.createFromAsset(getAssets(), "fonts/Arial Narrow.ttf");
        tv.setTypeface(Arial_Narrow);

        btnCalculator = (Button) findViewById(R.id.btnCalculator);
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, TermsActivity.class);
                startActivity(i);

            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, WebActivity.class);
                startActivity(i);
            }
        });

        btnContact = (Button) findViewById(R.id.btnContactUs);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
                startActivity(i);

            }
        });
        btnCalender = (Button) findViewById(R.id.btnCalender);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CalenderActivity.class);
                startActivity(i);

            }
        });

        btnFeedback=(Button)findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(i);
            }
        });

        btnCapitalGain=(Button)findViewById(R.id.btnCapitalGain);
        btnCapitalGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CapitalGainActivity.class);
                startActivity(i);
            }
        });
    }
}
