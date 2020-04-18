package com.example.blade.main.fragment.management

import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.ActivityUtils.startActivity
import com.example.blade.ReaderUtil
import com.example.blade.activity.tailoring.bin_Activity
import com.example.blade.activity.wallpaper.Main10Activity

object Pic {
    @JvmField
    val Pic_items = arrayOf(
            "哔哩哔哩封面提取" //0
            ,
            "INStAGram风格生成器" //1
            ,
            "获取当前壁纸" //2
            ,
            "图片裁剪形状" //3

    )

    fun execute(activity: FragmentActivity, i: String) {
        when (i) {
            Pic_items[0] -> {
                ReaderUtil.isis(activity, "http://b.qiuyeye.cn/index.html")
            }
            Pic_items[1] -> {
                ReaderUtil.isis(activity, "https://igfonts.io/")
            }
            Pic_items[2] -> {
                startActivity(activity, Main10Activity::class.java)
            }
            Pic_items[3] -> {
                startActivity(activity, bin_Activity::class.java)
            }
            else -> throw IllegalStateException("Unexpected value: $i")
        }
    }
}