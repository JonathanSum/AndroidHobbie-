package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int pointsA;
    int pointsB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(pointsA);
        displayForTeamB(pointsB);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void threePointsA(View view) {
        pointsA += 3;
        displayForTeamA(pointsA);
    }

    public void twoPointsA(View view) {
        pointsA += 2;
        displayForTeamA(pointsA);
    }

    public void onePointA(View view) {
        pointsA += 1;
        displayForTeamA(pointsA);
    }
    public void resetButton(View view){
        pointsA = 0;
        displayForTeamA(pointsA);
        pointsB = 0;
        displayForTeamB(pointsB);
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void threePointsB(View view) {
        pointsB += 3;
        displayForTeamB(pointsB);
    }

    public void twoPointsB(View view) {
        pointsB += 2;
        displayForTeamB(pointsB);
    }

    public void onePointB(View view) {
        pointsB += 1;
        displayForTeamB(pointsB);
    }
}
