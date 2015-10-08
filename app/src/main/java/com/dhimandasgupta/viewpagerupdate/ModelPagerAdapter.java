package com.dhimandasgupta.viewpagerupdate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dhimandasgupta on 09/10/15.
 */
public class ModelPagerAdapter extends FragmentStatePagerAdapter {
    private List<Model> mModels;

    public ModelPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainActivityFragment.newInstance(mModels.get(position));
    }

    @Override
    public int getCount() {
        return mModels == null ? 0 : mModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mModels == null ? "" : String.valueOf(position);
    }

    public void setModels(final List<Model> models) {
        mModels = models;
        notifyDataSetChanged();
    }
}
