package com.livequake.disastersafetyalert;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class UpdateDBService extends IntentService {
	public UpdateDBService() {
		super("UpdateDBService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		Bundle bundle = intent.getExtras();
		
		int status = Integer.parseInt(bundle.getString("type"));
		String p = bundle.getString("num");
		String phone = "";
		if(p.length() > 10) {
			phone = p.substring(1);
		}
		Log.i("getStat", bundle.getString("type"));
		Log.i("getOrig", p);
		Log.i("getNew", phone);
		
		if(status == 0 || status == 1) {
			
			Log.i("changing", "yeaaah");
			/* New Database Helper */
			DBHelper dbHelper = new DBHelper(this.getApplicationContext());
			SQLiteDatabase db = dbHelper.getWritableDatabase();
	
			/* Update safety value */
			ContentValues values = new ContentValues();
			values.put(DSAContract.AlertContactTable.COLUMN_NAME_SAFE, status);
	        
			db.update(DSAContract.AlertContactTable.TABLE_NAME,
					values,
					"PhoneNumber = \"" + phone + "\"", null);
			
			String gp = dbHelper.getPhone("Ryan Doherty");
			Log.i("getTablePhone", gp);
			String s = dbHelper.getSafety("Ryan Doherty");
			Log.i("safetyCheck", s);
		}
		else if(status == 2) {
			Intent iR = new Intent(this.getApplicationContext(), RespondActivity.class);
			iR.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			iR.putExtra("phone", phone);
    		startActivity(iR);
		}
	}

}
