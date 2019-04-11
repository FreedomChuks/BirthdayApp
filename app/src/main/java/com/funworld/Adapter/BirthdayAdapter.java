package com.funworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funworld.Model.Birthday;
import com.funworld.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.vHolder> {
private List<Birthday> list =new ArrayList<>();


    @NonNull
    @Override
    public vHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_card,parent,false);
        return new vHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vHolder holder, int position) {
     Birthday birthday=list.get(position);
     holder.name.setText(birthday.getFirstName());
     holder.credentials.setText(getCredentials(position));
     holder.year.setText(getAge(position)+"yrs");
     holder.date.setText(getDate(position));
     holder.dateleft.setText(getRemainingDays(position)+"Days");
    }

    public String getCredentials(int position){
        String credential=list.get(position).getFirstName();
        String name=credential.substring(0,1).toUpperCase();
        return name;
    }

    public int getAge(int position){
        int age=list.get(position).getCalendar().get(Calendar.YEAR);
      int currentage=Calendar.getInstance().get(Calendar.YEAR)-age;
      return currentage;
    }

    public void setBirthday(List<Birthday> birthday){
     this.list=birthday;
     notifyDataSetChanged();
    }

    public String getDate(int position){
        Calendar calendar=Calendar.getInstance();
        calendar= list.get(position).getCalendar();
        String date=calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
     return date;
    }

    public int getRemainingDays(int position){
        int daysleft=0;
        Calendar CurrentDate=Calendar.getInstance();
        Calendar day =Calendar.getInstance();
        day.setTime(list.get(position).getCalendar().getTime());
        if (day.after(CurrentDate)){
            daysleft= day.get(Calendar.DAY_OF_MONTH)-(CurrentDate.get(Calendar.DAY_OF_MONTH));
        }
        return daysleft;
    }

    public Birthday getPosAt(int position){
      return list.get(position);
    }

    public ArrayList<Birthday> getBirthdays() {
        return (ArrayList<Birthday>) list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class vHolder extends RecyclerView.ViewHolder{
        TextView credentials,name,year,date,dateleft;
        public vHolder(@NonNull View itemView) {
            super(itemView);
            dateleft=itemView.findViewById(R.id.daysleft);
            credentials=itemView.findViewById(R.id.credential);
            name=itemView.findViewById(R.id.name);
            year=itemView.findViewById(R.id.years);
            date=itemView.findViewById(R.id.date);
        }
    }
}
