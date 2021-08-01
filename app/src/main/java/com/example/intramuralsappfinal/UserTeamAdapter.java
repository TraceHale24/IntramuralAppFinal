package com.example.intramuralsappfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserTeamAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayList;

    public UserTeamAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_user_team, parent, false);
        ViewHolderClass viewHolderClass= new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhc = (ViewHolderClass) holder;
        vhc.textView.setText(arrayList.get(position).toString());
        vhc.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Team Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.nextTeam);
        }
    }
}
