package cn.ltwc.cft.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.beans.NoteBean;

public class NoteDB extends SQLiteOpenHelper {
    private static String notedbName = "Note.db";// 数据库的名字
    private static int version = 1;// 数据库版本号
    private static NoteDB instance = new NoteDB();// 本类的操作对象
    private String noteTableName = "note";// 数据库的表名

    private NoteDB() {
        super(MyApplication.getInstance(), notedbName, null, version);
        // TODO Auto-generated constructor stub
    }

    /**
     * 返回本类的操作对象
     *
     * @return
     */
    public static NoteDB getInstance() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreatTableSql = "create table "
                + noteTableName
                + "(title varchar(17),content varchar(1001),completeTime varchar(20),currentTime varchar(100))";

        db.execSQL(CreatTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + noteTableName);
        onCreate(db);
    }

    /**
     * 向数据库中加入记事
     *
     * @param notebean 记事的bean
     */
    public void addNote(NoteBean notebean) {

        String sql = "insert into " + noteTableName
                + "(title,content,completeTime,currentTime) values(?,?,?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(
                sql,
                new String[]{notebean.getNoteTitle(),
                        notebean.getNoteContent(), notebean.getCompleteTime(),
                        notebean.getCurrentTime()});
        db.close();
    }

    /**
     * 通过查询数据库得到数据库里面的内容
     *
     * @return
     */
    public List<NoteBean> getAll() {
        List<NoteBean> allNote = new ArrayList<NoteBean>();
        List<NoteBean> temp = new ArrayList<NoteBean>();
        String sql = "select * from " + noteTableName;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String completeTime = cursor.getString(cursor
                    .getColumnIndex("completeTime"));
            String currentTime = cursor.getString(cursor
                    .getColumnIndex("currentTime"));
            NoteBean noteBean = new NoteBean(title, content, completeTime,
                    currentTime);
            temp.add(noteBean);
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            allNote.add(temp.get(i));
        }
        return allNote;
    }

    /**
     * 修改笔记内容
     *
     * @param notebean    修改的笔记的bean
     * @param currentTime 通过上次完成的时间来匹配修改
     */
    public void update(NoteBean notebean, String currentTime) {

        String sql = "update "
                + noteTableName
                + " set title=?,content=?,completeTime=?,currentTime=? where currentTime='"
                + currentTime + "'";
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(
                sql,
                new String[]{notebean.getNoteTitle(),
                        notebean.getNoteContent(), notebean.getCompleteTime(),
                        notebean.getCurrentTime()});
        db.close();
    }

    public void deleteItem(String currentTime) {
        String sql = "DELETE FROM " + noteTableName + " where currentTime=?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{currentTime});
        db.close();
    }
}
