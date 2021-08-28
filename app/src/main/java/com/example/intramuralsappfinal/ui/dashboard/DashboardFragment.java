package com.example.intramuralsappfinal.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.adapters.TeamSearchAdapter;
import com.example.intramuralsappfinal.models.Event;
import com.example.intramuralsappfinal.models.Team;
import com.example.intramuralsappfinal.models.TeamMember;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment{

    private DashboardViewModel dashboardViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private TeamSearchAdapter adapter;
    private ArrayList<Team> teams;
    private EditText search;
    private ArrayList<Team> filteredTeams;
    private Spinner sportFilter;
    private Spinner typeFilter;
    private String sportStringFilter;
    private String typeStringFilter;
    private String searchStringFilter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.teamsSearch);
        search = view.findViewById(R.id.search_text);

        sportFilter = (Spinner) view.findViewById(R.id.sport);
        ArrayAdapter<CharSequence> sportAdapter = ArrayAdapter.createFromResource(getContext(), R.array.sports, android.R.layout.simple_spinner_item);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportFilter.setAdapter(sportAdapter);

        typeFilter = (Spinner) view.findViewById(R.id.type);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeFilter.setAdapter(typeAdapter);

        setHasOptionsMenu(true);
        mDatabase.getReference().child("teams").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                teams = new ArrayList<Team>();
                Map<String, Object> bd = (HashMap<String, Object>) snapshot.getValue();
                for(String teamKey : bd.keySet()) {
                    Map<String, Object> td = (HashMap<String, Object>) bd.get(teamKey);
                    String teamId = td.get("teamId").toString();
                    String name = td.get("name").toString();
                    String division = td.get("division").toString();
                    String teamType = td.get("teamType").toString();
                    String capacity = td.get("capacity").toString();
                    String sportType = td.get("sportType").toString();
                    String captain = td.get("captain").toString();
                    String captainId = td.get("captainId").toString();
                    String isOpenString = td.get("isOpen").toString();
                    boolean isOpen = false;
                    if(isOpenString.toLowerCase().equals("true")) {
                        isOpen = true;
                    }
                    ArrayList<TeamMember> members = new ArrayList<>();
                    Map<String, Object> roughMembers = (HashMap<String, Object>) td.get("members");
                    for (String memberId : roughMembers.keySet()) {
                        HashMap<String, Object> rougherMember = (HashMap<String, Object>) roughMembers.get(memberId);
                        String memberName = rougherMember.get("name").toString();
                        String phone = rougherMember.get("phone").toString();
                        String role = rougherMember.get("role").toString();
                        String userId = rougherMember.get("userId").toString();
                        TeamMember next = new TeamMember(role, memberName, phone, userId);
                        members.add(next);
                    }
                    ArrayList<Event> games = new ArrayList<>();
                    Map<String, Object> roughGames = (HashMap<String, Object>) td.get("schedule");
                    ArrayList<Event> schedule = new ArrayList<>();
                    for (String scheduleKey : roughGames.keySet()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime dateTime = LocalDateTime.parse(scheduleKey, formatter);
                        Event temp = new Event(roughGames.get(scheduleKey).toString(), dateTime);
                        schedule.add(temp);
                    }
                    Team nextTeam = new Team(teamId, name, division, teamType, schedule, capacity, members, sportType, captain, captainId, isOpen
                    );
                    teams.add(nextTeam);
                }

                filteredTeams = new ArrayList<>();
                filteredTeams.addAll(teams);
                adapter = new TeamSearchAdapter(getContext(), filteredTeams);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapter);

                typeFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        filteredTeams.clear();
                        typeStringFilter = parent.getItemAtPosition(position).toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sportFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        filteredTeams.clear();
                        sportStringFilter = parent.getItemAtPosition(position).toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String text = search.getText().toString().toLowerCase();
                        filteredTeams.clear();
                        filter();

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Hmm");
            }
        });
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });
        return root;
    }

    private void filter() {
        filteredTeams.clear();
        for(Team team : teams) {
            if (team.getSportType().contains(sportStringFilter) && team.getTeamType().contains(typeStringFilter) && team.toString().contains(searchStringFilter)) {
                filteredTeams.add(team);
            }
        }
    }

}