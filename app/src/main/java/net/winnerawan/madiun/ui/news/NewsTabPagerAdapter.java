package net.winnerawan.madiun.ui.news;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class NewsTabPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList;
    private final List<String> fragmentTitleList;
    private FragmentActivity activity;

    public NewsTabPagerAdapter(FragmentActivity activity, FragmentManager fm) {
        super(fm);
        this.activity = activity;
        this.fragmentList = new ArrayList<>();
        this.fragmentTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
//        notifyDataSetChanged();
    }

    public void addFragments(List<Fragment> fragments, List<String> titles) {
        fragments.clear();
        fragmentTitleList.clear();
        fragmentList.addAll(fragments);
        fragmentTitleList.addAll(titles);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (fragmentList.contains(object)) {
            return fragmentList.indexOf(object);
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public void clearItems() {
        fragmentList.clear();
        fragmentTitleList.clear();
        notifyDataSetChanged();
    }
}
