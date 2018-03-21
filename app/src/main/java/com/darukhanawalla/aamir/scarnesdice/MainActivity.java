package com.darukhanawalla.aamir.scarnesdice;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int oUser, cUser, oComp, cComp;
    ImageView dice;
    Button roll, hold, reset;
    TextView yscore, cscore, tscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice = findViewById(R.id.dice);
        roll = findViewById(R.id.roll);
        reset = findViewById(R.id.reset);
        hold = findViewById(R.id.hold);
        yscore = findViewById(R.id.yscore);
        cscore = findViewById(R.id.cscore);
        tscore = findViewById(R.id.tscore);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });

        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oUser = 0;
                cUser = 0;
                oComp = 0;
                cComp = 0;

                tscore.setText("Turn Score:  0");
                cscore.setText("Computer Score:  0");
                yscore.setText("Your Score:  0");

                dice.setImageResource(R.drawable.dice5);

                roll.setEnabled(true);
                hold.setEnabled(true);
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oUser += cUser;
                cUser = 0;
                tscore.setText("Turn Score:  0");
                yscore.setText("Your Score:  " + oUser);
                computerTurn();
            }
        });
    }

    private void rollDice()
    {
        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        switch (num)
        {
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dice.setImageResource(R.drawable.dice6);
                break;
        }

        if(num == 1)
        {
            cUser = 0;
            computerTurn();
        }
        else
        {
            cUser +=num;
        }

        tscore.setText("Turn Score:  " + cUser);
        hold.setEnabled(true);
    }

    private void computerTurn()
    {
        roll.setEnabled(false);
        hold.setEnabled(false);

        Random rand = new Random();
        int num = rand.nextInt(6) + 1;

        while(true)
        {
            switch (num)
            {
                case 1:
                    dice.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    dice.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    dice.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    dice.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    dice.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    dice.setImageResource(R.drawable.dice6);
                    break;
            }

            if(num == 1)
            {
                cComp = 0;
                compTurnOver();
                break;
            }
            else
            {
                cComp +=num;
            }
            tscore.setText("Turn Score:  " + cComp);

            for(int i=0; i<=100000000; i++);

            if(cComp >= 20)
            {
                compTurnOver();
                break;
            }

            num = rand.nextInt(6) + 1;
        }
    }

    private void stay()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
            }
        }, 1000);
    }

    private void compTurnOver()
    {
        if(!checkWin())
        {
            oComp += cComp;
            cComp = 0;
            tscore.setText("Turn Score:  0");
            cscore.setText("Computer Score:  " + oComp);
            roll.setEnabled(true);
            if(!checkWin())
                Toast.makeText(this, "Your Turn", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkWin()
    {
        if(oUser >= 100 || oComp >= 100)
        {
            if(oUser >= 100)
                Toast.makeText(this, "YOU WIN", Toast.LENGTH_SHORT).show();
            else if(oComp >= 100)
                Toast.makeText(this, "Computer Wins", Toast.LENGTH_SHORT).show();
            hold.setEnabled(false);
            roll.setEnabled(false);
            return true;
        }
        return false;
    }
}
