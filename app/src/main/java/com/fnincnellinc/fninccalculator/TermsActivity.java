package com.fnincnellinc.fninccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {

    CheckBox chkTerms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        chkTerms = findViewById(R.id.chkTerms);
        Button btnCalculator = findViewById(R.id.btnCalculator);
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chkTerms.isChecked()) {
                    Intent i = new Intent(TermsActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(TermsActivity.this, "Please accept terms to go calculator", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
