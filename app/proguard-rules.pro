# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/marcus/Library/Android/sdk/tools/proguard/proguard-android.txt
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


# Rules for Retrofit
-dontwarn retrofit.**
        -keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Rules for Picasso
-dontwarn com.squareup.okhttp.**

# Butter Knife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
        -keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Realm
-keepnames public class * extends io.realm.RealmObject
-keep class io.realm.** { *; }
-dontwarn javax.**
        -dontwarn io.realm.**

