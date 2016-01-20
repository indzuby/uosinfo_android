package com.uos.uosinfo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Departmenet;
import com.uos.uosinfo.domain.GreatMan;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.Notice;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.QnaBoard;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * Created by user on 2016-01-12.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "uosinfo";
    private static final int DATABASE_VERSION = 1;
    private Dao<Library,String> libraryDao;
    private Dao<PathFinder,String> pathFinderDao;
    private Dao<College,String> collegeDao;
    private Dao<Departmenet,String> departmentDao;
    private Dao<Notice,String> noticeDao;
    private Dao<QnaBoard,String> qnaDao;
    private Dao<Word,String> wordDao;
    private Dao<GreatMan,String> greatManDao;
    private Dao<ResultPathFinder,String> resultPathFinderDao;
    Context mContext;
    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME, null  , DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, College.class);
            TableUtils.createTable(connectionSource, Departmenet.class);
            TableUtils.createTable(connectionSource, GreatMan.class);
            TableUtils.createTable(connectionSource, Library.class);
            TableUtils.createTable(connectionSource, Notice.class);
            TableUtils.createTable(connectionSource, PathFinder.class);
            TableUtils.createTable(connectionSource, QnaBoard.class);
            TableUtils.createTable(connectionSource, ResultPathFinder.class);
            TableUtils.createTable(connectionSource, Word.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{
            TableUtils.dropTable(connectionSource, College.class,true);
            TableUtils.dropTable(connectionSource, Departmenet.class,true);
            TableUtils.dropTable(connectionSource, GreatMan.class,true);
            TableUtils.dropTable(connectionSource, Library.class,true);
            TableUtils.dropTable(connectionSource, Notice.class,true);
            TableUtils.dropTable(connectionSource, PathFinder.class,true);
            TableUtils.dropTable(connectionSource, QnaBoard.class,true);
            TableUtils.dropTable(connectionSource, ResultPathFinder.class,true);
            TableUtils.dropTable(connectionSource, Word.class,true);


            TableUtils.createTable(connectionSource, College.class);
            TableUtils.createTable(connectionSource, Departmenet.class);
            TableUtils.createTable(connectionSource, GreatMan.class);
            TableUtils.createTable(connectionSource, Library.class);
            TableUtils.createTable(connectionSource, Notice.class);
            TableUtils.createTable(connectionSource, PathFinder.class);
            TableUtils.createTable(connectionSource, QnaBoard.class);
            TableUtils.createTable(connectionSource, ResultPathFinder.class);
            TableUtils.createTable(connectionSource, Word.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Dao<College,String> getCollegeDao() throws SQLException{
        if(collegeDao == null)
            collegeDao = getDao(College.class);
        return collegeDao;
    }
    public Dao<Departmenet,String> getDepartmentDao() throws SQLException{
        if(departmentDao == null)
            departmentDao = getDao(Departmenet.class);
        return departmentDao;
    }
    public Dao<GreatMan,String> getGreatManDao() throws SQLException{
        if(greatManDao == null)
            greatManDao = getDao(GreatMan.class);
        return greatManDao;
    }
    public Dao<Library,String> getLibraryDao() throws SQLException{
        if(libraryDao == null)
            libraryDao = getDao(Library.class);
        return libraryDao;
    }
    public Dao<Notice,String> getNoticeDao() throws SQLException{
        if(noticeDao == null)
            noticeDao = getDao(Notice.class);
        return noticeDao;
    }
    public Dao<PathFinder,String> getPathFinderDao() throws SQLException{
        if(pathFinderDao == null)
            pathFinderDao = getDao(PathFinder.class);
        return pathFinderDao;
    }
    public Dao<QnaBoard,String> getQnaDao() throws SQLException{
        if(qnaDao == null)
            qnaDao = getDao(QnaBoard.class);
        return qnaDao;
    }
    public Dao<ResultPathFinder,String> getResultPathFinderDao() throws SQLException{
        if(resultPathFinderDao == null)
            resultPathFinderDao = getDao(ResultPathFinder.class);
        return resultPathFinderDao;
    }
    public Dao<Word,String> getWordDao() throws SQLException{
        if(wordDao == null)
            wordDao = getDao(Word.class);
        return wordDao;
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

    public boolean insertPathFinder(PathFinder pathfinder) throws SQLException{
        return (getPathFinderDao().create(pathfinder)>0);
    }
    public boolean hasPathFinderThisMonth(Date date) throws SQLException{
        Dao<PathFinder,String> pathFinderDao = getPathFinderDao();
        return pathFinderDao.queryBuilder().where().le("start_datetime",date).and().ge("end_datetime",date).countOf()>0;
    }
    public List<PathFinder> selectPathFinderByDate(Date date) throws SQLException{
        return getPathFinderDao().queryBuilder().orderBy("display_order", true).where().le("start_datetime", date).and().ge("end_datetime",date).query();
    }
}
