package com.funworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import com.funworld.Model.Birthday;
import com.funworld.Model.BirthdayViewModel;

import java.util.List;

public class
MainActivity extends AppCompatActivity {
BirthdayViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.tb);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        viewModel= ViewModelProviders.of(this).get(BirthdayViewModel.class);
        viewModel.getAllBirthday().observe(this, new Observer<List<Birthday>>() {
            @Override
            public void onChanged(List<Birthday> birthdays) {
                Toast.makeText(getApplicationContext(),"changed",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
