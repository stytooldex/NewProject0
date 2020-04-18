package com.example.blade.activity.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.blade.R;
import com.example.blade.base.BaseActivity;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.ycbjie.webviewlib.BridgeWebView;
import com.ycbjie.webviewlib.InterWebListener;
import com.ycbjie.webviewlib.X5WebChromeClient;
import com.ycbjie.webviewlib.X5WebUtils;
import com.ycbjie.webviewlib.X5WebView;
import com.ycbjie.webviewlib.X5WebViewClient;

import cc.shinichi.library.ImagePreview;

import static com.example.blade.util.clipboard.ClipboardUtils.copyText;

@SuppressLint("SetJavaScriptEnabled")
public class ActiveWeb extends BaseActivity {

    private X5WebView mWebView;


    @Override
    protected void initData() {
        //mWebView.addJavascriptInterface(new ImageJavascriptInterface(this), "imagine");
        mWebView.setOnScrollChangeListener(new X5WebView.OnScrollChangeListener() {
            @Override
            public void onPageEnd(int l, int t, int ode, int odd) {

            }

            @Override
            public void onPageTop(int l, int t, int ode, int odd) {
                if (t == 0) {
                    webAA.ScrollChangeListener(getSupportActionBar(), true);
                } else {
                    webAA.ScrollChangeListener(getSupportActionBar(), false);
                }
            }

            @Override
            public void onScrollChanged(int l, int t, int ode, int odd) {

            }
        });
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return handleLongImage();
            }
        });
        mWebView.getX5WebChromeClient().setWebListener(interWebListener);

        MyX5WebViewClient webViewClient = new MyX5WebViewClient(mWebView, this);
        mWebView.setWebViewClient(webViewClient);
        //MyX5WebChromeClient webChromeClient = new MyX5WebChromeClient(this);
        mWebView.setWebChromeClient(webChromeClient);

        ToastUtils.showLong("本应用不执行Browser隐私政策由第三方处理\n查看相关政策进『信息连发器』");
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            JsAlert.showMenu(result, webView, ActiveWeb.this, message);
            return true;
        }

        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
            JsAlert.menuInput(result, view, ActiveWeb.this, message);
            return true;

        }
    };

    @Override
    protected void initView() {
        mWebView = findViewById(R.id.web_view);
        mWebView.setOpenLayerType(true);
        mWebView.setSavePassword(true);

        getDataFromBrowser(getIntent());
        Intent nt = getIntent();
        String m = nt.getStringExtra("AEO");

        if (m != null) {
            if (m.equals("")) {
                mWebView.loadUrl(SPUtils.getInstance().getString("AEO"));
            } else {
                mWebView.loadUrl(m);
            }
        }

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_activit_web;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.resumeTimers();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.destroy();
            mWebView = null;
        }

    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    ToastUtils.showLong("再按一次退出");
                    mExitTime = System.currentTimeMillis();
                } else {
                    handleFinish();
                }


            }
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getDataFromBrowser(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == X5WebChromeClient.FILE_CHOOSER_RESULT_CODE) {
            mWebView.getX5WebChromeClient().uploadMessage(intent, resultCode);
        } else if (requestCode == X5WebChromeClient.FILE_CHOOSER_RESULT_CODE_5) {
            mWebView.getX5WebChromeClient().uploadMessageForAndroid5(intent, resultCode);
        }
    }


    public void handleFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    private boolean handleLongImage() {
        final WebView.HitTestResult hitTestResult = mWebView.getHitTestResult();
        if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            new AlertDialog.Builder(this)
                    .setItems(new String[]{"查看大图|保存图片", "复制图片链接"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String picUrl = hitTestResult.getExtra();
                            Log.e("picUrl", picUrl);
                            switch (which) {
                                case 0:
                                    ImagePreview.getInstance()
                                            .setContext(ActiveWeb.this)
                                            .setImage(picUrl)
                                            .start();
                                    break;
                                case 1:
                                    ToastUtils.showLong("复制成功");
                                    copyText(picUrl);
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .show();
            return true;
        }
        return false;
    }

    private void getDataFromBrowser(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            try {
                String scheme = data.getScheme();
                String host = data.getHost();
                String path = data.getPath();
                String text = "Scheme: " + scheme + "\n" + "host: " + host + "\n" + "path: " + path;
                //Log.e("data", text);
                String url = scheme + "://" + host + path;
                mWebView.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private InterWebListener interWebListener = new InterWebListener() {


        @Override
        public void hindProgressBar() {

        }

        @Override
        public void showErrorView(@X5WebUtils.ErrorType int type) {

        }

        @Override
        public void startProgress(int newProgress) {

        }

        @Override
        public void showTitle(String title) {
            setTitle(title);
        }
    };

    public void devil(View view) {
        new Webpack(mWebView, this);
    }


    private class MyX5WebViewClient extends X5WebViewClient {
        MyX5WebViewClient(BridgeWebView webView, Context context) {
            super(webView, context);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            WebTools.handleThirdApp(ActiveWeb.this, url);
        }

        @Override
        public void onLoadResource(WebView webView, String s) {
            super.onLoadResource(webView, s);

        }

    }


}
