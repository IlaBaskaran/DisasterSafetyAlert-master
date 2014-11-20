package com.livequake.disastersafetyalert;
import android.app.Application;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

public class DisasterSafetyAlert extends Application {
	
	@Override
	public void onCreate(){
		
		
		
		AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);
		options.developmentAppKey = "I-CgZLGxTh2d9VZomOdKIw";
		options.productionAppKey = "I-CgZLGxTh2d9VZomOdKIw";
		options.developmentAppSecret = "EhD6aihuTeeXv679fFztIg";
		options.inProduction = false; //determines which app key to use
		options.gcmSender = "173055454012";
		UAirship.takeOff(this, options);
		PushManager.enablePush();
		
	
/*		
		//use CustomPushNotificationBuilder to specify a custom layout
        CustomPushNotificationBuilder nb = new CustomPushNotificationBuilder();

        nb.statusBarIconDrawableId = R.drawable.icon_small;//custom status bar icon

        nb.layout = R.layout.notification;
        nb.layoutIconDrawableId = R.drawable.icon;//custom layout icon
        nb.layoutIconId = R.id.icon;
        nb.layoutSubjectId = R.id.subject;
        nb.layoutMessageId = R.id.message;

        // customize the sound played when a push is received
        //nb.soundUri = Uri.parse("android.resource://"+this.getPackageName()+"/" +R.raw.cat);

        PushManager.shared().setNotificationBuilder(nb);
        PushManager.shared().setIntentReceiver(IntentReceiver.class);*/
		PushManager.shared().setIntentReceiver(IntentReceiver.class);
		
        PushPreferences prefs = PushManager.shared().getPreferences();
        prefs.setSoundEnabled(true);
        prefs.setVibrateEnabled(true);
		
		String apid = PushManager.shared().getAPID();
		Logger.info("My Application onCreate - App APID: " + apid);
	   }
}
