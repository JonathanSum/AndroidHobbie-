package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by JonathanSum on 4/29/2017.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"NUMBER", "FAMILY", "COLORS", "PHRASES" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new NumberFragment().newInstance(position );
        } else if(position ==1) {
            return new FamilyFragment().newInstance(position + 1);
        } else if (position == 2) {
            return new ColorFragment().newInstance(position + 2);
        } else {
            return new PhrasesFragment().newInstance(position +3);
        }
//        return PageFragment.newInstance(position + 1);
    }


//       ;

    @Override
    public CharSequence getPageTitle(int position) {
        //Generate title based on item position
        return tabTitles[position];
    }

}
