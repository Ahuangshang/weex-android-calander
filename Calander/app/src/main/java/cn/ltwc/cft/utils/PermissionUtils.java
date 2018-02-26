package cn.ltwc.cft.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

/**
 * Created by gxy on 2017/3/6.
 * to do
 * 权限管理
 */

public class PermissionUtils {

    private static final String TAG = "PermissionUtils";

    /**
     * 对外检测权限的方法
     *
     * @param object     Activity或者Fragment
     * @param permission 权限
     * @return false或true
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean checkPermission(Object object, String permission) {
        // 未授权
        if (!checkSelfPermissionWrapper(object, permission)) {
            return false;
        }
        // 已授权
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean checkSelfPermissionWrapper(Object object, String permission) {
        if (object instanceof Activity) {
            Activity activity = (Activity) object;
            return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            throw new RuntimeException("object is not a activity or fragment");
        }
    }

    /**
     * 被用户拒绝过
     *
     * @param object     Activity或者Fragment
     * @param permission 权限
     * @return
     */
    public static boolean isRejected(Object object, String permission) {
        if (object instanceof Activity) {
            Activity activity = (Activity) object;
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.shouldShowRequestPermissionRationale(permission);
        } else {
            throw new RuntimeException("object is not a activity or fragment");
        }
    }

    /**
     * 请求权限
     *
     * @param object      Activity或者Fragment
     * @param permission  权限
     * @param requestCode 请求码
     */
    public static void requestPermissionsWrapper(Object object, String[] permission, int requestCode) {
        if (object instanceof Activity) {
            Activity activity = (Activity) object;
            ActivityCompat.requestPermissions(activity, permission, requestCode);
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            fragment.requestPermissions(permission, requestCode);
        } else {
            throw new RuntimeException("object is not a activity or fragment");
        }
    }


}
