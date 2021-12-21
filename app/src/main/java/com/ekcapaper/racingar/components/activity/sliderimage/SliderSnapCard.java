package com.ekcapaper.racingar.components.activity.sliderimage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ekcapaper.racingar.components.R;
import com.ekcapaper.racingar.components.adapter.AdapterSnapGeneric;
import com.ekcapaper.racingar.components.data.DataGenerator;
import com.ekcapaper.racingar.components.helper.StartSnapHelper;
import com.ekcapaper.racingar.components.model.Image;
import com.ekcapaper.racingar.components.utils.Tools;

import java.util.List;

public class SliderSnapCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_snap_card);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snap Card");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RecyclerView recyclerViewRated = findViewById(R.id.recyclerViewRated);
        recyclerViewRated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // generate data
        List<Image> items = DataGenerator.getImageDate(this);
        List<Image> items2 = DataGenerator.getImageDate(this);

        recyclerView.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_card));
        recyclerView.setOnFlingListener(null);
        new StartSnapHelper().attachToRecyclerView(recyclerView);

        recyclerViewRated.setAdapter(new AdapterSnapGeneric(this, items2, R.layout.item_snap_card));
        recyclerViewRated.setOnFlingListener(null);
        new StartSnapHelper().attachToRecyclerView(recyclerViewRated);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notif_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
