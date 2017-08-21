# WebViewDialog

- 显示webview的dialog
- 背景透明

## 导入
app.build 导入依赖
```java
    compile 'com.jwkj:WebViewDialog:v1.0.2'
    compile 'com.jwkj:commwebview:v1.0.8'
```

## 配置混淆
```java
#WebViewDialog库
-keep class com.hdl.webviewdialog.** {  *;}
-dontwarn com.hdl.webviewdialog.**

#commwebview库
-keep class com.jwkj.** {  *;}
-dontwarn com.jwkj.**
```

## 使用

```java
        WebViewDialog dialog = new WebViewDialog(this);
        dialog.loadUrl(url);
        dialog.show();
```
## 版本记录

1.0.2 ( [2017.08.21]() )

- 【新增】背景透明功能
