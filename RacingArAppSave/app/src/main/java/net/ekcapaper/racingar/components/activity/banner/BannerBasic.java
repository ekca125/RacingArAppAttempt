package net.ekcapaper.racingar.components.activity.banner;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.adapter.AdapterGridBasic;
import net.ekcapaper.racingar.components.data.DataGenerator;
import net.ekcapaper.racingar.components.utils.Tools;
import net.ekcapaper.racingar.components.utils.ViewAnimation;
import net.ekcapaper.racingar.components.widget.SpacingItemDecoration;

import java.util.List;

public class BannerBasic extends AppCompatActivity {

    private View parent_view;
    private View banner;

    private RecyclerView recyclerView;
    private AdapterGridBasic mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_basic);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Picture");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);

        List<Integer> items = DataGenerator.getNatureImages(this);
        items.addAll(DataGenerator.getNatureImages(this));
        items.addAll(DataGenerator.getNatureImages(this));
        items.addAll(DataGenerator.getNatureImages(this));

        //set data and list adapter
        mAdapter = new AdapterGridBasic(this, items);
        recyclerView.setAdapter(mAdapter);

        banner = findViewById(R.id.banner);
        banner.setVisibility(View.GONE);
        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridBasic.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Integer obj, int position) {
                if (banner.getVisibility() == View.GONE) {
                    ViewAnimation.expand(banner);
                } else {
                    ViewAnimation.collapse(banner);
                }
            }
        });

        new Handler(this.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewAnimation.expand(banner);
            }
        }, 1500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
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

    public void onBannerActionClicked(View view) {
        ViewAnimation.collapse(banner);
    }
}