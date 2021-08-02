package com.example.intramuralsappfinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.UserTeam;
import com.example.intramuralsappfinal.ui.profile.ProfileViewModel;
import com.example.intramuralsappfinal.ui.profile.UserTeamAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private User currUser;
    private RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        recyclerView = (RecyclerView) view.findViewById(R.id.userTeams);
        mDatabase.getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //Toast.makeText(ProfileFragment.class, user, Toast.LENGTH_LONG).show();
                //System.out.println("THIS IS YOUR PROFILE: " + user.toString());
                currUser = user;
                HashMap<String, UserTeam> teams = user.getTeams();
                ArrayList<UserTeam> userTeams = new ArrayList<>();
                for(String id: teams.keySet()) {
                    UserTeam temp = new UserTeam(teams.get(id).getName(),teams.get(id).getRole(),teams.get(id).getSportType(),teams.get(id).getTeamType());
                    userTeams.add(temp);
                }
                UserTeamAdapter uta = new UserTeamAdapter(getContext(),userTeams);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(uta);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "This broke", Toast.LENGTH_LONG);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText("TEAMS");
            }
        });
        return root;
    }
}


/*
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = (TextView) root.findViewById(R.id.Name);

        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //name.setText(currUser.getName());
                textView.setText(s);
            }
        });
        return root;
 */