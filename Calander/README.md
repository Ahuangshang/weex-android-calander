#Calander
#viewutils引用说明：
##在你的项目的AndroidManifest.xml文件里添加一下内容：
####      
         <meta-data
          android:name="cn.ltwc.bitmaputils.glide.MyGlideModule"
          android:value="GlideModule" />
#如果项目混淆的话，需要在proguard-rules.pro混淆文件里添加：
#####
     -keep public class * implements  com.bumptech.glide.module.GlideModule
     -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
         **[] $VALUES;
         public *;
     }
     -keep class com.bumptech.** {
         *;
     }

#weexsdk源码修改地方：
####WXSwipeLayout类里面finishPullRefresh()和finishPullLoad()里面注释了 if (mCurrentAction == PULL_REFRESH)和if (mCurrentAction == LOAD_MORE)条件
#
#
#
