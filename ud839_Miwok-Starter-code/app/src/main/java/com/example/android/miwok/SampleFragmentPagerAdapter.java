package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by JonathanSum on 4/29/2017.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"NUMBER", "FAMILY", "COLORS","PHRASES"};
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }
    @Override
    public int getCount(){
        return PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int position){
        return PageFragment.newInstance(position + 1);
    }
    @Override
    public CharSequence getPageTitle(int position){
        //Generate title based on item position
        return tabTitles[position];
    }

    }
