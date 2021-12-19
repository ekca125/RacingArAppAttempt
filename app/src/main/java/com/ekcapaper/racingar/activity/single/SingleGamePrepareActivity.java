package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekcapaper.racingar.game.GameAppOperator;
import com.material.components.R;
import com.material.components.data.ThisApplication;

public class SingleGamePrepareActivity extends AppCompatActivity {
    GameAppOperator gameAppOperator;
    ProgressBar single_game_prepare_progress_indeterminate_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_prepare);

        gameAppOperator = ((ThisApplication)getApplication()).getGameAppOperator();

        single_game_prepare_progress_indeterminate_circular =
                findViewById(R.id.single_game_prepare_progress_indeterminate_circular);
        runProgressDeterminateCircular();
        runMakeRoom();
    }

    private void runProgressDeterminateCircular() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = single_game_prepare_progress_indeterminate_circular.getProgress() + 10;
                single_game_prepare_progress_indeterminate_circular.setProgress(progress);
                if (progress > 100) {
                    single_game_prepare_progress_indeterminate_circular.setProgress(0);
                }
                mHandler.postDelayed(this, 500);
            }
        };
        mHandler.post(runnable);
    }

    private void runMakeRoom(){
        gameAppOperator.makeSingleRoom();
        if(gameAppOperator.checkCurrentGameRoomOperator()){
            Toast.makeText(this,"Start Game", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Make Room Failed",Toast.LENGTH_SHORT).show();
        }
    }
}