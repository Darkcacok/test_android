package com.example.test_android;

import android.provider.BaseColumns;

public final class SampleDBContract {

    public static class Specialty implements BaseColumns {
        public static final String TABLE_NAME = "specialty";
        public static final String COLUMN_SPECIALTY_ID = "specialty_id";
        public static final String COLUMN_NAME = "name";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                COLUMN_SPECIALTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT" + ")";
    }

    public static class Employee implements BaseColumns{
        public static final String TABLE_NAME = "employee";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_AVATAR_URL = "avatar_url";
        public static final String COLUMN_SPECIALTY_ID = "specialty_id";

        public static final  String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + "  TEXT, " +
                COLUMN_DATE_OF_BIRTH + " INTEGER, " +
                COLUMN_AVATAR_URL + " TEXT, " +
                COLUMN_SPECIALTY_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_SPECIALTY_ID + ") REFERENCES " +
                Specialty.TABLE_NAME + "(" + Specialty.COLUMN_SPECIALTY_ID + ") " + ")";
    }
}


