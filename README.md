# WebViewDialog

- 显示webview的dialog
- 背景透明

## 效果图

![](https://github.com/huangdali/WebViewDialog/blob/master/screenshot.png)


## 导入
app.build 导入依赖
```java
    compile 'com.jwkj:WebViewDialog:v1.0.3'
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

## h5关闭对话框

需要h5页面编写js代码关闭对话框：
- 对象名：JsCallNative
- 方法名：closeDialog

**eg:**
在需要关闭对话框按钮加入以下代码
```java
   onClick="window.JsCallNative.closeDialog()"
```



## 版本记录

v1.0.3 ( [2017.08.21]() )
- 【优化】删除一些无用资源和日志

v1.0.2 ( [2017.08.21]() )
- 【新增】背景透明功能
