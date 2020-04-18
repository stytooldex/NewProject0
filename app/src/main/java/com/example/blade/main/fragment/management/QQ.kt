package com.example.blade.main.fragment.management

import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ActivityUtils.startActivity
import com.blankj.utilcode.util.ResourceUtils.readAssets2String
import com.example.blade.activity.replace_pictures.PicturesActivity
import com.example.blade.util.qq.Android_qq_Share
import com.example.blade.util.qq.Android_qq_Share.TEXT

object QQ {
    @JvmField
    val QQ_items = arrayOf(
            "QQ信息连发器" //0
            ,
            "QQ处理工具" //1
            ,
            "QQ全局图片替换" //2
            ,
            "QQ卡死代码" //3

    )

    fun execute(activity: FragmentActivity, i: String) {
        when (i) {
            QQ_items[0] -> {
                burst(activity)
            }
            QQ_items[1] -> {

            }
            QQ_items[2] -> {
                startActivity(activity, PicturesActivity::class.java)
            }
            QQ_items[3] -> {
                qqShare(activity)
            }
            else -> throw IllegalStateException("Unexpected value: $i")
        }
    }

    private fun burst(activity: FragmentActivity) {

    }

    private fun qqShare(activity: FragmentActivity) {
        MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            message(text = "单身狗提醒,请勿发给对像\nPs：卡死了没法跟那个人聊天了？『点击 咳咳』\n再一次点进聊天不要翻上去，也可用电脑QQ查以前记录")
            input(hint = "Your Hint Text") { _, text ->
                val isA = readAssets2String("qq_id/a.txt") + "\n\n${text}"
                Android_qq_Share(activity).shareQQFriend(null, isA, TEXT, null)
            }
            negativeButton(text = "咳咳") {
                val msgText = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                Android_qq_Share(activity).shareQQFriend(null, msgText, TEXT, null)
            }
            positiveButton()
            lifecycleOwner(activity)
        }
    }
}