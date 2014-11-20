package com.livequake.disastersafetyalert;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class PeopleActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people, menu);
		return true;
	}
}
