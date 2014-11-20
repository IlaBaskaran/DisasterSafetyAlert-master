package com.livequake.disastersafetyalert;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
	public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context ctx, Intent intent) {
		
		if (intent.getAction().equals(SMS_RECEIVED)) {
			/* Get SMS msg passed in */
			Bundle bundle = intent.getExtras();
			if(bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				final SmsMessage[] msgs = new SmsMessage[pdus.length];
				/* Retrieve SMS received */
				for(int i = 0; i < pdus.length; i++) {
					msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				}
				/* Check if SMS is relevant (starts with [LIGHTHOUSE ALERT]) */
				if(msgs.length > -1) {
					Log.i("SmsReceiver", "Message recieved: " + msgs[0].getMessageBody());
					for(int k = 0; k < msgs.length; k++) {
						String m = msgs[k].getMessageBody();
						if(m.startsWith(PeopleFragment.beginText)) {
							Log.i("SmsReceiver", "Relevant! " + msgs[k].getMessageBody());
							String type = m.substring(PeopleFragment.beginText.length(), PeopleFragment.beginText.length() + 1);
							Log.i("SmsReceiverB", type);
							String pNum = msgs[k].getOriginatingAddress();
							pNum = pNum.replaceAll("[^\\d.]", "");
							Intent iS = new Intent(ctx, UpdateDBService.class);
						    iS.putExtra("type", type);
							iS.putExtra("num", pNum);
							ctx.startService(iS);
							
							/*Intent broadcastIntent = new Intent();
							broadcastIntent.setAction("SMS_RECEIVED_ACTION");
							broadcastIntent.putExtra("sms", m);
							ctx.sendBroadcast(broadcastIntent); */
						}
						else {
							Log.i("SmsReceiver", "Not Relevant: " + msgs[k].getMessageBody());
						}
					}
				}
				
			}
		}
	}

}
