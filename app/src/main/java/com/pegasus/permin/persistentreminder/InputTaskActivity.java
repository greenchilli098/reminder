package com.pegasus.permin.persistentreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InputTaskActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inDate, inTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_task);

        inDate = (EditText) findViewById(R.id.in_date);
        inTime = (EditText) findViewById(R.id.in_time);

        inDate.setOnClickListener(this);
        inTime.setOnClickListener(this);


    }

    private void displayDateInEditText(int yy, int mm, int dd) {

        Calendar newCal = new GregorianCalendar(yy,mm,dd);

        final String format = Settings.System.getString(getContentResolver(), Settings.System.DATE_FORMAT);
        DateFormat dateFormat ;

        if (TextUtils.isEmpty(format)) {
            dateFormat = android.text.format.DateFormat.getMediumDateFormat(getApplicationContext());
        } else {
            dateFormat = new SimpleDateFormat(format);
        }

        inDate.setText(dateFormat.format(newCal.getTime()));

    }

    @Override
    public void onClick(View v) {

        if(v == inDate){

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            final DatePickerDialog datePickerDialog = new DatePickerDialog( this,
                    new DatePickerDialog.OnDateSetListener(){
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

                             displayDateInEditText(year,monthOfYear,dayOfMonth);
                        }
                    }, mYear,mMonth,mDay);

            datePickerDialog.show();

        }

        if (v == inTime){

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog( this,
                    new TimePickerDialog.OnTimeSetListener(){
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute){

                            //add AM PM
                            String amPM;
                            if (hourOfDay>=12)
                                amPM = "PM";
                            else
                                amPM = "AM  ";

                            //add 0 in front of minute
                            //----- to be continue

                            inTime.setText(Integer.toString(hourOfDay) +":" + Integer.toString(minute)+" "+ amPM);
                        }
                    }, mHour,mMinute,true);

            timePickerDialog.show();
        }

    }
}
