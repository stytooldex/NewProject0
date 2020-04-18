package com.example.blade.main.fragment.management

import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.checkbox.checkBoxPrompt
import com.afollestad.materialdialogs.checkbox.isCheckPromptChecked
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItems
import com.bitvale.switcher.SwitcherX
import com.blankj.utilcode.util.FileUtils.delete
import com.blankj.utilcode.util.FileUtils.isFileExists
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.SDCardUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.blade.R
import com.example.blade.preference.Kill
import com.example.blade.util.Code
import com.example.blade.util.clipboard.ClipboardUtils
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator
import com.lzf.easyfloat.anim.DefaultAnimator
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.interfaces.OnDisplayHeight
import com.lzf.easyfloat.interfaces.OnInvokeView
import com.lzf.easyfloat.utils.DisplayUtils
import kotlinx.android.synthetic.main.activity_main_luxin.view.*
import kotlinx.android.synthetic.main.activity_main_wz_mark.view.*
import kotlinx.android.synthetic.main.float_app_iii.view.*
import kotlinx.android.synthetic.main.preference_widget_switch_compat_x.view.*
import java.util.*

object MGame {
    @JvmField
    val MGame_items = arrayOf(
            "王者荣耀重名" //0
            ,
            "和平精英极限画质工具" //1
            ,
            "王者荣耀国标" //2
            ,
            "中心标" //3

    )

    fun execute(activity: FragmentActivity, i: String) {
        when (i) {
            MGame_items[0] -> {
                kinna(activity)
            }
            MGame_items[1] -> {
                quality(activity)
            }
            MGame_items[2] -> {
                wzMark(activity)
            }
            MGame_items[3] -> {
                mark(activity)
            }
            else -> throw IllegalStateException("Unexpected value: $i")
        }
    }

    private fun wzMark(activity: FragmentActivity) {
        ToastUtils.showLong("感觉这功能没软用,已懒的写 可以用a萌")
        /*val dialog = MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.activity_main_wz_mark)
            lifecycleOwner(activity)
        }
        dialog.onShow {
            val i = it.getCustomView()
            i.switchWidget_wz.setOnCheckedChangeListener { i: Boolean ->

            }
        }*/
    }

    private fun quality(activity: FragmentActivity) {
        val id = SDCardUtils.getSDCardPathByEnvironment() + "/Android/data/com.tencent.tmgp.pubgmhd/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/Config/Android/EnjoyCJZC.ini"
        val dialog = MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.activity_main_luxin)
            lifecycleOwner(activity)
        }//settings put global policy_control immersive.full=apps,-com.google.android.
        dialog.onShow {
            it.getCustomView().button1.setOnClickListener {
                if (isFileExists(id)) {
                    if (delete(id)) {
                        ToastUtils.showLong("帮你关闭了\n请重启游戏")
                        dialog.dismiss()
                    } else {
                        Code.bug()
                    }
                } else {
                    if (ResourceUtils.copyFileFromAssets("EnjoyCJZC.ini", id)) {
                        ToastUtils.showLong("帮你开启了\n请重启游戏")
                        dialog.dismiss()
                    } else {
                        Code.bug()
                    }
                }
            }
        }
    }

    private fun mark(activity: FragmentActivity) {
        val floatTag = "Float_III"
        EasyFloat.with(activity)
                .setLayout(R.layout.float_app_iii, OnInvokeView { view ->

                    view.VAL_III.setOnLongClickListener {
                        EasyFloat.dismissAppFloat(floatTag)
                        EasyFloat.dismiss(activity, floatTag)
                        false
                    }

                })
                .setShowPattern(ShowPattern.ALL_TIME)
                .setTag(floatTag)
                .setDragEnable(false)
                .setMatchParent(widthMatch = false, heightMatch = false)
                .setAnimator(DefaultAnimator())
                .setGravity(Gravity.CENTER)
                .setAppFloatAnimator(AppFloatDefaultAnimator())
                .setDisplayHeight(OnDisplayHeight { context -> DisplayUtils.rejectedNavHeight(context) })
                .registerCallback {
                    show {

                    }
                }
                .show()
    }

    private fun kinna(activity: FragmentActivity) {

        MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            input(hint = "王者荣耀ID", maxLength = 5) { dialog, sequence ->

                val isChecked = dialog.isCheckPromptChecked()
                when (SPUtils.getInstance().getInt("dialog")) {
                    0 -> {
                        val i = "King_of_Glory_has_the_same_name/as1.txt"
                        ClipboardUtils.copyText(if (isChecked) sequence.toString() + ResourceUtils.readAssets2String(i) else ResourceUtils.readAssets2String(i) + sequence.toString())
                    }
                    1 -> {
                        val ii = "King_of_Glory_has_the_same_name/as2.txt"
                        ClipboardUtils.copyText(if (isChecked) sequence.toString() + ResourceUtils.readAssets2String(ii) else ResourceUtils.readAssets2String(ii) + sequence.toString())
                    }
                    2 -> {
                        val iii = "King_of_Glory_has_the_same_name/as3.txt"
                        ClipboardUtils.copyText(if (isChecked) sequence.toString() + ResourceUtils.readAssets2String(iii) else ResourceUtils.readAssets2String(iii) + sequence.toString())
                    }
                    else -> {
                        ToastUtils.showLong("Exception")
                    }
                }
            }
            checkBoxPrompt(text = "空白字符正置到前面:可选") {}
            listItems(res = R.array.socialNetworks) { _, index, _ ->
                SPUtils.getInstance().put("dialog", index)
            }
            negativeButton()
            cancelable(false)
            cancelOnTouchOutside(false)
            lifecycleOwner(activity)

        }
    }
}