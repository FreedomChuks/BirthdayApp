package com.funworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.funworld.pojo.AlarmReceivers;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;


public class AddBirthday extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
TextInputEditText firstname,lastname,dob;
Button picker;
Calendar calendar;
public static final String Extra_NAME="com.funworld.firstname";
public static final String EXTRA_LNAME="com.funworld.lastname";
public static final String Extra_Date="com.funworld.date";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);
        Toolbar toolbar =findViewById(R.id.tb2);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        dob=findViewById(R.id.dob);
        picker=findViewById(R.id.openpicker);
        calendar=Calendar.getInstance();
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailoge();
            }
        });


    }

    public void showDatePickerDailoge(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                );
              datePickerDialog.show();

    }

    public void saveToDB(){
        String firstname=this.firstname.getText().toString().trim();
        String lastname=this.lastname.getText().toString().trim();
        String dob=this.dob.getText().toString().trim();
        if (firstname.trim().isEmpty()||lastname.trim().isEmpty()||dob.trim().isEmpty()){
            Snackbar.make(findViewById(R.id.rview),"Fields Cant be empty",Snackbar.LENGTH_LONG).show();

            return;
        }
        Intent data = new Intent();
        data.putExtra(Extra_NAME,firstname);
        data.putExtra(EXTRA_LNAME,lastname);
        data.putExtra(Extra_Date,calendar);
        setResult(RESULT_OK,data);
        finish();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addbirthday_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.quit:
                finishAffinity();
                break;

            case R.id.save:
               saveToDB();
               break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int mon=month+1;
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        dob.setText(dayOfMonth+"/"+mon+"/"+year);
    }

}
