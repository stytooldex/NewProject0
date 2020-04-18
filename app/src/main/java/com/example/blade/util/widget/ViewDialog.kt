package com.example.blade.util.widget

import android.graphics.Bitmap
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.blankj.utilcode.util.*
import com.blankj.utilcode.util.AppUtils.getAppIcon
import com.blankj.utilcode.util.AppUtils.getAppName

object ViewDialog {
    private val Items = listOf("打开软件", "卸载软件", "应用信息", "提取安装包", "提取软件图标")
    fun dump(context: FragmentActivity, packageName: String, path: String) {
        val dialog = MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            icon(drawable = getAppIcon(packageName))
            title(text = getAppName(packageName))
            message(text = packageName)
            listItemsSingleChoice(items = Items) { _, index, _ ->
                val name = PathUtils.getExternalStoragePath() + "/" + AppUtils.getAppName(packageName) + "_" + AppUtils.getAppVersionName(packageName)
                when (index) {
                    0 -> AppUtils.launchApp(packageName)
                    1 -> AppUtils.uninstallApp(packageName)
                    2 -> AppUtils.launchAppDetailsSettings(packageName)
                    3 -> if (FileUtils.copy(path, "$name.apk")) notify("$name.apk") else ToastUtils.showLong("失败了")
                    4 -> if (ImageUtils.save(ImageUtils.drawable2Bitmap(getAppIcon(packageName)), "$name.png", Bitmap.CompressFormat.PNG)) notify("$name.png") else ToastUtils.showLong("失败了")
                    else -> {
                    }
                }
            }
            lifecycleOwner(context)
        }
    }

    private fun notify(s: String) {
        ToastUtils.showLong(s)
        FileUtils.notifySystemToScan(s)
    }
}