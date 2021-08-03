package com.example.intramuralsappfinal.ui.profile;

import android.os.Bundle;

import com.example.intramuralsappfinal.R;
import com.example.intramuralsappfinal.models.Team;
import com.example.intramuralsappfinal.models.User;
import com.example.intramuralsappfinal.models.UserTeam;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;

public class UserTeamActivity extends AppCompatActivity {
    private TextView teamName, sportType, teamType, division, capacity;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private Team currTeam;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_team);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        teamName = (TextView) findViewById(R.id.TeamName);
        sportType = (TextView) findViewById(R.id.sportType);
        teamType = (TextView) findViewById(R.id.teamType);
        division = (TextView) findViewById(R.id.Division);
        capacity = (TextView) findViewById(R.id.capacity);


        UserTeam team = (UserTeam) getIntent().getSerializableExtra("team");
        System.out.println(team.getName());
        Button button = (Button) findViewById(R.id.leaveTeam);
        mDatabase.getReference().child("teams").child(team.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //User user = snapshot.getValue(User.class);
                User user = snapshot.getValue(User.class);
                //Toast.makeText(ProfileFragment.class, user, Toast.LENGTH_LONG).show();
                //System.out.println("THIS IS YOUR PROFILE: " + user.toString());
                currUser = user;
                currTeam = snapshot.getValue(Team.class);
                if(currTeam != null) {

                    teamName.setText(currTeam.getName());
                    sportType.setText("Sport: " + currTeam.getSportType());
                    teamType.setText("Team Type: " + currTeam.getTeamType());
                    division.setText("Division: " + currTeam.getDivision());
                    capacity.setText("Capacity: " + currTeam.getCapacity());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Code to leave team
                //TODO: We need to get the USERSID in the DB and then the list of teams from that user, and then remove based on team name... NOt sure where to go from here righ tnow!
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query teamQuery = ref.child("users").child("teams").orderByChild("name").equalTo(currTeam.getName());
                System.out.println(ref.child("users").child("teams").orderByChild("name").equalTo(currTeam.getName()));
                teamQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot teamSnapshot : snapshot.getChildren()) {
                            teamSnapshot.getRef().removeValue();
                        }
                        Toast.makeText(UserTeamActivity.this, "Leaving Team", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("We broke it baby");
                    }
                });


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}