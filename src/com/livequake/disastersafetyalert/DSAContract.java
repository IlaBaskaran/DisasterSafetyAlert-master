package com.livequake.disastersafetyalert;

import android.provider.BaseColumns;

public final class DSAContract {
	
	private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

	public DSAContract() {}
	
	/* Inner class that defines the table contents */
	public static abstract class AlertContactTable implements BaseColumns {
		public static final String TABLE_NAME = "AlertContacts";
		public static final String COLUMN_NAME_NAME_ID = "NameID";
		public static final String COLUMN_NAME_PHONE = "PhoneNumber";
		public static final String COLUMN_NAME_SAFE = "Safe";
		public static final String COLUMN_NAME_LAST = "LastContactDate";
		
		
		public static final String SQL_CREATE_ACDB = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_NAME_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SAFE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_LAST + TEXT_TYPE + " )";

		public static final String SQL_DELETE_ACDB = "DROP TABLE IF EXISTS " + TABLE_NAME;
		
		public static final String SQL_GET_ALL = "SELECT  * FROM " + TABLE_NAME;
		
		public static final String SQL_GET_PHONE = "SELECT " + COLUMN_NAME_PHONE +
				" FROM " + TABLE_NAME +
				" WHERE " + COLUMN_NAME_NAME_ID + " = ";
		
		public static final String SQL_GET_SAFETY = "SELECT " + COLUMN_NAME_SAFE +
				" FROM " + TABLE_NAME +
				" WHERE " + COLUMN_NAME_NAME_ID + " = ";
	}
}
