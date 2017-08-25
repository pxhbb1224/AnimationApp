# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
## proguard configuration common for all Android apps
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 以下两个命令配合让类的路径给删除了
-allowaccessmodification
-repackageclasses ''

# 记录生成的日志数据，在proguard目录下
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt

-dontwarn

-keepattributes Exceptions,InnerClasses,Deprecated,*Annotation*,EnclosingMethod
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-keepnames class * implements java.io.Serializable

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


## Android Support Library
-keep class android.support.v4.**  { *; }
-keep interface android.support.v4.** { *; }
-dontwarn android.support.v4.**

# Allow obfuscation of android.support.v7.internal.view.menu.**
# to avoid problem on Samsung 4.2.2 devices with appcompat v21
# see https://code.google.com/p/android/issues/detail?id=78377
# see http://stackoverflow.com/questions/24809580/noclassdeffounderror-android-support-v7-internal-view-menu-menubuilder/26641388
-keep class !android.support.v7.internal.view.menu.* implements android.support.v4.internal.view.SupportMenu, android.support.v7.** {*;}
-keep interface android.support.v7.** { *; }


## for JSONObject
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}


## proguard configuration for Gson
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

#This is extra - added by me to exclude gson obfuscation
-keep class com.google.gson.** { *; }

#This is for self defined Objects
-keep class base.stock.data.** { *; }
-keep class base.stock.chart.data.** { *; }
-keep class base.stock.common.data.** { *; }
-keep class base.stock.openaccount.data.** { *; }
-keep class base.stock.openaccount.dataNative.** { *; }
-keep class base.stock.tiger.trade.data.** { *; }
-keep class com.tigerbrokers.stock.data.** { *; }


## proguard configuration for WeiXin
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}


## proguard configuration for UMeng
#-libraryjars ./../libs/umeng_sdk.jar
# 以下类过滤不混淆
-keep public class * extends com.umeng.**
# 以下包不进行过滤
-keep class com.umeng.** { *; }
# 如果您使用了双向反馈功能，还需要添加下面代码，以免我们自定义的UI被混淆
-keep public class com.umeng.fb.ui.ThreadView { }
# 保留资源引用文件R.java
-keep public class com.tigerbrokers.stock.R$* {
    public static final int *;
}
-dontwarn com.umeng.**


## proguard configuration for Yixin
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}


## proguard configuration for apache http
#-libraryjars ./../libs/httpmime-4.3.4.jar
#-libraryjars ./../libs/httpcore-4.3.2.jar
-keep class org.apache.**
-keep interface org.apache.**
-dontwarn org.apache.**


## joda-time
-dontwarn org.joda.convert.**


## newrelic
-keep class com.newrelic.** { *; }
-dontwarn com.newrelic.**


## fabric
-keepattributes SourceFile,LineNumberTable
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**


## paho
-keep public class org.eclipse.paho.** { *; }


## file chooser
-keepclassmembers class * extends android.webkit.WebChromeClient {
   public void openFileChooser(...);
}


## OkHttp 2
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn okio.**


## OkHttp 3
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

## weixin sso
-keep class com.tencent.mm.sdk.** {
   *;
}

## facebook fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

-dontwarn javax.annotation.**

## amazon aws
-keep class org.apache.commons.logging.**               { *; }
-keep class com.amazonaws.services.sqs.QueueUrlHandler  { *; }
-keep class com.amazonaws.javax.xml.transform.sax.*     { public *; }
-keep class com.amazonaws.javax.xml.stream.**           { *; }
-keep class com.amazonaws.services.**.model.*Exception* { *; }
-keep public class com.amazonaws.** { *; }
-keep public class com.fasterxml.jackson.core.** { *; }

-dontwarn com.amazonaws.**
-dontwarn javax.xml.stream.events.**
-dontwarn org.codehaus.jackson.**
-dontwarn org.apache.commons.logging.impl.**
-dontwarn org.apache.http.conn.scheme.**

## community jar
-keep class com.tigerbrokers.stock.common.** { *; }
-keep class base.stock.community.bean.** { *; }

## mixpanel
-dontwarn com.mixpanel.**

## tencent sso
-dontwarn com.tencent.**
-keep class com.tencent.** {*;}

## xiaomi push
-keep class com.tigerbrokers.stock.model.push.XMPushMessageReceiver {*;}

# ActiveAndroid
-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer
-keep class com.activeandroidlib.** { *; }
-keep class com.activeandroidlib.**.** { *; }
-keep class * extends com.activeandroidlib.Model
-keep class * extends com.activeandroidlib.serializer.TypeSerializer
-dontwarn org.apache.commons.collections.BeanMap
-dontwarn java.beans.**

# Retrofit
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-keep class retrofit.** { *; }

-dontwarn rx.**
-dontwarn retrofit.**
-dontwarn okio.**

-keepclasseswithmembers class * {
  @retrofit.http.* <methods>;
}

-keepclassmembernames interface * {
  @retrofit.http.* <methods>;
}


# Support V7
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
  public <init>(android.content.Context);
}

# Lombok models
-keep class com.tigerbrokers.open.account.data.model.* { *; }
-keep class com.tigerbrokers.stock.sdk.data.model.* { *; }

# EventBus
-keepclassmembers class ** {
  public void onEvent(**);
  public void onEventMainThread(**);
}

# Stetho
-keep class com.facebook.stetho.** { *; }

# Retrolambda
-dontwarn java.lang.invoke.*
-dontwarn java8.util.**

# Others
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class org.codehaus.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
-keep class package.with.model.classes.** { *; }

-dontwarn javax.**
-dontwarn com.squareup.**
-dontwarn com.sun.**
-dontwarn lombok.**
-dontwarn org.apache.**

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#for 小能
-dontwarn cn.xiaoneng.**
-dontwarn edu.emory.mathcs.backport.java.util.**
-dontwarn net.sf.retrotranslator.**
-dontwarn org.fusesource.**
-keep class cn.xiaoneng.** {*;}
-keep class edu.emory.mathcs.backport.java.util.** {*;}
-keep class net.sf.retrotranslator.** {*;}
-keep class org.fusesource.** {*;}

# Video sdk
# 需要将log4j里的org.apache.log4j.chainsaw.loadXMLAction删除,否则proguard无法进行optimization

-keep class netscape.ldap.** { *; }
-keep class com.koal.** { *; }
-keep class sun.io.** { *; }
-keep class com.bairuitech.** { *; }
-keep class com.thinkive.** { *; }
-keep class com.koal.ra.api.** { *;}
-keep class org.dom4j.**{ *;}
-keep class org.bouncycastle.util.**{ *;}
-keep class koal.common3.util.**{*;}
-keep class koal.security3.**{*;}
-keep class maven.koal.common3.**{*;}
-keep class org.apache.commons.**{*;}

-keep class com.tigerbrokers.stock.zxstock.account.data.** {*;}

-dontwarn com.bairuitech.**
-dontwarn netscape.ldap.**
-dontwarn com.koal.**
-dontwarn sun.io.**
-dontwarn com.thinkive.**
-dontwarn com.koal.ra.api.**
-dontwarn org.dom4j.**
-dontwarn org.bouncycastle.util.**
-dontwarn koal.common3.util.**
-dontwarn koal.security3.**
-dontwarn maven.koal.common3.**
-dontwarn org.apache.commons.**


# InnerBrowser js interface
-keep class com.tigerbrokers.stock.ui.TigerBridge { *; }


# MUPDF
-keep class com.artifex.mupdfdemo.** {*;}


# MPAndroidChart
-dontwarn io.realm.**

# Facebook 登录分享
-keep class com.facebook.** { *; }

# Twitter 登录分享
-dontwarn com.google.appengine.api.urlfetch.**

#乐视SDK 混淆

-keep class org.xutils.** { *; }
-dontwarn org.apache.commons.codec.**
-keep class org.apache.commons.codec.** { *;}
-keep class org.apache.** { *; }
-keepclasseswithmembernames class * { native <methods>;}
-keep class com.lecloud.sdk.http.** { *;}
-keep class com.lecloud.sdk.api.ad.** { *;}
-keep class com.lecloud.sdk.player.** { *;}
-keep class com.lecloud.sdk.api.** { *;}
-keep class com.lecloud.sdk.utils.**{ *;}
-keep class com.lecloud.sdk.videoview.** { *;}
-keep class com.lecloud.sdk.listener.** { *;}
-keep class com.lecloud.sdk.download.**{ *;}
-keep class com.lecloud.sdk.config.** { *;}
-keep class com.lecloud.sdk.surfaceview.** { *;}
-keep class com.lecloud.sdk.constant.** { *;}
-keep public class com.letv.ads.**{ *;}
-keep public class com.letv.plugin.pluginloader.**{ *;}
-dontwarn com.letv.ads.**
-dontwarn com.letv.plugin.pluginloader.**
-keep class cn.mmachina.** { *; }
-keep class com.letv.adlib.** { *; }
-keep class com.letvcloud.cmf.** { *; }
-keep class com.lecloud.uploadservice.** { *; }
-dontwarn org.cmf.**
-keep class android.net.** { *; }
-dontwarn android.net.**
-keep class com.android.internal.http.multipart.** { *; }
-keep class org.apache.commons.** { *; }
-keep class com.lecloud.xutils.** { *; }
-keep class android.app.IServiceConnection { *; }
-keep class * implements android.os.IInterface { *; }
-keep class android.util.Singleton { *; }
-keep class android.os.SystemProperties
-keepclassmembers class android.os.SystemProperties{ public <fields>; public <methods>; }
-keep class android.net.** { *; }
-keep class android.support.v4.app.NotificationCompat** {
 public *; }

# ipaynow 支付
-keep class com.alipay.**{ *; }
-keep class com.ut.*
-keep class cn.gov.pbc.tsm.*{ *; }
-keep class com.UCMobile.PayPlugin.*{ *; }
-keep class com.unionpay.*{ *; }
-dontwarn com.unionpay.**

-keep class com.baidu.**{*;}
-dontwarn com.baidu.**

-keep class com.ipaynow.plugin.**{*;}
-dontwarn com.ipaynow.plugin.**
