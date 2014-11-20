package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MFragPagerAdapter extends FragmentPagerAdapter {
	
	private static final int FRAGMENT_COUNT = 3;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private FragmentManager mFM;
	public boolean disableSwipe = false;
	
	public MFragPagerAdapter(FragmentManager fm) {
		super(fm);
		mFM = fm;
		
		//add fragments
		mFragments.add(new DisasterFragment());
		mFragments.add(new PeopleFragment());
		mFragments.add(new PlacesFragment());

	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = mFragments.get(arg0);
		disableSwipe = false;
		return fragment;
	}

	@Override
	public int getCount() {
		return FRAGMENT_COUNT;
	}
	
    public Fragment getActiveFragment(ViewPager container, int pos) {
    	String name = "android:switcher:" + container.getId() + ":" + pos;
    	return  mFM.findFragmentByTag(name);
    }
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Disasters";
			case 1:
				return "People";
			case 2:
				return "Places";
		}
		return null;
    }
}
