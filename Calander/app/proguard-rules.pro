# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#---------------------------------1.基本指令区----------------------------------
 #指定代码的压缩级别
 -optimizationpasses 5
 #包明不混合大小写
# -dontusemixedcaseclassnames
 #不去忽略非公共的库类
 -dontskipnonpubliclibraryclasses
# #优化  不优化输入的类文件
# -dontoptimize
 #预校验
 -dontpreverify
 #混淆时是否记录日志
 -verbose
 # 混淆时所采用的算法
 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 #保护注解
 -keepattributes *Annotation*
 -optimizations !class/unboxing/enum
 -keepattributes *JavascriptInterface*
 -keepattributes SourceFile,LineNumberTable
  #忽略警告
    -ignorewarning
 #------------------------------1.基本指令区--------------------------------------------
 #---------------------------------2.默认保留区---------------------------------
 # 保持哪些类不被混淆
#  -keep public class * extends android.app.Fragment
#  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Application
#  -keep public class * extends android.app.Service
#  -keep public class * extends android.content.BroadcastReceiver
#  -keep public class * extends android.content.ContentProvider
#  -keep public class * extends android.app.backup.BackupAgentHelper
#  -keep public class * extends android.preference.Preference
#  -keep public class com.android.vending.licensing.ILicensingService
   #如果有引用v4包可以添加下面这行
#  -keep public class * extends android.support.v4.app.Fragment
#  -keep public class * extends android.view.View
  #如果引用了v4或者v7包
#   -dontwarn android.support.**
    #保持 native 方法不被混淆
    -keepclasseswithmembernames class * {
        native <methods>;
    }
    #保持 Parcelable 不被混淆
     -keep class * implements android.os.Parcelable {
       public static final android.os.Parcelable$Creator *;
     }
    #保持 Serializable 不被混淆
     -keepnames class * implements java.io.Serializable

     #保持 Serializable 不被混淆并且enum 类也不被混淆
     -keepclassmembers class * implements java.io.Serializable {
         static final long serialVersionUID;
         private static final java.io.ObjectStreamField[] serialPersistentFields;
         !static !transient <fields>;
         !private <fields>;
         !private <methods>;
         private void writeObject(java.io.ObjectOutputStream);
         private void readObject(java.io.ObjectInputStream);
         java.lang.Object writeReplace();
         java.lang.Object readResolve();
     }
 #---------------------------------2.默认保留区---------------------------------
-keep public class cn.ltwc.cft.weex.WeexActivity
 #---------------------------------3.反射相关的类和方法(我们需要手动处理的地方)-----------------------
 -keep public class cn.ltwc.cft.entiy.**{ *;}
 -keep public class cn.ltwc.cft.beans.**{ *;}
 -keep public class cn.ltwc.cft.weex.**{ *;}
 #不要混淆Activity的所有属性(与方法)
  -keepclasseswithmembers class cn.ltwc.cft.activity.** {
      <fields>;
      #<methods>;
  }
 #---------------------------------3.反射相关的类和方法-----------------------
 #---------------------------------4.与js互相调用的类------------------------
#---------------------------------4.与js互相调用的类------------------------
 #---------------------------------5.第三方jar-----------------------
#这里对第三方jar包的类不进行混淆
 -keep class android.support.v4.view.**{ *;}
 -keep class org.apache.http.entity.mime.**{ *;}
 -keep class javax.security.sasl.**{ *;}
 -keep class javax.activation.**{ *;}
 -keep class javax.annotation.**{ *;}
 -keep class javax.swing.**{ *;}
 -keep class java.rmi.**{ *;}
 -keep class org.apache.**{ *;}
 -keep class com.sina.sso**{ *;}
 -keep class cn.sharesdk.**{ *;}
 -keep class com.android.volley{ *;}
 -keep class com.sina.weibo.sdk{ *;}
 -keep class com.unionpay{ *;}
 -keep class com.tencent.**{ *;}
 -keep class m.framework{ *;}
 -keep class com.pingplusplus.android{ *;}
 -keep class org.java_websocket{ *;}
 -keep class org.gradle{ *;}
 -keep class hirondelle.date4j{ *;}
 -keep class com.avos.**{ *;}
 -keep class com.loopj.android.http{ *;}
 -keep class com.ta.utdid2.**{ *;}
 -keep class com.ut.device.**{ *;}
 -keep class com.soundcloud.**{ *;}
 -keep class com.edmodo.cropper.**{ *;}
 -keep class cn.limc.androidcharts.**{ *;}
 -keep class org.bouncycastle.**{ *;}
 -dontoptimize
  -dontpreverify

  -dontwarn cn.jpush.**
  -keep class cn.jpush.** { *; }
   -dontwarn cn.jiguang.**
  -keep class cn.jiguang.** { *; }
   #==================gson==========================
   -dontwarn com.google.**
   -keep class com.google.gson.** {*;}
   #==================protobuf======================
   -dontwarn com.google.**
   -keep class com.google.protobuf.** {*;}
 -dontwarn javax.annotation.**
 -dontwarn javax.inject.**
 # OkHttp3
 -dontwarn okhttp3.logging.**
 -keep class okhttp3.internal.**{*;}
 -dontwarn okio.**
 # Retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
 # RxJava RxAndroid
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 # Gson
 -keep class com.google.gson.stream.** { *; }
 -keepattributes EnclosingMethod
# -keep class org.xz_sale.entity.**{*;}#这是你定义的实体类
 #---------------------------------5.第三方jar-----------------------
 -keep class sun.misc.Unsafe { *; }
 # Application classes that will be serialized/deserialized over Gson
 -keep class com.google.gson.examples.android.model.** { *; }
 -keep public class com.byl.bean.Expressions { *; }

#---------------------------------6.一些自定义view-----------------------
 #对于所有类，有这个构造函数不进行混淆,主要是为了在layout中的，自定义的view
# -keepclasseswithmembers class * {
#     public <init>(android.content.Context, android.util.AttributeSet);
# }
# -keepclasseswithmembers class * {
#     public <init>(android.content.Context, android.util.AttributeSet, int);
# }
 #这个主要是在layout 中写的onclick方法android:onclick="onClick"，不进行混淆
 -keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }
 -keepclassmembers class * {
    public <init>(org.json.JSONObject);
 }
 -keep class **.R$* {*;}
  #不混淆资源类
    -keepclassmembers class **.R$* {
        public static <fields>;
    }

    #百川hotfix
    #基线包使用，生成mapping.txt
    -printmapping mapping.txt
    #修复后的项目使用，保证混淆结果一致
    #-applymapping mapping.txt

    #hotfix
    #-keep class com.taobao.sophix.**{*;}
    #-keep class com.ta.utdid2.device.**{*;}
    #weex
    -keep class com.taobao.weex.**{*;}
    #防止inline
    -dontoptimize
#---------------------------------6.一些自定义view-----------------------
-keep public class * implements  com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.bumptech.** {
    *;
}
#---------------------------------7.SchemeUtil-----------------------
 -keep public class cn.ltwc.cft.utils.SchemeUtil
 -keepclassmembers class cn.ltwc.cft.utils.SchemeUtil { *; }
 -keep public class cn.ltwc.cft.data.Constant
  -keepclassmembers class cn.ltwc.cft.data.Constant { *; }
 #---------------------------------7.SchemeUtil-----------------------