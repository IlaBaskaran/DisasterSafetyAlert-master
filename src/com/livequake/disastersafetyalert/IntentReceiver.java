package com.livequake.disastersafetyalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class IntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctx, Intent intent) {
		String action = intent.getAction();
		if (action.equals(PushManager.ACTION_PUSH_RECEIVED)) {
			//Intent rssIntent = getIntent();
			/*Intent iR = new Intent(ctx, RespondActivity.class);
			iR.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(iR);*/
			Toast t = Toast.makeText(ctx, "afjklewjflsejfkld", Toast.LENGTH_LONG);
			t.show();
		}
		else if (action.equals(PushManager.ACTION_NOTIFICATION_OPENED)) {
			// user opened the notification so we launch the application

            // This intent is what will be used to launch the activity in our application
			Intent lLaunch = new Intent(Intent.ACTION_MAIN);

            // Main.class can be substituted any activity in your android project that you wish
            // to be launched when the user selects the notification from the Notifications drop down
			lLaunch.setClass(UAirship.shared().getApplicationContext(),	MainFragmentActivity.class);
			lLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // copy the intent data from the incoming intent to the intent
            // that we are going to launch
			//copyIntentData(intent, lLaunch);
			lLaunch = (Intent) intent.clone();

			UAirship.shared().getApplicationContext().startActivity(lLaunch);

			
			Toast t = Toast.makeText(ctx, "opened", Toast.LENGTH_LONG);
			t.show();
		}
		else if (action.equals(PushManager.ACTION_REGISTRATION_FINISHED)) {
			String ap_id = intent.getStringExtra(PushManager.EXTRA_APID);
		}
	}
}
