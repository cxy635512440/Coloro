package com.goshine.player.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goshine.player.database.SQLiteHelperOrm;
import com.goshine.player.exception.Logger;
import com.goshine.player.po.POMedia;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

public final class FileBusiness {

	private static final String TABLE_NAME = "files";
	private static final String TAG = "FileBusiness";

	public static List<POMedia> getAllSortFiles() {
		SQLiteHelperOrm db = new SQLiteHelperOrm();
		try {
			Dao<POMedia, Long> dao = db.getDao(POMedia.class);
			QueryBuilder<POMedia, Long> query = dao.queryBuilder();
			query.orderBy("title_key", true);
			return dao.query(query.prepare());
		} catch (SQLException e) {
			Logger.e(e);
		} finally {
			if (db != null)
				db.close();
		}
		return new ArrayList<POMedia>();
	}

}
