package com.funworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funworld.Model.Birthday;
import com.funworld.R;

import java.util.ArrayList;
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
    }

    public void setBirthday(List<Birthday> birthday){
     this.list=birthday;
     notifyDataSetChanged();
    }

    public void getPosAt(){

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
