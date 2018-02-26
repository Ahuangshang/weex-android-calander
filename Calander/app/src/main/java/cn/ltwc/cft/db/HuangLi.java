package cn.ltwc.cft.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.beans.YiJiBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.cft.utils.FileUtils;

/**
 * 从assets里面复制数据库到手机内存
 *
 * @author LZL
 */
public class HuangLi {
    private SQLiteDatabase db;// 数据库操作类
    private String dbName = "Huangli.db";// 数据库的名字
    private String tableName = "huangli";// 数据库的表名
    private String dbAssetsPath = "db/Huangli.db";// 数据库在Assets中存在的路径
    private String dbSdcardPath = Environment.getDataDirectory()
            + File.separator + "data" + File.separator
            + Constant.APPPACKAGENAME + File.separator + dbName;// 数据库在Sdcard中存在的路径

    private static Integer loc = 1;

    // 单例
    // 声明一个空的本类静态对象-----------------------------------------
    private static HuangLi instance;

    // 私有化本类构造函数-----------------------------------------
    private HuangLi() {
        // 实例化db
        // 1.判断city.db是不是在本app对应的包中,如果不存在
        if (!FileUtils.isExit(dbSdcardPath)) {
            // false:
            // 1.将city.db拷贝到本app对应的包中
            FileUtils.copyAssetsToSdcard(dbAssetsPath, dbSdcardPath);
        }

        // 2.实例化db
        db = MyApplication.getInstance().openOrCreateDatabase(dbSdcardPath,
                Context.MODE_PRIVATE, null);
    }

    // 1 2 3
    // 刚好进入 刚好进入 刚好进入

    /**
     * 安全：可以唯一保证本对象是单一实例 相对高效:只有第一次调用该方法会进入互斥锁，以后都不会进入 节省内存:用的时候分配内存
     *
     * @return
     */

    // 唯一入口：静态的共有的
    public static HuangLi getInstance() {
        if (instance == null) {
            synchronized (loc) {
                return instance == null ? instance = new HuangLi() : instance;
            }
        }
        return instance;
    }

    // -------------------------------------------------

    /**
     * 查询当前阳历对应的宜忌
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public YiJiBean quearHuangli(String year, String month, String day) {
        YiJiBean bean = new YiJiBean();
        String sql = "SELECT * FROM " + tableName
                + " WHERE y=? and m=? and d=?";
        Cursor c = db.rawQuery(sql, new String[]{year, month, day});
        while (c.moveToNext()) {
            String yi = c.getString(c.getColumnIndex("yi"));
            String ji = c.getString(c.getColumnIndex("ji"));
            bean.setYi(yi);
            bean.setJi(ji);
        }
        return bean;
    }
}
