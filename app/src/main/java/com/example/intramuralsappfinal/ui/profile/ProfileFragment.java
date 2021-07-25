package com.example.intramuralsappfinal.ui.profile;

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

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private TextView name, email, netID, school, phoneNumber, gender;
    private User currUser;

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


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //Toast.makeText(ProfileFragment.class, user, Toast.LENGTH_LONG).show();
                System.out.println("THIS IS YOUR PROFILE: " + user.toString());
                currUser = user;
                if(currUser != null) {
                    name.setText(currUser.getName());
                    email.setText(currUser.getEmail());
                    netID.setText(currUser.getNetid());
                    school.setText(currUser.getSchool());
                    phoneNumber.setText(currUser.getPhoneNumber());
                    gender.setText(currUser.getGender());

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
                //name.setText(currUser.getName());
                textView.setText(s);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance();
//        String userID = mAuth.getCurrentUser().getUid();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = (TextView) root.findViewById(R.id.Name);
//
//        mDatabase.getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User user = snapshot.getValue(User.class);
//                //Toast.makeText(ProfileFragment.class, user, Toast.LENGTH_LONG).show();
//                System.out.println("THIS IS YOUR PROFILE: " + user.toString());
//                currUser = user;
//                if(currUser != null) {
//                    name.setText(currUser.getName());
//                }
//                else {
//                    name.setText("Trevor Sucks at Smash");
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        }) ;
//
//
//
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