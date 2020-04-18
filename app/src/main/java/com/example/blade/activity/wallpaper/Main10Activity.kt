package com.example.blade.activity.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.*
import com.bumptech.glide.Glide
import com.example.blade.R
import com.example.blade.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main10.*

class Main10Activity : BaseActivity() {
    private val sd = SDCardUtils.getSDCardPathByEnvironment() + "/A_Nico/"

    override fun initData() {}

    override fun initView() {
        val wallpaperManager2 = WallpaperManager.getInstance(this)
        if (wallpaperManager2.peekDrawable() == null) {
            finish()
            ToastUtils.showLong("\u0028\u003d\uff9f\u0414\uff9f\u003d\u0029\u0020\u5f53\u524d\u53ef\u80fd\u662f\u52a8\u6001\u58c1\u7eb8\uff0c\u6682\u4e0d\u652f\u6301")
        }
        Glide.with(this).load(wallpaperManager2.peekDrawable()).into((findViewById<View>(R.id.imageView) as ImageView))

        iFloatingActionButton.setOnClickListener {
            if (ImageUtils.save(ImageUtils.drawable2Bitmap(wallpaperManager2.peekDrawable()), sd + ColorUtils.getRandomColor() + ".png", Bitmap.CompressFormat.PNG)) {
                ToastUtils.showLong("壁纸已保存至:$sd")
                FileUtils.notifySystemToScan(sd)
            } else {
                ToastUtils.showLong("壁纸保存失败，请联系开发者")
            }
        }
    }

    override fun setLayoutRes(): Int {
        return R.layout.activity_main10
    }
}