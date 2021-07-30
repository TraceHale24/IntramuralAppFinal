package com.example.intramuralsappfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.models.UserTeam;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<UserTeam> mData;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerViewAdapter(List<UserTeam> data) {
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_team_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserTeam next = mData.get(position);
        holder.mTeamType.setText(next.getName());
        holder.mTeamName.setText(next.getName());
        holder.mTeamType.setText(next.getTeamType());
        holder.mRole.setText(next.getRole());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTeamName;
        TextView mRole;
        TextView mSportType;
        TextView mTeamType;

        public ViewHolder(View itemView) {
            super(itemView);
            mTeamName = itemView.findViewById(R.id.teamName);
            mRole = itemView.findViewById(R.id.role);
            mSportType = itemView.findViewById(R.id.sportType);
            mTeamType = itemView.findViewById(R.id.teamType);

            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    UserTeam getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
