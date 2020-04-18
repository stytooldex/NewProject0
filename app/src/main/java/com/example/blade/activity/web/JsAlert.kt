package com.example.blade.activity.web

import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.tencent.smtt.export.external.interfaces.JsPromptResult
import com.tencent.smtt.export.external.interfaces.JsResult
import com.tencent.smtt.sdk.WebView

//https://www.w3school.com.cn/tiy/t.asp?f=hdom_alert
class JsAlert {
    companion object {
        @JvmStatic
        fun showMenu(jsResult: JsResult, webView: WebView, activeWeb: ActiveWeb, key: String) {
            val materialDialog = MaterialDialog(webView.context, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                message(text = key)
                positiveButton {
                    jsResult.confirm();
                }
                negativeButton {
                    jsResult.cancel();
                }
                lifecycleOwner(activeWeb)
            }
            materialDialog.onDismiss {
                jsResult.cancel();
            }
        }

        @JvmStatic
        fun menuInput(result: JsPromptResult, any: WebView, activeWeb: ActiveWeb, message: String) {
            val materialDialog = MaterialDialog(any.context, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                input { _, text ->
                    result.confirm(text.toString())
                }
                negativeButton {
                    result.cancel();
                }
                lifecycleOwner(activeWeb)
            }
            materialDialog.onDismiss {
                result.cancel();
            }
        }
    }

}