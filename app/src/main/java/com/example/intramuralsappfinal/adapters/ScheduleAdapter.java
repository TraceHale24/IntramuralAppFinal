package com.example.intramuralsappfinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayList;

    public ScheduleAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_schedule_item, parent, false);
        ViewHolderClass vhc = new ViewHolderClass(view);
        return vhc;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhc = (ScheduleAdapter.ViewHolderClass) holder;
        vhc.textView.setText(arrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.nextGame);
        }
    }
}
