package cn.ltwc.cft.utils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.cft.data.Constant;
import cn.ltwc.utils.LogUtil;
import cn.ltwc.utils.ToastUtil;
import rx.functions.Action1;

/**
 * Created by admin on 2017/3/22.
 */

public class DownLoadUtil {
    private static Action1<String> listener;
    private static Action1<Integer> progress;

    public static void downLoad(String url) {
        //LogUtil.d( "下载url:" + url);
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.showMessageLongCenter("需要SD卡。");
            return;
        }
        DownloaderTask task = new DownloaderTask();
        task.execute(url);
    }

    public static void downLoad(String url, Action1<String> lis) {
        listener = lis;
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.showMessageLongCenter("需要SD卡。");
            return;
        }
        DownloaderTask task = new DownloaderTask();
        task.execute(url);
    }

    public static void downLoadJS(String url, Action1<String> lis) {
        listener = lis;
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.showMessageLongCenter("需要SD卡。");
            return;
        }
        DownloaderTask task = new DownloaderTask(true);
        task.execute(url);
    }

    public static void downLoadWitheProgress(String url, Action1<Integer> pro) {
        progress = pro;
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtil.showMessageLongCenter("需要SD卡。");
            return;
        }
        DownloaderTask task = new DownloaderTask();
        task.execute(url);
    }

    // 内部类
    private static class DownloaderTask extends AsyncTask<String, Integer, String> {
        boolean downJs;

        public DownloaderTask() {
        }

        public DownloaderTask(boolean downJs) {
            this.downJs = downJs;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url = params[0];
            String fileName = getFileName(url);
            fileName = URLDecoder.decode(fileName);
            if (downJs) {
                if (fileName.contains(";")) {
                    fileName = fileName.split(";")[0];
                }
            }
            File directory = Constant.directory;
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, fileName);
            LogUtil.e("fileName" + fileName + "\nexists" + file.exists() + "\nfileLength" + file.length());
            try {
                HttpClient client = new DefaultHttpClient();
                // client.getParams().setIntParameter("http.socket.timeout",3000);//设置超时
                HttpGet get = new HttpGet(url);
                HttpResponse response = client.execute(get);
                if (HttpStatus.SC_OK == response.getStatusLine()
                        .getStatusCode()) {
                    HttpEntity entity = response.getEntity();
                    InputStream input = entity.getContent();
                    long total = entity.getContentLength();
                    //如果文件存在并且长度相同不再重复下载
                    if (file.exists() && file.length() == total) {
                        return fileName;
                    }
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        if (!directory.exists()) {
                            directory.mkdirs();
                        }
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(file);
                            byte[] buffer = new byte[2048];
                            int count = 0;
                            int read = 0;
                            int percent = 0;
                            int downPercent = 0;
                            while ((read = input.read(buffer)) != -1) {
                                count += read;
                                fos.write(buffer, 0, read);
                                percent = (int) (((double) count / total) * 100);
                                //每下载完成1%就通知修改下载进度
                                if (percent - downPercent >= 1) {
                                    downPercent = percent;
                                    publishProgress(percent);
                                }
                            }
                            fos.flush();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            if (fos != null) {
                                fos.close();
                            }
                        }
                    } else {
                        //LogUtil.i( "NO SDCard.");
                    }
                    input.close();
                    // entity.consumeContent();
                    return fileName;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
//            if (listener != null) {
//                closeProgressDialog();
//            }
            if (progress != null) {
                progress.call(100000);
            }
            if (result == null) {
                ToastUtil.showMessageLongCenter("连接错误！请稍后再试！");
                if (listener != null) {
                    listener.call(null);
                }
            } else {
                File directory = Constant.directory;
                File file = new File(directory, result);
                if (listener != null) {
                    listener.call(file.getAbsolutePath());
                } else {
                    if (!downJs) {
                        Intent intent = FileUtils.getFileIntent(file);
                        MyApplication.getInstance().startActivity(intent);
                    }
                    ToastUtil.showMessageLongCenter("已保存到SD卡。");
                }
            }
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            if (listener != null) {
//                showProgressDialog();
//            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            if (progress != null) {
                progress.call(values[0]);
            }
        }

    }


    private static void showProgressDialog() {
        MyApplication.getInstance().showProgressDialog();
    }

    private static void closeProgressDialog() {
        MyApplication.getInstance().disMissProgressDialog();
    }

    public static String getFileName(String url) {
        String filename = "";
        boolean isok = false;
        // 从UrlConnection中获取文件名称
        try {
            URL myURL = new URL(url);
            URLConnection conn = myURL.openConnection();
            if (conn == null) {
                return null;
            }
            Map<String, List<String>> hf = conn.getHeaderFields();
            if (hf == null) {
                return null;
            }
            Set<String> key = hf.keySet();
            if (key == null) {
                return null;
            }
            for (String skey : key) {
                List<String> values = hf.get(skey);
                for (String value : values) {
                    String result;
                    try {
                        result = new String(value.getBytes("utf8"), "utf-8");
                        int location = result.indexOf("filename");
                        if (location >= 0) {
                            result = result.substring(location
                                    + "filename".length());
                            filename = result
                                    .substring(result.indexOf("=") + 1).replace("\"", "");
                            isok = true;
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if (isok) {
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 从路径中获取
        if (TextUtils.isEmpty(filename)) {
            filename = url.substring(url.lastIndexOf("/") + 1);
        }
        return filename;
    }
}
