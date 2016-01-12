package com.uos.uosinfo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2016-01-12.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "uosinfo";
    private static final int DATABASE_VERSION = 1;
    private Dao<Library,String> libraries;
    private Dao<PathFinder,String> pathFinders;
    Context mContext;
    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME, null  , DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Library.class);
            TableUtils.createTable(connectionSource, PathFinder.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{
            TableUtils.dropTable(connectionSource,Library.class,true);
            TableUtils.dropTable(connectionSource,PathFinder.class,true);

            TableUtils.createTable(connectionSource, Library.class);
            TableUtils.createTable(connectionSource, PathFinder.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Dao<Library,String> getLibraryDao() throws SQLException{
        if(libraries == null)
            libraries = getDao(Library.class);
        return libraries;
    }
    public Dao<PathFinder,String> getPathFinderDao() throws SQLException{
        if(pathFinders == null)
            pathFinders = getDao(PathFinder.class);
        return pathFinders;
    }


    public boolean insertLibrary(Library library) throws SQLException{
        return (getLibraryDao().create(library)>0);
    }
    public boolean updateLibrary(Library library) throws SQLException{
        return (getLibraryDao().update(library)>0);
    }
    public List<Library> selectLibrary() throws SQLException{
        return getLibraryDao().queryBuilder().orderBy("create_datetime",false).query();
    }
    public Date lastLibraryCreateAt() throws SQLException {
        Dao<Library,String> libraryDao = getLibraryDao();
        if(libraryDao.countOf()==0)
            return null;
        return libraryDao.query(libraryDao.queryBuilder().orderBy("create_datetime", false).limit(1L).prepare()).get(0).getCreateDatetime();
    }
    public boolean isExistLibrary(String objectID) throws SQLException{
        Dao<Library,String> libraryDao = getLibraryDao();
        if(libraryDao.countOf()==0)
            return false;
        return libraryDao.idExists(objectID);
    }

    public boolean hasPathFinderThisMonth(Date nowDate) throws SQLException{
        Dao<PathFinder,String> pathFinderDao = getPathFinderDao();
        if(pathFinderDao.countOf()==0)
            return false;
        return pathFinderDao.queryBuilder().where().ge("start_datetime",nowDate).and().le("end_datetime",nowDate).countOf()>0;
    }
    public List<PathFinder> selectPathFinderByMonth(int month) throws SQLException{
        Dao<PathFinder,String> pathFinderDao = getPathFinderDao();
        if(pathFinderDao.countOf()==0)
            return null;
        return pathFinderDao.queryBuilder().where().ge("MONTH(start_datetime)",month).and().le("MONTH(end_datetime)",month).query();
    }
}
