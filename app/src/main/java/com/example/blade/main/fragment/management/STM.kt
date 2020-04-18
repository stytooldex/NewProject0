package com.example.blade.main.fragment.management

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.text.InputType
import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ActivityUtils.startActivity
import com.blankj.utilcode.util.ClickUtils.applyPressedViewScale
import com.blankj.utilcode.util.ColorUtils.getRandomColor
import com.blankj.utilcode.util.FileUtils.notifySystemToScan
import com.blankj.utilcode.util.ShellUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.blade.R
import com.example.blade.activity.device.DeviceActivity
import com.example.blade.preference.Switch_Preference
import com.example.blade.preference.Switch_Preference.killList
import com.example.blade.util.shell.SHellKt
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator
import com.lzf.easyfloat.anim.DefaultAnimator
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import com.lzf.easyfloat.interfaces.OnDisplayHeight
import com.lzf.easyfloat.interfaces.OnInvokeView
import com.lzf.easyfloat.utils.DisplayUtils
import kotlinx.android.synthetic.main.float_app_screenshot.view.*
import kotlinx.android.synthetic.main.preference_recyclerview_x.view.*


object STM {
    @JvmField
    val STM_items = arrayOf(
            "手机参数信息" //0
            ,
            "ROOT截屏" //1
            ,
            "原生WiFi去叹号去叉" //2
            ,
            "电量伪装" //3
            ,
            "强制隐藏导航栏和状态栏" //4
            ,
            "去除正在其他应用上层显示" //5
            ,
            "强制打开微信暗黑模式设置" //6

    )

    fun execute(activity: FragmentActivity, i: String) {
        when (i) {
            STM_items[0] -> {
                startActivity(activity, DeviceActivity::class.java)
            }
            STM_items[1] -> {
                screenshot(activity)
            }
            STM_items[2] -> {

            }
            STM_items[3] -> {
                battery(activity)
            }
            STM_items[4] -> {
                global(activity)
            }
            STM_items[5] -> {
                val intent = Intent("android.settings.APP_NOTIFICATION_SETTINGS")
                intent.putExtra("android.provider.extra.APP_PACKAGE", "android")
                try {
                    startActivity(activity, intent)
                } catch (e: Exception) {
                    ToastUtils.showLong("e: Exception")
                }

            }
            STM_items[6] -> {
                SHellKt.run(null, "am start -n com.tencent.mm/com.tencent.mm.plugin.setting.ui.setting.SettingRedesign")
            }
            else -> throw IllegalStateException("Unexpected value: $i")
        }
    }


    private fun global(activity: FragmentActivity) {
        val dialog = MaterialDialog(activity, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            customView(R.layout.preference_recyclerview_x)
            lifecycleOwner(activity)
            negativeButton()
            positiveButton(text = "恢复默认") {
                SHellKt.run(null, "settings put global policy_control null")
            }
        }//settings put global policy_control immersive.full=apps,-com.google.android.
        dialog.onShow {
            val i = it.getCustomView().recycler_view
            Switch_Preference(activity, i, killList(), Switch_Preference.OnIf { i1, s ->
                when (s) {
                    "1" -> {
                        if (i1) {
                            SHellKt.run(null, "settings put global policy_control immersive.full=*")
                        } else {
                            SHellKt.run(null, "settings put global policy_control immersive.full=null")
                        }
                    }
                    "2" -> {
                        if (i1) {
                            SHellKt.run(null, "settings put global policy_control immersive.status=*")
                        } else {
                            SHellKt.run(null, "settings put global policy_control immersive.status=null")
                        }
                    }
                    "3" -> {
                        if (i1) {
                            SHellKt.run(null, "settings put global policy_control immersive.navigation=*")
                        } else {
                            SHellKt.run(null, "settings put global policy_control immersive.navigation=null")
                        }
                    }
                }
            })

        }
    }

    private fun battery(activity: FragmentActivity) {
        val dialog = MaterialDialog(activity).show {
            input(inputType = InputType.TYPE_CLASS_PHONE, maxLength = 3, hint = "0-999") { _, text ->
                SHellKt.run(null, "dumpsys battery set level $text")
            }
            negativeButton()
            lifecycleOwner(activity)
            negativeButton(text = "恢复默认") {
                SHellKt.run(null, "dumpsys battery reset")
            }
        }
        dialog.onShow {
            //it.getCustomView().imageView4.setImageDrawable(getAppIcon(packageName))

        }
    }

    private fun wifiA(activity: FragmentActivity) {
        MaterialDialog(activity).show {
            title(text = "ROOT")
            message(text = "支持android:\n5.0 - 6.x\n7.0 - 7.1\n7.1.2")
            lifecycleOwner(activity)
            positiveButton(text = "执行") {
                when (Build.VERSION.SDK_INT) {
                    21, 22, 23 -> {
                        SHellKt.run(null, "settings put global captive_portal_server captive.v2ex.co")
                    }
                    24, 25, 26, 27, 28 -> {
                        SHellKt.run(null, "settings delete global captive_portal_use_https")

                    }

                }
            }
            negativeButton(text = "恢复默认") {
                when (Build.VERSION.SDK_INT) {
                    21, 22, 23 -> {
                        SHellKt.run(null, "settings delete global captive_portal_server")

                    }
                    24 -> {
                        SHellKt.run(null, "settings delete global captive_portal_use_https")

                    }
                    25 -> {
                        SHellKt.run(null, "settings delete global captive_portal_https_url")
                        SHellKt.run(null, "settings delete global captive_portal_http_url")
                    }
                    26, 27, 28 -> {

                    }

                }
            }
        }
    }

    @SuppressLint("SdCardPath")
    private fun screenshot(activity: FragmentActivity) {
        val floatTag = "Float_screenshot"
        val i = "/sdcard/Pictures/screenshot_${getRandomColor()}.png"
        val ii = "/system/bin/screencap -p $i"
        EasyFloat.with(activity)
                .setLayout(R.layout.float_app_screenshot, OnInvokeView { view ->
                    view.imageView5_screenshot.setOnClickListener { v ->
                        applyPressedViewScale(v)
                        SHellKt.run(object : SHellKt.OnSu {
                            override fun onStdout(string: List<String?>?) {
                                ToastUtils.showLong("1$string")
                            }

                            override fun onStderr(string: List<String?>?) {
                                notifySystemToScan(i)
                                ToastUtils.showLong("已保存的到$i")
                            }
                        }, ii)

                    }
                    view.imageView5_screenshot.setOnLongClickListener {
                        EasyFloat.dismissAppFloat(floatTag)
                        EasyFloat.dismiss(activity, floatTag)
                        false
                    }
                })
                .setShowPattern(ShowPattern.ALL_TIME)
                .setSidePattern(SidePattern.DEFAULT)
                .setTag(floatTag)
                .setDragEnable(true)
                .hasEditText(false)
                .setMatchParent(widthMatch = false, heightMatch = false)
                .setAnimator(DefaultAnimator())
                .setGravity(Gravity.CENTER)
                .setAppFloatAnimator(AppFloatDefaultAnimator())
                .setDisplayHeight(OnDisplayHeight { context -> DisplayUtils.rejectedNavHeight(context) })
                .registerCallback {
                    show {
                        ShellUtils.execCmd("su -c", true)
                    }
                }
                .show()
    }
}