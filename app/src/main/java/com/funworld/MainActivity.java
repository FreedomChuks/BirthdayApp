package com.funworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.funworld.Adapter.BirthdayAdapter;
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
        RecyclerView recyclerView =findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final BirthdayAdapter birthdayAdapter =new BirthdayAdapter();
        recyclerView.setAdapter(birthdayAdapter);
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


}
