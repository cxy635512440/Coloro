package com.goshine.player.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.goshine.player.OPlayerApplication;
import com.goshine.player.exception.Logger;
import com.goshine.player.po.POMedia;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class SQLiteHelperOrm extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "oplayer.db";
	private static final int DATABASE_VERSION = 1;

	public SQLiteHelperOrm(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public SQLiteHelperOrm() {
		super(OPlayerApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, POMedia.class);
		} catch (SQLException e) {
			Logger.e(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int arg2, int arg3) {
		try {
			TableUtils.dropTable(connectionSource, POMedia.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Logger.e(e);
		}
	}
}