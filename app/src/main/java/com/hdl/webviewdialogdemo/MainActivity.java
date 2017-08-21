package com.hdl.webviewdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hdl.webviewdialog.WebViewDialog;

public class MainActivity extends AppCompatActivity {
    private String url = "http://39.108.193.125:8080/vas/pages/prompt/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDialog(View view) {
        WebViewDialog dialog = new WebViewDialog(this);
        dialog.loadUrl(url);
        dialog.show();
    }
}
