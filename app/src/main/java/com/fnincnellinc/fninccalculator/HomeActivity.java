package com.fnincnellinc.fninccalculator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Neeraj on 01-03-2018.
 */

public class HomeActivity extends AppCompatActivity {

    Button btnLogin, btnCalculator, btnContact, btnCalender, btnFeedback, btnCapitalGain, btnInstagram;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        TextView tv = this.findViewById(R.id.tvInfo);
        tv.setSelected(true);  // Set focus to the textview
        Typeface Arial_Narrow = Typeface.createFromAsset(getAssets(), "fonts/Arial Narrow.ttf");
        tv.setTypeface(Arial_Narrow);

        btnCalculator = findViewById(R.id.btnCalculator);
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, TermsActivity.class);
                startActivity(i);

            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, WebActivity.class);
                startActivity(i);
            }
        });

        btnContact = findViewById(R.id.btnContactUs);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
                startActivity(i);

            }
        });
        btnCalender = findViewById(R.id.btnCalender);
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CalenderActivity.class);
                startActivity(i);

            }
        });

        btnFeedback = findViewById(R.id.btnFeedback);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(i);
            }
        });

        btnCapitalGain = findViewById(R.id.btnCapitalGain);
        btnCapitalGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CapitalGainActivity.class);
                startActivity(i);
            }
        });
        btnInstagram = findViewById(R.id.btnInstagram);
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/faurienell");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/faurienell")));
                }
            }
        });
    }
}
