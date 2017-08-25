package com.hdl.webviewdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hdl.webviewdialog.WebViewDialog;
import com.jwkj.WebViewCallback;

public class MainActivity extends AppCompatActivity {
    private String url = "http://39.108.193.125:8080/vas/pages/dialogs/";
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUrl = (EditText) findViewById(R.id.et_url);
    }

    public void onDialog(View view) {
        url = etUrl.getText().toString().trim();
        final WebViewDialog dialog = new WebViewDialog(this);
        dialog.loadUrl(url);
        dialog.setPlayAnimate(false);
        dialog.getWebView().startCallback(new WebViewCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onProgress(int curProgress) {
                if (curProgress == 100) {
                    dialog.show();
                }
            }

            @Override
            public void onError(int errorCode, String description, String failingUrl) {
            }
        });
    }
}
