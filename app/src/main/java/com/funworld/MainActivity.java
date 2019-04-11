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

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.funworld.Adapter.BirthdayAdapter;
import com.funworld.Adapter.RecyclerviewDecoration;
import com.funworld.Model.Birthday;
import com.funworld.Model.BirthdayViewModel;
import com.funworld.pojo.AlarmReceivers;
import com.funworld.util.AlarmHelper;
import com.funworld.util.snackbarhelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;
import java.util.List;


public class  MainActivity extends AppCompatActivity {
BirthdayViewModel viewModels;
Birthday birthday;
Calendar calendar;
BirthdayAdapter birthdayAdapter;
public static final int ADD_BIRTHDAY_REQUEST=1;
    public static final String NOTIFICATION_CHANNEL="com.funworld.pojo.channel";
    NotificationManager notificationManager,mNotificationManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_BIRTHDAY_REQUEST&&resultCode==RESULT_OK){
            String firstname=data.getStringExtra(AddBirthday.Extra_NAME);
            String lastname=data.getStringExtra(AddBirthday.EXTRA_LNAME);
            calendar=(Calendar) data.getSerializableExtra(AddBirthday.Extra_Date);
            birthday = new Birthday(firstname,lastname,calendar);
            viewModels.Insert(birthday);
            Toast.makeText(getApplicationContext(),birthday.getCalendar().get(Calendar.YEAR)+" "+birthday.getCalendar().get(Calendar.MONTH)+" "
                    +birthday.getCalendar().get(Calendar.DAY_OF_MONTH),Toast.LENGTH_LONG).show();
            setAlerm(birthday);
        }else {
            Toast.makeText(getApplicationContext(),"couldn't add it up",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.tb);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        birthdayAdapter=new BirthdayAdapter();
        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        RecyclerView recyclerView =findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new RecyclerviewDecoration(this,LinearLayoutManager.VERTICAL,16));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FloatingActionButton floatingActionButton =findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();
                Intent intent = new Intent(getApplicationContext(),AddBirthday.class);
                startActivityForResult(intent,ADD_BIRTHDAY_REQUEST);

            }
        });
        recyclerView.setAdapter(birthdayAdapter);
         calendar=Calendar.getInstance();
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
                snackbarhelper.birthdayDeleted(findViewById(R.id.rtview),birthdayAdapter);
                    AlarmHelper.cancleAlarm(getApplicationContext(), birthdayAdapter.getPosAt(birthdayAdapter.getItemCount()-1).getLastName().hashCode());



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
                AlarmHelper.cancelAllAlarms(getApplicationContext(),birthdayAdapter.getBirthdays());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAlerm(Birthday birthday){
        int id=birthday.getLastName().hashCode()+birthday.getFirstName().hashCode();
        AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceivers.class);
        intent.putExtra(AlarmReceivers.USER_NAME,birthday.getFirstName());
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,id,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,birthday.getCalendar().getTimeInMillis(),pendingIntent);
    }



    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (NOTIFICATION_CHANNEL,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to " +
                    "stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
