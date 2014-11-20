package com.livequake.disastersafetyalert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ibm.mobile.services.core.IBMBluemix;
import com.ibm.mobile.services.data.IBMData;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ClipData.Item;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

public class MainFragmentActivity extends FragmentActivity implements ActionBar.TabListener {
	
	// Declare Tab Variable
	Tab tab;
	MFragPagerAdapter mMFragPagerAdapter;
	ViewPager mViewPager;
		/* Catch Intent from BroadcastReceiver */
	IntentFilter intentFilter;
	
	final String APP_ID = "applicationID";
	final String APP_SECRET = "applicationSecret";
	final String APP_ROUTE = "applicationRoute";
	final String PROPS_FILE = "disastersafetyalert.properties";
	
	private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getExtras().getString("sms");
			String type = msg.substring(PeopleFragment.beginText.length(), PeopleFragment.beginText.length());
			Log.i("SmsReceiverB", type);

		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Properties props = new java.util.Properties();
		Context context = getApplicationContext();
		try {
			AssetManager assetManager = context.getAssets();
			props.load(assetManager.open(PROPS_FILE));
			Log.i("Found configuration file: " + PROPS_FILE, "!!!");
		} catch (FileNotFoundException e) {
			Log.e( "**** The bluelist.properties file was not found.","****" ,e);
		} catch (IOException e) {
			Log.e("****The bluelist.properties file could not be read properly.","****", e);
		}
		
		// initialize the IBM core backend-as-a-service
		IBMBluemix.initialize(this, props.getProperty(APP_ID), 
							props.getProperty(APP_SECRET), props.getProperty(APP_ROUTE));
		// initialize the IBM Data Service
		IBMData.initializeService();
		
		
		setContentView(R.layout.activity_main_fragment);

		/* Intent to filter */
		intentFilter = new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
		//Intent nI = new Intent(android.content.Intent.ACTION_VIEW);
	
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		final ActionBar actionBar = getActionBar();
		
		/* Specify that tabs should be displayed in the action bar */
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    // Specify that the Home/Up button should not be enabled, since there is no hierarchical parent.
	 	//actionBar.setHomeButtonEnabled(false);	//must set to API 14
	    
        mMFragPagerAdapter = new MFragPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pagerMain);
        mViewPager.setAdapter(mMFragPagerAdapter);

	    /* Add tab for each defined in adapter */
	    for (int i = 0; i < mMFragPagerAdapter.getCount(); i++) {
	        actionBar.addTab(
	                actionBar.newTab()
	                        .setText(mMFragPagerAdapter.getPageTitle(i))
	                        .setTabListener(this));
	    }
	    
	    /* Change highlight on tabs to reflect which is chosen */
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {}
	
	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {}

	@Override
	protected void onResume(){
		/* Register receiver */
		registerReceiver(intentReceiver, intentFilter);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		/* Unregister receiver */
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		/* Unregister receiver */
		unregisterReceiver(intentReceiver);
		super.onPause();
	}


}
