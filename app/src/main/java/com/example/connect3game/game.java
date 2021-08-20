package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class game extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        String winner = "";
        int tap = Integer.parseInt(counter.getTag().toString());
        if (gameState[tap] == 2 && gameActive) {
            gameState[tap] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    if(activePlayer == 0) {
                        winner = "O";
                    } else {
                        winner = "X";
                    }
                    Button playagain = (Button) findViewById(R.id.button2);
                    TextView text = (TextView) findViewById(R.id.winnerTextView);
                    text.setText("Player with " + winner + " wins the game");
                    playagain.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.button2);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        Log.i("info","reached");
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }
}