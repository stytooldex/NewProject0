# 指定外部模糊字典
-obfuscationdictionary proguard-socialism.txt
# 指定class模糊字典
-classobfuscationdictionary proguard-socialism.txt
# 指定package模糊字典
-packageobfuscationdictionary proguard-socialism.txt

-repackageclasses
-overloadaggressively
-allowaccessmodification
-mergeinterfacesaggressively

-keepclasseswithmembers class **.R$* {
    public static final int define_*;
}

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-assumenosideeffects class android.util.Log {
public static *** isLoggable(java.lang.String, int);
public static *** v(...);
public static *** i(...);
public static *** w(...);
public static *** d(...);
public static *** e(...);
}

-keep class android.**{*;}
