package com.fnincnellinc.fninccalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CalenderActivity extends AppCompatActivity {

    EditText etDate, etDays;
    Calendar myCalendar;
    TextView tvFutureDate;
    Button btnFindDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    CheckBox chk, chkHolidays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        etDate = findViewById(R.id.etDate);
        etDays = findViewById(R.id.etDays);
        etDays.setText("45");
        chk = findViewById(R.id.chk);
        chkHolidays = findViewById(R.id.chkHolidays);
        tvFutureDate = findViewById(R.id.tvFutureDate);
        btnFindDate = findViewById(R.id.btnFindDate);
        btnFindDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etDays.getText().toString())) {
                    etDays.setError("Invalid Days");
                    return;
                }
                if (chk.isChecked() && !chkHolidays.isChecked()) {
                    String dt = etDate.getText().toString();  // Start date
                    int Days = Integer.parseInt(etDays.getText().toString());
                    Calendar cal = Calendar.getInstance();
                    // cal now contains current date
                    try {
                        cal.setTime(sdf.parse(dt));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // add the working days

                    for (int i = 0; i < Days; i++)
                        do {
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                        } while (!isWorkingDay(cal));


                    tvFutureDate.setText(String.format("Date is : %s", sdf.format(cal.getTime())));
                } else if (chkHolidays.isChecked() && !chk.isChecked()) {
                    String dt = etDate.getText().toString();  // Start date
                    int Days = Integer.parseInt(etDays.getText().toString());
                    Calendar cal = Calendar.getInstance();
                    // cal now contains current date
                    try {
                        cal.setTime(sdf.parse(dt));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // add the working days

                    for (int i = 0; i < Days; i++)
                        do {
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                        } while (!isHolidaysDay(cal));


                    tvFutureDate.setText(String.format("Date is : %s", sdf.format(cal.getTime())));
                } else if (chk.isChecked() && chkHolidays.isChecked()) {
                    String dt = etDate.getText().toString();  // Start date
                    int Days = Integer.parseInt(etDays.getText().toString());
                    Calendar cal = Calendar.getInstance();
                    // cal now contains current date
                    try {
                        cal.setTime(sdf.parse(dt));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // add the working days

                    for (int i = 0; i < Days; i++)
                        do {
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                        } while (!isHolidaysDayAndWorkingDay(cal));


                    tvFutureDate.setText(String.format("Date is : %s", sdf.format(cal.getTime())));

                } else {
                    String dt = etDate.getText().toString();  // Start date
                    int Days = Integer.parseInt(etDays.getText().toString());

                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(dt));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, Days);

                    String output = sdf.format(c.getTime());
                    tvFutureDate.setText(String.format("Date is : %s", output));
                }

            }
        });


        Calendar c = Calendar.getInstance();

        String strDate = sdf.format(c.getTime());
        etDate.setText(strDate);

        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                etDate.setText(sdf.format(myCalendar.getTime()));
            }

        };


        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(CalenderActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private static boolean isWorkingDay(Calendar cal) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY;
    }

    private static boolean isHolidaysDay(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int Day = cal.get(Calendar.DATE);
        int Month = cal.get(Calendar.MONTH) + 1;

        return checkHoliday(year, Month, Day);

    }

    private static boolean isHolidaysDayAndWorkingDay(Calendar cal) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
            return false;
        }

        int year = cal.get(Calendar.YEAR);
        int Day = cal.get(Calendar.DATE);
        int Month = cal.get(Calendar.MONTH) + 1;

        return checkHoliday(year, Month, Day);


    }

    private static boolean checkHoliday(int year, int month, int day) {

        if (year == 2018 && month == 12 && (day == 16 || day == 17 || day == 25 || day == 26)) {

            return false;

        } else if (year == 2019 && month == 1 && day == 1) {

            return false;
        } else if (year == 2019 && month == 3 && day == 21) {
            return false;
        } else if (year == 2019 && month == 4 && (day == 19 || day == 22 || day == 27)) {
            return false;
        } else if (year == 2019 && month == 5 && day == 1) {
            return false;
        } else if (year == 2019 && month == 6 && (day == 16 || day == 17)) {
            return false;
        } else if (year == 2019 && month == 8 && day == 9) {
            return false;
        } else if (year == 2019 && month == 9 && day == 24) {
            return false;
        } else if (year == 2019 && month == 12 && (day == 16 || day == 25 || day == 26)) {
            return false;
        } else if (year == 2020 && month == 1 && day == 1) {
            return false;
        } else if (year == 2020 && month == 3 && day == 21) {
            return false;
        } else if (year == 2020 && month == 4 && (day == 10 || day == 13 || day == 27)) {
            return false;
        } else if (year == 2020 && month == 5 && day == 1) {
            return false;
        } else if (year == 2020 && month == 6 && day == 16) {
            return false;
        } else if (year == 2020 && month == 8 && (day == 9 || day == 10)) {
            return false;
        } else if (year == 2020 && month == 9 && day == 24) {
            return false;
        } else if (year == 2020 && month == 12 && (day == 16 || day == 25 || day == 26)) {
            return false;
        } else if (year == 2021 && month == 1 && day == 1) {
            return false;
        } else if (year == 2021 && month == 3 && (day == 21 || day == 22)) {
            return false;
        } else if (year == 2021 && month == 4 && (day == 2 || day == 5 || day == 27)) {
            return false;
        } else if (year == 2021 && month == 5 && day == 1) {
            return false;
        } else if (year == 2021 && month == 6 && day == 16) {
            return false;
        } else if (year == 2021 && month == 8 && day == 9) {
            return false;
        } else if (year == 2021 && month == 9 && day == 24) {
            return false;
        } else if (year == 2021 && month == 12 && (day == 16 || day == 25 || day == 26 || day == 27)) {
            return false;
        } else if (year == 2022 && month == 1 && day == 1) {
            return false;
        } else if (year == 2022 && month == 3 && day == 21) {
            return false;
        } else if (year == 2022 && month == 4 && (day == 15 || day == 18 || day == 27)) {
            return false;
        } else if (year == 2022 && month == 5 && (day == 1 || day == 2)) {
            return false;
        } else if (year == 2022 && month == 6 && day == 16) {
            return false;
        } else if (year == 2022 && month == 8 && day == 9) {
            return false;
        } else if (year == 2022 && month == 9 && day == 24) {
            return false;
        } else if (year == 2022 && month == 12 && (day == 16 || day == 25 || day == 26)) {
            return false;
        } else if (year == 2023 && month == 1 && (day == 1 || day == 2)) {
            return false;
        } else if (year == 2023 && month == 3 && day == 21) {
            return false;
        } else if (year == 2023 && month == 4 && (day == 7 || day == 10 || day == 27)) {
            return false;
        } else if (year == 2023 && month == 5 && day == 1) {
            return false;
        } else if (year == 2023 && month == 6 && day == 16) {
            return false;
        } else if (year == 2023 && month == 8 && day == 9) {
            return false;
        } else if (year == 2023 && month == 9 && (day == 24 || day == 25)) {
            return false;
        } else if (year == 2023 && month == 12 && (day == 16 || day == 25 || day == 26)) {
            return false;
        } else if (year == 2024 && month == 1 && day == 1) {
            return false;
        } else if (year == 2024 && month == 3 && (day == 21 || day == 29)) {
            return false;
        } else if (year == 2024 && month == 4 && (day == 1 || day == 27)) {
            return false;
        } else if (year == 2024 && month == 5 && day == 1) {
            return false;
        } else if (year == 2024 && month == 6 && (day == 16 || day == 17)) {
            return false;
        } else if (year == 2024 && month == 8 && day == 9) {
            return false;
        } else if (year == 2024 && month == 9 && day == 24) {
            return false;
        } else if (year == 2024 && month == 12 && (day == 16 || day == 25 || day == 26)) {
            return false;
        } else if (year == 2025 && month == 1 && day == 1) {
            return false;
        } else if (year == 2025 && month == 3 && day == 21) {
            return false;
        } else if (year == 2025 && month == 4 && (day == 18 || day == 21 || day == 27 || day == 28)) {
            return false;
        } else if (year == 2025 && month == 5 && day == 1) {
            return false;
        } else if (year == 2025 && month == 6 && day == 16) {
            return false;
        } else if (year == 2025 && month == 8 && day == 9) {
            return false;
        } else if (year == 2025 && month == 9 && day == 24) {
            return false;
        } else return year != 2025 || month != 12 || (day != 16 && day != 25 && day != 26);


    }
}
