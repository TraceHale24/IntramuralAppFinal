package com.example.intramuralsappfinal.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.R;

import java.io.Serializable;
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
                //Toast.makeText(context, arrayList.get(position).toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), UserTeamActivity.class);
                intent.putExtra("team", (Serializable) arrayList.get(position));
                context.startActivity(intent);


                //Here we need to open up the view of the UserTeam so that the user can *Leave* and view the team info in the page fragment or activity
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
