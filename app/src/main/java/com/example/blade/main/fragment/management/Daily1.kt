package com.example.blade.main.fragment.management

import android.annotation.SuppressLint
import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.ActivityUtils.startActivity
import com.example.blade.R
import com.example.blade.ReaderUtil
import com.example.blade.activity.dump.ApkActivity
import com.example.blade.activity.led.LEDActivity
import com.example.blade.activity.small_font.MeiActivity
import com.example.blade.util.battery.BatteryUtils
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator
import com.lzf.easyfloat.anim.DefaultAnimator
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.interfaces.OnDisplayHeight
import com.lzf.easyfloat.interfaces.OnInvokeView
import com.lzf.easyfloat.utils.DisplayUtils
import kotlinx.android.synthetic.main.float_app_power.view.*
import kotlinx.android.synthetic.main.float_app_time.view.*

object Daily1 {
    @JvmField
    val items = arrayOf(
            "提取APK" //0
            ,
            "MiKuTools" //1
            ,
            "实时汇率换算" //2
            ,
            "小字体数字+蓝色英文字" //3
            ,
            "显卡天梯图" //4
            ,
            "悬浮时间" //5
            ,
            "悬浮电量" //6
            ,
            "悬浮文本" //7
            ,
            "LED滚动弹木" //8
    )

    fun execute(activity: FragmentActivity, i: String) {
        when (i) {
            items[0] -> {
                startActivity(ApkActivity::class.java)
                //startActivity(activity,ApkActivity::class.java)
            }
            items[1] -> {
                ReaderUtil.isis(activity, "https://tools.miku.ac/")
            }
            items[2] -> {
                ReaderUtil.isis(activity, "https://tools.miku.ac/exchange_rate")
            }
            items[3] -> {
                startActivity(activity, MeiActivity::class.java)
            }
            items[4] -> {
                ReaderUtil.isis(activity, "https://tools.miku.ac/gpu_rank")
            }
            items[5] -> {
                time(activity)
            }
            items[6] -> {
                power(activity)
            }
            items[7] -> {

            }
            items[8] -> {
                startActivity(activity, LEDActivity::class.java)
            }
            else -> throw IllegalStateException("Unexpected value: $i")
        }
    }

    private fun time(activity: FragmentActivity) {
        val floatTag = "Float_time"
        EasyFloat.with(activity)
                .setLayout(R.layout.float_app_time, OnInvokeView { view ->

                    view.tv_time.setOnLongClickListener {
                        EasyFloat.dismissAppFloat(floatTag)
                        EasyFloat.dismiss(activity, floatTag)
                        false
                    }

                })

                .setShowPattern(ShowPattern.ALL_TIME)
                .setTag(floatTag)
                .setDragEnable(true)
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

    @SuppressLint("SetTextI18n")
    private fun power(activity: FragmentActivity) {
        val floatTag = "Float_power"
        EasyFloat.with(activity)
                .setLayout(R.layout.float_app_power, OnInvokeView { view ->
                    BatteryUtils.registerBatteryStatusChangedListener { status -> view.tv_power.text = status.toString() }
                    view.tv_power.setOnLongClickListener {
                        EasyFloat.dismissAppFloat(floatTag)
                        EasyFloat.dismiss(activity, floatTag)
                        false
                    }

                })
                .setShowPattern(ShowPattern.ALL_TIME)
                .setTag(floatTag)
                .setDragEnable(true)
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
}