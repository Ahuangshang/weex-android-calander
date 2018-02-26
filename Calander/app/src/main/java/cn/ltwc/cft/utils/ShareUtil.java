package cn.ltwc.cft.utils;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cn.ltwc.cft.MyApplication;
import cn.ltwc.utils.ToastUtil;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by LZL on 17/10/2.
 */

public class ShareUtil {
    //支持分享的Platform名称
    private final List<String> platforms = new ArrayList<>(Arrays.asList(Wechat.NAME, WechatMoments.NAME, QQ.NAME));
    //各平台App的包名
    private final List<String> packageNames = new ArrayList<>(Arrays.asList("com.tencent.mm", "com.tencent.mm", "com.tencent.mobileqq"));
    //标题和内容的最大长度
    private final int titleLength = 30, contentLength = 40;
    private String defaultImg = "http://zerosboy.site/Ahuangshang/img/shareImg.png";

    public interface ShareType {
        int ShareTypeWechat = 0;
        int ShareTypeWechatMoments = 1;
        int ShareTypeQQ = 2;
    }

    private static class SingletonHolder {
        private static final ShareUtil INSTANCE = new ShareUtil();
    }

    public static ShareUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 执行分享操作
     *
     * @param title         分享标题(微博分享不使用)
     * @param content       分享内容(微信朋友圈不使用)
     * @param url           分享链接
     * @param image         分享的图片(分享微信和QQ是时候传入缩略图,分享微博的时候传入大图)
     * @param sharePlatform 分享类型(0:微信 1:微信朋友圈 2:QQ 3:新浪微博)
     */
    public void doShare(String title, String content, @NonNull String url, String image, @NonNull int sharePlatform) {
        if (!isPkgInstalled(packageNames.get(sharePlatform))) {
            ToastUtil.showMessage("没有安装客户端");
            return;
        }
        Platform.ShareParams params = new Platform.ShareParams();
        //截取标题长度
        if (title.length() > titleLength) {
            title = title.substring(0, titleLength - 1);
        }
        if (content.length() > contentLength) {
            content = content.substring(0, contentLength - 1);
        }
        switch (sharePlatform) {
            //微信好友及朋友圈
            case ShareType.ShareTypeWechat:
            case ShareType.ShareTypeWechatMoments: {
                if (sharePlatform == ShareType.ShareTypeWechat) {
                    params = new Wechat.ShareParams();
                    params.setText(content);
                } else {
                    params = new WechatMoments.ShareParams();
                }
                params.setShareType(Platform.SHARE_WEBPAGE);
                params.setTitle(title);
                params.setUrl(url);
                break;
            }
            //QQ
            case ShareType.ShareTypeQQ: {
                params = new QQ.ShareParams();
                params.setTitle(title);
                params.setText(content);
                params.setTitleUrl(url);
                break;
            }
            default: {
                ToastUtil.showMessage("不支持的平台");
                return;
            }
        }

        //设置图片
        if (!TextUtils.isEmpty(image)) {
            if (image.startsWith("http")) {
                params.setImageUrl(image);
            } else {
                params.setImagePath(image);
            }
        } else {
            params.setImageUrl(defaultImg);
        }

        Platform platform = ShareSDK.getPlatform(platforms.get(sharePlatform));
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //ToastUtil.showMessage("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //ToastUtil.showMessage("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //ToastUtil.showMessage("分享取消");
            }
        });
        platform.share(params);
    }

    /**
     * 判断手机是否安装了老版本的app
     *
     * @param pkgName
     * @return
     */
    public static boolean isPkgInstalled(String pkgName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApplication.getInstance().getPackageManager().getPackageInfo(pkgName, 0);
        } catch (Exception e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }
}
