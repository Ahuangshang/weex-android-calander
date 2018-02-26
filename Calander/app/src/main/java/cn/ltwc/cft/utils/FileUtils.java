package cn.ltwc.cft.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.beans.CityCodeBean;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.utils.LogUtil;

public class FileUtils {
    /**
     * 判断文件是否存在
     *
     * @param path 文件的路径
     * @return
     */
    public static boolean isExit(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        } else {
            File file = new File(path);
            if (file.exists() && file.length() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 将Assets目录中的文件拷贝到sdcard中
     *
     * @param assetsPath 文件在assets目录中的位置
     * @param sdcardPath 文件在sdcard目录中的位置
     */
    public static void copyAssetsToSdcard(String assetsPath, String sdcardPath) {
        InputStream in = null;
        OutputStream os = null;
        try {
            // 获取.db在assets中的输入流
            in = MyApplication.getInstance().getAssets().open(assetsPath);
            // 获取.db需要在SDCARD中存放的输出流
            os = new FileOutputStream(sdcardPath);
            // 定义缓冲器
            byte[] buffer = new byte[1024];
            // 当前读取数据的长度
            int len = 0;
            // 读取输入流到数组中
            while ((len = in.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            // 提交缓冲区文件
            os.flush();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * 创建缓存文件夹
     *
     * @param context
     * @return
     */
    public static String buildCache(Context context) {
        String cachePath = "";
        if (context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().toString();
        } else if (context.getCacheDir() != null) {
            cachePath = context.getCacheDir().toString();
        }
        return cachePath;
    }

    /**
     * 获取应用专属缓存目录
     * android 4.4及以上系统不需要申请SD卡读写权限
     * 因此也不用考虑6.0系统动态申请SD卡读写权限问题，切随应用被卸载后自动清空 不会污染用户存储空间
     *
     * @param context 上下文
     * @param type    文件夹类型 可以为空，为空则返回API得到的一级目录
     * @return 缓存文件夹 如果没有SD卡或SD卡有问题则返回内存缓存目录，否则优先返回SD卡缓存目录
     */
    public static File getCacheDirectory(Context context, String type) {
        File appCacheDir = getExternalCacheDirectory(context, type);
        if (appCacheDir == null) {
            appCacheDir = getInternalCacheDirectory(context, type);
        }
        if (appCacheDir == null) {
            LogUtil.e("getCacheDirectory fail ,the reason is mobile phone unknown exception !");
        } else {
            if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
                LogUtil.e("getCacheDirectory fail ,the reason is make directory fail !");
            }
        }
        return appCacheDir;
    }

    /**
     * 获取SD卡缓存目录
     *
     * @param context 上下文
     * @param type    文件夹类型 如果为空则返回 /storage/emulated/0/Android/data/app_package_name/cache
     *                否则返回对应类型的文件夹如Environment.DIRECTORY_PICTURES 对应的文件夹为 .../data/app_package_name/files/Pictures
     *                {@link Environment#DIRECTORY_MUSIC},
     *                {@link Environment#DIRECTORY_PODCASTS},
     *                {@link Environment#DIRECTORY_RINGTONES},
     *                {@link Environment#DIRECTORY_ALARMS},
     *                {@link Environment#DIRECTORY_NOTIFICATIONS},
     *                {@link Environment#DIRECTORY_PICTURES}, or
     *                {@link Environment#DIRECTORY_MOVIES}.or 自定义文件夹名称
     * @return 缓存目录文件夹 或 null（无SD卡或SD卡挂载失败）
     */
    public static File getExternalCacheDirectory(Context context, String type) {
        File appCacheDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(type)) {
                appCacheDir = context.getExternalCacheDir();
            } else {
                appCacheDir = context.getExternalFilesDir(type);
            }

            if (appCacheDir == null) {// 有些手机需要通过自定义目录
                appCacheDir = new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getPackageName() + "/cache/" + type);
            }

            if (appCacheDir == null) {
                LogUtil.e("getExternalDirectory fail ,the reason is sdCard unknown exception !");
            } else {
                if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
                    LogUtil.e("getExternalDirectory fail ,the reason is make directory fail !");
                }
            }
        } else {
            LogUtil.e("getExternalDirectory fail ,the reason is sdCard nonexistence or sdCard mount fail !");
        }
        return appCacheDir;
    }

    /**
     * 获取内存缓存目录
     *
     * @param type 子目录，可以为空，为空直接返回一级目录
     * @return 缓存目录文件夹 或 null（创建目录文件失败）
     * 注：该方法获取的目录是能供当前应用自己使用，外部应用没有读写权限，如 系统相机应用
     */
    public static File getInternalCacheDirectory(Context context, String type) {
        File appCacheDir = null;
        if (TextUtils.isEmpty(type)) {
            appCacheDir = context.getCacheDir();// /data/data/app_package_name/cache
        } else {
            appCacheDir = new File(context.getFilesDir(), type);// /data/data/app_package_name/files/type
        }

        if (!appCacheDir.exists() && !appCacheDir.mkdirs()) {
            LogUtil.e("getInternalDirectory fail ,the reason is make directory fail !");
        }
        return appCacheDir;
    }

    /**
     * 读取目录下的城市信息
     *
     * @param
     * @return
     */
    private static List<CityCodeBean> readCity() {
        String fileName = "city.txt";
        List<CityCodeBean> cities = new ArrayList<CityCodeBean>();
        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(MyApplication.getInstance()
                    .getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                String city = line.substring(0, line.indexOf(":"));
                String code = line.substring(line.indexOf(":") + 1,
                        line.length());
                CityCodeBean bean = new CityCodeBean(city, code);
                cities.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return cities;
    }

    /**
     * 获取城市的code
     *
     * @param city
     * @return
     */
    public static String getCityCode(String city) {
        if (city.endsWith("市")) {
            city = city.substring(0, city.length() - 1);
        }
        String code = "";
        List<CityCodeBean> cities = readCity();
        for (int i = 0; i < cities.size(); i++) {
            if (city.equals(cities.get(i).getCity())) {
                code = cities.get(i).getCode();
                return code;
            }
        }
        if (TextUtils.isEmpty(code)) {
            for (int i = 0; i < cities.size(); i++) {
                if (city.contains(cities.get(i).getCity())) {
                    code = cities.get(i).getCode();
                    return code;
                }
            }
        }

        return code;
    }

    public static Intent getFileIntent(File file) {

        String type = getMIMEType(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(MyApplication.getInstance().getApplicationContext(), "cn.ltwc.cft.fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//这句话一定要添加
        } else {
            uri = Uri.fromFile(file);
        }
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }

    public static void writeToSDCard(String fileName, InputStream input) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File directory = Constant.directory;
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = new byte[2048];
                int j = 0;
                while ((j = input.read(b)) != -1) {
                    fos.write(b, 0, j);
                }
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            LogUtil.i("NO SDCard.");
        }
    }

    private static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
     /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
       
     /* 依扩展名的类型决定MimeType */
        if (end.equals("pdf")) {
            type = "application/pdf";//
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4") || end.equals("rmvb") || end.equals("avi")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            /* android.permission.INSTALL_PACKAGES */
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pptx") || end.equals("ppt")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("docx") || end.equals("doc")) {
            type = "application/vnd.ms-word";
        } else if (end.equals("xlsx") || end.equals("xls")) {
            type = "application/vnd.ms-excel";
        } else {
//       /*如果无法直接打开，就跳出软件列表给用户选择 */    
            type = "*/*";
        }
        return type;
    }

    public static boolean createPath(String path) {
        File f = new File(path);
        return f.exists() || f.mkdirs();
    }

    public static File saveFile(String file, InputStream inputStream) {
        File f = null;
        OutputStream outSm = null;
        try {
            f = new File(file);
            String path = f.getParent();
            if (!createPath(path)) {
                return null;
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            outSm = new FileOutputStream(f);
            byte[] buffer = new byte[4 * 1024];
            while ((inputStream.read(buffer)) != -1) {
                outSm.write(buffer);
            }
            outSm.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (outSm != null) outSm.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return f;
    }

    public static Drawable getImageDrawable(String file) {
        if (!isExit(file)) return null;
        try {
            InputStream inp = new FileInputStream(new File(file));
            return BitmapDrawable.createFromStream(inp, "img");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
