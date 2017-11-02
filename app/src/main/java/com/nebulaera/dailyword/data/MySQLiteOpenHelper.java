package com.nebulaera.dailyword.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nebulaera.dailyword.constants.SQLiteTableConstants;

/**
 * @author Barry 2017/11/2
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String LYJ_TAG = "LYJ_MySQLiteOpenHelper";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(LYJ_TAG, "[sql onCreate]");
        String sql = "create table " + SQLiteTableConstants.TABLE_DAILY
                + "(_id integer primary key autoincrement,"
                + SQLiteTableConstants.DAILY_CONTENT + " varchar(20),"
                + SQLiteTableConstants.DAILY_TIME + " time)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(LYJ_TAG, "[sql onUpgrade]");
    }
}
