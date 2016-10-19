
 # 指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames

-keepattributes Exceptions,InnerClasses

#【指定不去忽略非公共的库类。 】
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontshrink

#【不预校验】
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontobfuscate

 #优化  不优化输入的类文件
-dontoptimize

#忽略警告
-ignorewarnings

# 保护注解
-keepattributes *Annotation*

-keep public class * extends android.app.Application
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.v4.**

# Parcel library
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持 enum 类也不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class android.support.** {*; }

#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################

#####################自己的混淆--start################


-dontwarn org.springframework.**
-dontwarn me.himanshusoni.quantityview.**
-dontwarn com.squareup.otto.**
-dontwarn com.marshalchen.ultimaterecyclerview.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.nineoldandroids.**
-dontwarn com.daimajia.slider.library.**
-dontwarn in.srain.cube.views.ptr.**
-dontwarn okio.**

#-keep class com.nuesoft.woaccept.model.** { *;}
-keep class org.springframework.** { *;}
-keep class com.google.gson.** { *;}
-keep class com.squareup.otto.** { *;}
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class com.marshalchen.ultimaterecyclerview.** { *;}

#联通蓝牙读卡器终端sdk
-keep class com.kaeridcard.**{*;}


# nineoldandroids
-keep interface com.nineoldandroids.view.** { *; }
-keep class com.nineoldandroids.** { *; }
-keep class com.liulishuo.magicprogresswidget.** { *; }
-keep class com.daimajia.slider.library.** { *; }
-keep class in.srain.cube.views.ptr.** { *; }
-keep class okio.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#####################自己的混淆--end################