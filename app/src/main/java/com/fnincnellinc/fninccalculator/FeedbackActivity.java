package com.fnincnellinc.fninccalculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivStar5;
    int Star1 = 0, Star2 = 0, Star3 = 0, Star4 = 0, Star5 = 0;
    int Rating = 0;
    String feedbackMessage;
    Button btnSend;
    EditText etFeedback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        etFeedback = (EditText) findViewById(R.id.etFeedback);
        btnSend = (Button) findViewById(R.id.btnSend);
        ivStar1 = (ImageView) findViewById(R.id.ivStar1);
        ivStar2 = (ImageView) findViewById(R.id.ivStar2);
        ivStar3 = (ImageView) findViewById(R.id.ivStar3);
        ivStar4 = (ImageView) findViewById(R.id.ivStar4);
        ivStar5 = (ImageView) findViewById(R.id.ivStar5);

        ivStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Star1 == 0) {
                    ivStar1.setImageResource(R.drawable.selected);
                    Star1 = 1;
                } else if (Star1 == 1) {
                    ivStar1.setImageResource(R.drawable.unselected);
                    Star1 = 0;
                    ivStar2.setImageResource(R.drawable.unselected);
                    Star2 = 0;
                    ivStar3.setImageResource(R.drawable.unselected);
                    Star3 = 0;
                    ivStar4.setImageResource(R.drawable.unselected);
                    Star4 = 0;
                    ivStar5.setImageResource(R.drawable.unselected);
                    Star5 = 0;
                }
            }
        });

        ivStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Star2 == 0) {
                    ivStar1.setImageResource(R.drawable.selected);
                    Star1 = 1;
                    ivStar2.setImageResource(R.drawable.selected);
                    Star2 = 1;
                } else if (Star2 == 1) {
                    ivStar2.setImageResource(R.drawable.unselected);
                    Star2 = 0;
                    ivStar3.setImageResource(R.drawable.unselected);
                    Star3 = 0;
                    ivStar4.setImageResource(R.drawable.unselected);
                    Star4 = 0;
                    ivStar5.setImageResource(R.drawable.unselected);
                    Star5 = 0;
                }
            }
        });

        ivStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Star3 == 0) {
                    ivStar1.setImageResource(R.drawable.selected);
                    Star1 = 1;
                    ivStar2.setImageResource(R.drawable.selected);
                    Star2 = 1;
                    ivStar3.setImageResource(R.drawable.selected);
                    Star3 = 1;
                } else if (Star3 == 1) {

                    ivStar3.setImageResource(R.drawable.unselected);
                    Star3 = 0;
                    ivStar4.setImageResource(R.drawable.unselected);
                    Star4 = 0;
                    ivStar5.setImageResource(R.drawable.unselected);
                    Star5 = 0;
                }
            }
        });

        ivStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Star4 == 0) {
                    ivStar1.setImageResource(R.drawable.selected);
                    Star1 = 1;
                    ivStar2.setImageResource(R.drawable.selected);
                    Star2 = 1;
                    ivStar3.setImageResource(R.drawable.selected);
                    Star3 = 1;
                    ivStar4.setImageResource(R.drawable.selected);
                    Star4 = 1;
                } else if (Star4 == 1) {


                    ivStar4.setImageResource(R.drawable.unselected);
                    Star4 = 0;
                    ivStar5.setImageResource(R.drawable.unselected);
                    Star5 = 0;
                }
            }
        });

        ivStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Star5 == 0) {
                    ivStar1.setImageResource(R.drawable.selected);
                    Star1 = 1;
                    ivStar2.setImageResource(R.drawable.selected);
                    Star2 = 1;
                    ivStar3.setImageResource(R.drawable.selected);
                    Star3 = 1;
                    ivStar4.setImageResource(R.drawable.selected);
                    Star4 = 1;
                    ivStar5.setImageResource(R.drawable.selected);
                    Star5 = 1;
                } else if (Star5 == 1) {

                    ivStar5.setImageResource(R.drawable.unselected);
                    Star5 = 0;
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Star1 == 1) {
                    Rating = 1;
                }
                if (Star2 == 1) {
                    Rating = 2;
                }
                if (Star3 == 1) {
                    Rating = 3;
                }
                if (Star4 == 1) {
                    Rating = 4;
                }
                if (Star5 == 1) {
                    Rating = 5;
                }
                if (Rating == 0) {
                    Toast.makeText(FeedbackActivity.this, "Please give Rating", Toast.LENGTH_LONG).show();
                    return;
                }
                feedbackMessage = etFeedback.getText().toString();
                if (TextUtils.isEmpty(feedbackMessage)) {
                    etFeedback.setError("Please write feedback message");
                    return;
                }

                String EmailBody = "Rating = " + Rating + "\nFeedback : " + feedbackMessage;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "Gerhard@fninc.co.za", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, EmailBody);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

            }
        });
    }
}
