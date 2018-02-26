package cn.ltwc.bitmaputils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * TODO:Glide全局配置类（一定要在manifest里面申明如下内容） <meta-data
 * android:name="cn.ltwc.cft.utils.MyGlideModule" android:value= "GlideModule"
 * /> <!-- Glide是允许多个设置Model的所以必然会有冲突（如果有多个lib项目的话），可以使用 --> <!-- <meta-data
 * android:name="cn.ltwc.cft.utils.MyGlideModule" tools:node= ”remove”/> -->
 * <!-- 来避免这一类的问题 -->
 *
 * @author LZL
 */
@SuppressWarnings("unused")
public class MyGlideModule implements GlideModule {
    public static int yourSizeInBytes = 204857600;

    public MyGlideModule() {

        // TODO Auto-generated constructor stub
    }

    // 在这里创建设置内容
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // TODO Auto-generated method stub
        // 设置图片质量
        // builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        // 设置磁盘缓存到应用的内部目录，并且设置了最大的大小为 100M
        // 缓存目录为程序内部缓存目录/data/data/your_package_name/cache/image_manager_disk_cache/(不能被其它应用访问)
//		builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
//				yourSizeInBytes));
        // 设置磁盘缓存到外部存储（也设置了最大大小为 100M）
        // 一般存放临时缓存数据,路径：SDCard/Android/data/your_package_name/cache/image_manager_disk_cache，
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,
                yourSizeInBytes));
        // *******************************
        // 推荐使用上面两种缓存路劲，第一种路劲可以随着APP卸载而卸载。
        // *******************************
        // 设置缓存到指定文件路劲（getMyCacheLocationWithoutIO()为获取自定义缓存路劲）
        // builder.setDiskCache(new
        // DiskLruCacheFactory(getMyCacheLocationWithoutIO(), yourSizeInBytes));
        // 或者
        // builder.setDiskCache(new DiskCache.Factory() {
        // @Override
        // public DiskCache build() {
        // File cacheLocation = getMyCacheLocationBlockingIO();
        // cacheLocation.mkdirs();
        // return DiskLruCacheWrapper.get(cacheLocation, yourSizeInBytes);
        // }
        //
        // });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // TODO Auto-generated method stub

    }

    /**
     * 创建自己的缓存文件
     *
     * @return
     */
    private File getMyCacheLocationBlockingIO() {
        // TODO Auto-generated method stub
        return null;
    }
}
