package com.example.intramuralsappfinal.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.models.Event;
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

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();

                String gender = td.get("gender").toString();
                String netId = td.get("netId").toString();
                String phone = td.get("phone").toString();
                String school = td.get("school").toString();
                String name = td.get("name").toString();
                String email = td.get("email").toString();
                Map<String, Object> rougherTeams = (HashMap<String, Object>) td.get("teams");
                ArrayList<UserTeam> teams = new ArrayList<>();
                for(String key : rougherTeams.keySet()) {
                    HashMap<String, Object> roughTeams = (HashMap<String, Object>) rougherTeams.get(key);
                    String division = roughTeams.get("division").toString();
                    String role = roughTeams.get("role").toString();
                    String sportType = roughTeams.get("sportType").toString();
                    String teamType = roughTeams.get("teamType").toString();
                    String teamName = roughTeams.get("name").toString();
                    String teamId = roughTeams.get("teamId").toString();
                    ArrayList<Event> schedule = new ArrayList<>();
                    Map<String, Object> roughSchedule = (HashMap<String, Object>) roughTeams.get("schedule");
                    for(String scheduleKey : roughSchedule.keySet()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime dateTime = LocalDateTime.parse(scheduleKey, formatter);
                        Event temp = new Event(roughSchedule.get(scheduleKey).toString(), dateTime);
                        schedule.add(temp);
                    }
                    UserTeam temp = new UserTeam(teamName, role, sportType, teamType, division, teamId, schedule);
                    teams.add(temp);
                    System.out.println(roughTeams.get(key));
                }
                User user = new User(name, netId, email, phone, gender, school, teams);
                currUser = user;



                UserTeamAdapter uta = new UserTeamAdapter(getContext(),currUser.getTeams());
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