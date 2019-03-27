package com.funworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.funworld.Adapter.BirthdayAdapter;
import com.funworld.Model.Birthday;
import com.funworld.Model.BirthdayViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class
MainActivity extends AppCompatActivity {
BirthdayViewModel viewModel;
Date date;
public static final int ADD_BIRTHDAY_REQUEST=1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_BIRTHDAY_REQUEST&&resultCode==RESULT_OK){
            String firstname=data.getStringExtra(AddBirthday.Extra_NAME);
            String lastname=data.getStringExtra(AddBirthday.EXTRA_LNAME);
//            String dob =data.getStringExtra(AddBirthday.Extra_Date);
            date=(Date)data.getSerializableExtra(AddBirthday.Extra_Date);
            Toast.makeText(getApplicationContext(), date.getMonth()+"y", Toast.LENGTH_SHORT).show();
            Birthday birthday = new Birthday(firstname,lastname,date);
            viewModel.Insert(birthday);
        }else {
            Toast.makeText(getApplicationContext(),requestCode,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.tb);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        RecyclerView recyclerView =findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FloatingActionButton floatingActionButton =findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddBirthday.class);
                startActivityForResult(intent,ADD_BIRTHDAY_REQUEST);

            }
        });
        final BirthdayAdapter birthdayAdapter =new BirthdayAdapter();
        recyclerView.setAdapter(birthdayAdapter);
        date=new Date();
        viewModel= ViewModelProviders.of(this).get(BirthdayViewModel.class);
        viewModel.getAllBirthday().observe(this, new Observer<List<Birthday>>() {
            @Override
            public void onChanged(List<Birthday> birthdays) {
                birthdayAdapter.setBirthday(birthdays);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);

    }


}
