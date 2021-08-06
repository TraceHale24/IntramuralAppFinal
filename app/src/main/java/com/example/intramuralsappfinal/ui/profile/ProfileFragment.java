package com.example.intramuralsappfinal.ui.profile;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private TextView name, email, netID, school, phoneNumber, gender;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        name = (TextView) view.findViewById(R.id.Name);
        email = (TextView) view.findViewById(R.id.Email);
        netID = (TextView) view.findViewById(R.id.NetID);
        school = (TextView) view.findViewById(R.id.School);
        phoneNumber = (TextView) view.findViewById(R.id.PhoneNumber);
        gender = (TextView) view.findViewById(R.id.Gender);
        mDatabase.getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> td = (HashMap<String, Object>) snapshot.getValue();
                name.setText(td.get("name").toString());
                email.setText(td.get("email").toString());
                netID.setText(td.get("netId").toString());
                school.setText(td.get("school").toString());
                phoneNumber.setText(td.get("phone").toString());
                gender.setText(td.get("gender").toString());
                Map<String, Object> rougherTeams = (HashMap<String, Object>) td.get("teams");
                ArrayList<UserTeam> userTeams = new ArrayList<>();
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
                    userTeams.add(temp);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = (TextView) root.findViewById(R.id.Name);

        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        return root;
    }
}