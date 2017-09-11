# WebViewDialog

- 显示webview的dialog
- 背景透明

## 效果图

![](https://github.com/huangdali/WebViewDialog/blob/master/screenshot.png)


## 导入
app.build 导入依赖
```java
    compile 'com.jwkj:WebViewDialog:v1.0.6'
    compile 'com.jwkj:commwebview:v1.1.4'
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

常规使用：

```java
        WebViewDialog dialog = new WebViewDialog(this);
        dialog.loadUrl(url);
        dialog.show();
```

如果弹窗是由系统自动完成，而不是用户点击，建议在页面加载完成的时候再弹出：
```java
        url = etUrl.getText().toString().trim();
        final WebViewDialog dialog = new WebViewDialog(this);
        dialog.loadUrl(url);
        dialog.getWebView().startCallback(new WebViewCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onProgress(int curProgress) {
                if (curProgress == 100) {//加载完成的时候再显示
                    dialog.show();
                }
            }

            @Override
            public void onError(int errorCode, String description, String failingUrl) {
            }
        });
```


## h5关闭对话框

需要h5页面编写js代码关闭对话框：
- 对象名：JsCallNative
- 方法名：closeDialog

**eg:**
在需要关闭对话框的地方加入以下代码
```java
   onClick="window.JsCallNative.closeDialog()"
```



## 版本记录

v1.0.6 ( [2017.08.25]() )
- 【新增】设置是否显示dialog的出现动画

v1.0.5 ( [2017.08.21]() )
- 【新增】getWebView方法，可自己扩展配置webview

v1.0.4 ( [2017.08.21]() )
- 【新增】addJavascriptInterface方法，可自己扩展与Js的交互

v1.0.3 ( [2017.08.21]() )
- 【优化】删除一些无用资源和日志

v1.0.2 ( [2017.08.21]() )
- 【新增】背景透明功能
