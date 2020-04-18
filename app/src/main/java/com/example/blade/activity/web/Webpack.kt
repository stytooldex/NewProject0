package com.example.blade.activity.web

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.util.PatternsCompat
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.blankj.utilcode.util.ObjectUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.blade.R
import com.example.blade.util.clipboard.ClipboardUtils
import com.ycbjie.webviewlib.X5WebView
import kotlinx.android.synthetic.main.my_custom_view_web.view.*

class Webpack(x5WebView: X5WebView, activity: FragmentActivity) {
    init {
        val dialog = MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.my_custom_view_web)

            //positiveButton(text = "打开/搜索")
        }
        dialog.onShow {

            val i = it.getCustomView().arrow_more
            val ii = it.getCustomView()
            it.getCustomView().arrow_back_black.setOnClickListener {
                if (x5WebView.canGoBack()) {
                    x5WebView.goBack()
                }
            }
            it.getCustomView().arrow_forward_black.setOnClickListener {
                if (x5WebView.canGoForward()) {
                    x5WebView.goForward();
                }
            }
            it.getCustomView().arrow_d29.setOnClickListener {
                x5WebView.loadUrl("http://m.imomoe.io/")
            }
            i.setOnClickListener { showMenu(i, x5WebView, activity) }

            it.getCustomView().imageView5.setOnClickListener {
                val s = ii.v_sid.text.toString()
                ToastUtils.showLong(s)
                if (!ObjectUtils.isEmpty(s)) {
                    if (PatternsCompat.WEB_URL.matcher(s).matches()) {
                        x5WebView.loadUrl(s);
                    } else {
                        x5WebView.loadUrl("https://www.baidu.com/s?wd=$s");
                    }
                }
            }
        }
    }

    private fun showMenu(v: View, x5WebView: X5WebView, activity: FragmentActivity) {
        val popup = PopupMenu(activity, v)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actionbar_share -> {
                    val shareText: String = x5WebView.title + x5WebView.url
                    WebTools.share(activity, shareText)
                }
                R.id.actionbar_cope -> {
                    ClipboardUtils.copyText(x5WebView.url)
                    Toast.makeText(activity, "复制成功", Toast.LENGTH_LONG).show()
                }
                R.id.actionbar_open -> WebTools.openLink(activity, x5WebView.url)
                R.id.actionbar_webview_refresh -> x5WebView.reLoadView()
                R.id.arrow_back_black -> if (x5WebView.canGoBack()) {
                    x5WebView.goBack()
                }
                R.id.arrow_forward_black -> if (x5WebView.canGoForward()) {
                    x5WebView.goForward()
                }
                R.id.arrow_d29 -> x5WebView.loadUrl("http://m.imomoe.io/")
                else -> {
                }
            }
            false
        }
        popup.inflate(R.menu.menu_webview)
        popup.show()
    }

}