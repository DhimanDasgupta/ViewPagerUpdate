package com.dhimandasgupta.viewpagerupdate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String MODELS_TAG = "models";

    private int mCurrentPage = 0;
    private List<Model> mModels;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        setUpModels(savedInstanceState);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        if (mViewPager != null) {
            setupViewPager();
        }

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MODELS_TAG, (ArrayList) mModels);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    private void setUpModels(final Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mModels = new ArrayList<Model>();

            for (int i = 0; i < 5; i++) {
                final Model model = new Model();

                model.mText = "This is number " + i;
                model.mColor = i%2 == 0 ? Color.RED : Color.GREEN;
                model.mShowLayout = false;

                mModels.add(model);
            }
        } else {
            mModels = savedInstanceState.getParcelableArrayList(MODELS_TAG);
        }
    }
    
    private void setupViewPager() {
        final ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentPage = position;
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter.setModels(mModels);
    }

    private void onFabClicked() {
        mModels.get(mCurrentPage).mShowLayout = !mModels.get(mCurrentPage).mShowLayout;
        setupViewPager();
        mViewPager.setCurrentItem(mCurrentPage);
    }
}
