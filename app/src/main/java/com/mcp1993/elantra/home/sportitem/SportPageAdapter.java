package com.mcp1993.elantra.home.sportitem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class SportPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private static final String[] mTitles = {"计步", "跑步", "健身", "骑行"};

    public SportPageAdapter(FragmentManager fm) {
        super(fm);

        fragmentList = new ArrayList<>();
        fragmentList.add(new StepFragment());
        fragmentList.add(new RunFragment());
        fragmentList.add(new FitnessFragment());
        fragmentList.add(new CycleFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
