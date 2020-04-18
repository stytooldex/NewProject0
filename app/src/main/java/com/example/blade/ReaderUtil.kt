package com.example.blade

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ServiceUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.blade.activity.tools.tools_Activity
import com.example.blade.activity.web.ActiveWeb
import com.example.blade.service.AutoLike
import com.example.blade.service.Information_Repeater
import com.example.blade.util.accessibility.BaseAccessibilityService
import com.github.florent37.viewanimator.ViewAnimator

/**
 * @author Nico
 */
object ReaderUtil {

    @JvmStatic
    fun isis(mActivity: Context, name: String?) {
        if (SPUtils.getInstance(spName).getBoolean("flutter.KileX5", false)) {
            val uri = Uri.parse(name)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            try {
                mActivity.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            val `as` = Intent(mActivity, ActiveWeb::class.java)
            `as`.putExtra("AEO", name)
            mActivity.startActivity(`as`)
        }
    }

    private fun isis2(mActivity: Context, name: String) {
        val `as` = Intent(mActivity, tools_Activity::class.java)
        `as`.putExtra("AEO", name)
        mActivity.startActivity(`as`)
    }

    fun replaceFragment(fragment: FragmentManager, id: Fragment?) {
        fragment.beginTransaction()
                .add(id!!, ColorUtils.getRandomColor().toString() + "")
                .commit()
    }

    @JvmStatic
    fun replaceFragment(fragment: FragmentManager, @IdRes containerViewId: Int, header: Fragment?) {
        fragment.beginTransaction()
                .replace(containerViewId, header!!)
                .commit()
    }

    private fun aars(i: Int) {
        when (i) {
            0 -> if (ServiceUtils.isServiceRunning(AutoLike::class.java)) {
                ToastUtils.showLong("辅助已启用")
            } else {
                ToastUtils.showLong("找到（QQ自动点赞）启动")
                BaseAccessibilityService.getInstance().goAccess()
            }
            1 -> if (ServiceUtils.isServiceRunning(Information_Repeater::class.java)) {
                ToastUtils.showLong("辅助已启用")
            } else {
                ToastUtils.showLong("找到（QQ信息连发器）启动")
                BaseAccessibilityService.getInstance().goAccess()
            }
            else -> {
            }
        }
    }

    // Q_processing_tools, "全部网址功能", "过渡动画修改", "电池伪装" -> isis2(mActivity, tvq)'
    @JvmStatic
    fun execute(tvq: String?, activity: FragmentActivity) {
        when (tvq) {

            "启动辅助AutoLike" -> aars(0)

            "启动辅助Message" -> aars(1)

            //"YY1" -> isis(mActivity, "https://code.aliyun.com/open-Coed/Ni-Coed/blob/master/videogame_asset.md")

            //"YY2" -> isis(mActivity, "https://code.aliyun.com/open-Coed/Ni-Coed/blob/master/videogame_hospital.md")

            //"YY3" -> isis(mActivity, "https://code.aliyun.com/open-Coed/Ni-Coed/blob/master/videogame_map.md")

            /*  "启动辅助IconN" -> if (EasyFloat.appFloatIsShow("Float1")) {
                  EasyFloat.dismissAppFloat("Float1")
              } else {
                  EasyFloat.with(mActivity)
                          .setLayout(R.layout.float_test, OnInvokeView { view ->
                              val imageView = view.findViewById<ImageView>(R.id.imageView2)
                              when (SPUtils.getInstance(spName).getString("flutter.IconN", "关闭")) {
                                  "旋转" -> rotate(imageView, 0)
                                  "抖动" -> rotate(imageView, 1)
                                  "弹跳" -> rotate(imageView, 2)
                                  else -> {
                                  }
                              }
                              if (SPUtils.getInstance(spName).getBoolean("flutter.IconX", false)) {
                                  Glide.with(mActivity).load(R.drawable.x_icon).into(imageView)
                              }
                              setViewLayoutParams(imageView, SizeUtils.dp2px(SPUtils.getInstance(spName).getLong("flutter.Icon_size", 48).toFloat()))
                          })
                          .setShowPattern(ShowPattern.ALL_TIME)
                          .setSidePattern(SidePattern.DEFAULT)
                          .setTag("Float1")
                          .setDragEnable(true)
                          .hasEditText(false)
                          .setGravity(Gravity.CENTER)
                          .setMatchParent(false, false)
                          .show()
              }

              "IconN" -> {
                  EasyFloat.dismissAppFloat("Float1")
                  EasyFloat.dismiss(mActivity, "Float1")
                  ToastUtils.showLong("请重新启动辅助")
              }*/

            else -> {
            }
        }
    }


    @Synchronized
    private fun rotate(v: View, i: Int) {
        when (i) {
            0 -> ViewAnimator
                    .animate(v)
                    .rotation(360f)
                    .repeatCount(ViewAnimator.INFINITE)
                    .start()
            1 -> ViewAnimator.animate(v)
                    .shake()
                    .interpolator(LinearInterpolator()).repeatCount(ViewAnimator.INFINITE)
                    .start()
            2 -> ViewAnimator.animate(v)
                    .bounce()
                    .interpolator(BounceInterpolator()).repeatCount(ViewAnimator.INFINITE)
                    .start()
            else -> {
            }
        }
    }

    private fun setViewLayoutParams(view: View, n: Int) {

        val lp = view.layoutParams
        if (lp.height != n || lp.width != n) {
            lp.width = n
            lp.height = n
            view.layoutParams = lp
        }
    }


}