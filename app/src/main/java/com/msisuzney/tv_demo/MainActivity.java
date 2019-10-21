package com.msisuzney.tv_demo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.msisuzney.tv_demo.view.TabPageIndicator;
import com.msisuzney.tv_waterfallayout.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabPageIndicator tabDock;
    private ViewPager viewPager;

    private List<String> tabtitles = new ArrayList<String>();

    {
        tabtitles.add("TAB1");
    }

    private List<Fragment> fragments = new ArrayList<>();

    {
        fragments.add(new WaterfallFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabDock = findViewById(R.id.tabDock);
        viewPager = findViewById(R.id.viewPager);
        WaterfallFragmentAdapter adapter = new WaterfallFragmentAdapter(getSupportFragmentManager(), tabtitles, fragments);
        viewPager.setAdapter(adapter);
        tabDock.setViewPager(viewPager);
    }
}
