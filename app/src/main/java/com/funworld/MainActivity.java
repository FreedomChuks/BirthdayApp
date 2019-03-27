package com.funworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.funworld.Adapter.BirthdayAdapter;
import com.funworld.Model.Birthday;
import com.funworld.Model.BirthdayViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;


public class  MainActivity extends AppCompatActivity {
BirthdayViewModel viewModels;
    Birthday birthday;
Date date;
public static final int ADD_BIRTHDAY_REQUEST=1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_BIRTHDAY_REQUEST&&resultCode==RESULT_OK){
            String firstname=data.getStringExtra(AddBirthday.Extra_NAME);
            String lastname=data.getStringExtra(AddBirthday.EXTRA_LNAME);
            date=(Date)data.getSerializableExtra(AddBirthday.Extra_Date);
            Toast.makeText(getApplicationContext(), date.getMonth()+"y", Toast.LENGTH_SHORT).show();
            birthday = new Birthday(firstname,lastname,date);
            viewModels.Insert(birthday);
        }else {
            Toast.makeText(getApplicationContext(),birthday.getDob().getYear()+"y",Toast.LENGTH_LONG).show();
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
        viewModels= ViewModelProviders.of(this).get(BirthdayViewModel.class);
        viewModels.getAllBirthday().observe(this, new Observer<List<Birthday>>() {
            @Override
            public void onChanged(List<Birthday> birthdays) {
                birthdayAdapter.setBirthday(birthdays);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            viewModels.Delete(birthdayAdapter.getPosAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.deleteAll:
                viewModels.DeleteAll();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
