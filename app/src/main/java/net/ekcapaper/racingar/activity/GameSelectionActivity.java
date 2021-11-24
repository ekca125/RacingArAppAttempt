package net.ekcapaper.racingar.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.activity.bottomnavigation.BottomNavigationBasic;
import net.ekcapaper.racingar.components.activity.button.ButtonBasic;
import net.ekcapaper.racingar.components.activity.card.CardBasic;
import net.ekcapaper.racingar.components.activity.list.ListNewsCard;
import net.ekcapaper.racingar.components.activity.tabs.TabsBasic;

public class GameSelectionActivity extends CardBasic {
    CardView cardViewGameModeSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardViewGameModeSingle = findViewById(R.id.card_view_game_mode_single);
        cardViewGameModeSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameSelectionActivity.this,"test",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
