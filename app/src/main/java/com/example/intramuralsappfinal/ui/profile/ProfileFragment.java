package com.example.intramuralsappfinal.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private TextView name, email, netID, school, phoneNumber, gender;
    private User currUser;
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.userTeams);
        mDatabase.getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //Toast.makeText(ProfileFragment.class, user, Toast.LENGTH_LONG).show();
                //System.out.println("THIS IS YOUR PROFILE: " + user.toString());
                currUser = user;
                ArrayList<UserTeam> teams = new ArrayList<>();
                ArrayList<UserTeam> userTeams = new ArrayList<>();
//                for(String id: teams.keySet()) {
//                   UserTeam temp = new UserTeam(teams.get(id).getName(),teams.get(id).getRole(),teams.get(id).getSportType(),teams.get(id).getTeamType(), teams.get(id).getSchedule());
//                   userTeams.add(temp);
//                }
                UserTeamAdapter uta = new UserTeamAdapter(getContext(),userTeams);
                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(uta);


                if(currUser != null) {
                    name.setText(currUser.getName());
                    email.setText("Email: " + currUser.getEmail());
                    netID.setText("NETID: " + currUser.getNetId());
                    school.setText("School: " + currUser.getSchool());
                    phoneNumber.setText("Phone: " + currUser.getPhone());
                    gender.setText("Gender: " + currUser.getGender());


                }
                else {
                    name.setText("Trevor Sucks at Smash");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;



        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        final TextView textView = view.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("TEAMS");
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
    }
}