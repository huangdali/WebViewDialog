package com.hdl.webviewdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;

import com.jwkj.CommWebView;

/**
 * 弹窗版webview
 * Created by HDL on 2017/8/18.
 */

public class WebViewDialog extends Dialog {
    private Context mContext;
    private CommWebView webView;
    private int screenWidth, screenHeight;
    private int margin_left_right_dp = 30;
    private int margin_top_bottom_dp = 65;
    private OnDialogListener onDialogListener;

    public WebViewDialog(@NonNull Context context) {
        this(context, R.style.Red_Dialog);
    }

    public WebViewDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        initPix();
        init();
        startAnimate();
    }

    private void init() {
//        View view = View.inflate(mContext, R.layout.dialog_webview, null);
//        webView = (CommWebView) view.findViewById(R.id.cwv_view);
        webView = new CommWebView(mContext);
        webView.setTransparent(true);
        webView.addJavascriptInterface(new JSCallNative(), "JsCallNative");
        setContentView(webView);
        setMargin();
    }

    /**
     * 添加js与java互相调用类.
     * <p>
     * SuppressLint("JavascriptInterface") 表示webview的修复漏洞
     *
     * @param mapClazz js方法与java方法映射类
     * @param objName  对象的名字
     */
    @SuppressLint("JavascriptInterface")
    public WebViewDialog addJavascriptInterface(Object mapClazz, String objName) {
        webView.addJavascriptInterface(mapClazz, objName);
        return this;
    }

    /**
     * 设置距离左边和右边的距离(单位：dp，默认30dp)
     *
     * @param margin_left_right_dp
     */
    public void setMargin_left_right_dp(int margin_left_right_dp) {
        this.margin_left_right_dp = margin_left_right_dp;
        setMargin();
    }

    /**
     * 设置边距
     *
     * @param left_right
     * @param top_bottom
     */
    public void setMargin(int left_right, int top_bottom) {
        this.margin_left_right_dp = left_right;
        this.margin_top_bottom_dp = top_bottom;
        setMargin();
    }

    /**
     * 设置距离顶部和底部的距离（单位：dp，默认65dp）
     *
     * @param margin_top_bottom_dp
     */
    public void setMargin_top_bottom_dp(int margin_top_bottom_dp) {
        this.margin_top_bottom_dp = margin_top_bottom_dp;
        setMargin();
    }

    private void setMargin() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = screenWidth - 2 * dip2px(margin_left_right_dp);
        lp.height = screenHeight - 2 * dip2px(margin_top_bottom_dp); // 高度
    }

    private void startAnimate() {
        getWindow().setWindowAnimations(R.style.Red_Dialog);
    }

    /**
     * 加载url
     *
     * @param url
     */
    public void loadUrl(String url) {
        webView.setCurWebUrl(url);
        webView.refresh();
    }

    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void initPix() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    @Override
    public void dismiss() {
        if (webView != null) {
            webView.onDestroy();
        }
        if (onDialogListener != null) {
            onDialogListener.onClose();
        }
        super.dismiss();
    }

    public class JSCallNative {
        @JavascriptInterface
        public void closeDialog() {
            if (mContext != null) {
                /**
                 * 4.4以上的webview，需要在子线程中调用js与java互相调用的代码
                 */
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                });
            }
        }
    }
}
