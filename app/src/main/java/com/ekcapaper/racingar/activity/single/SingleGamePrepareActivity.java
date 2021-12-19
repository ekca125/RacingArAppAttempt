package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.material.components.R;

public class SingleGamePrepareActivity extends AppCompatActivity {
    ProgressBar single_game_prepare_progress_indeterminate_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_prepare);

        single_game_prepare_progress_indeterminate_circular =
                findViewById(R.id.single_game_prepare_progress_indeterminate_circular);
        runProgressDeterminateCircular();
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
}