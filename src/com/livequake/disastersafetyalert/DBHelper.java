package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "DSAdb.db";
    
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Method is called during creation of the database
    @Override
	public void onCreate(SQLiteDatabase db) {
    	db.execSQL(DSAContract.AlertContactTable.SQL_CREATE_ACDB);		
	}
    
    // Method is called during upgrade of the database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DSAContract.AlertContactTable.SQL_DELETE_ACDB);
        onCreate(db);
	}
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /* Get all names from Alert Contact List */
    public List<String> getAllNames() {
		List<String> contactList = new ArrayList<String>();
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_ALL, null);
	 
	    /* looping through all rows and adding to list */
	    if (cursor.moveToFirst()) {
	        do {
	            contactList.add(cursor.getString(1));
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	    return contactList;
	}
    
    /* Get list of all phone numbers from Alert Contact List */
    public List<String> getAllNumbers() {
		List<String> contactList = new ArrayList<String>();
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_ALL, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            contactList.add(cursor.getString(2));
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	    return contactList;
	}    
	
    /* Get safety values of all Alert Contacts */
    public List<Integer> getAllSafety() {
		List<Integer> contactList = new ArrayList<Integer>();
	    
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_ALL, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            contactList.add(cursor.getInt(3));
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	    return contactList;
	}

    /* Get Phone Number of Specific Contact from Name */
    public String getPhone(String name) {
    	String num = "";
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_PHONE + "\"" + name + "\";", null);
    	
    	if(cursor.moveToFirst()) {
    		if(cursor != null) {
    			num = cursor.getString(0);
    			Log.i("getP", num);
    		}
    	}
    	
    	cursor.close();
    	db.close();
    	return num;
    }

    /* Get Safety from Name */
    public String getSafety(String name) {
    	String num = "";
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_SAFETY + "\"" + name + "\";", null);
    	
    	if(cursor.moveToFirst()) {
    		if(cursor != null) {
    			num = cursor.getString(0);
    			Log.i("gets", num);
    		}
    	}
    	
    	cursor.close();
    	db.close();
    	return num;
    }
}
