package com.example.blade.activity.dump

import android.content.pm.ApplicationInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearSpaceItemDecoration
import com.blankj.utilcode.util.AppUtils.AppInfo
import com.blankj.utilcode.util.ClickUtils.applyPressedViewScale
import com.blankj.utilcode.util.SPUtils
import com.example.blade.R
import com.example.blade.activity.dump.ItemApk.DisplayNameAscendComparator
import com.example.blade.base.BaseActivity
import com.example.blade.util.Code
import com.example.blade.util.widget.ViewDialog
import com.qw.curtain.lib.Curtain
import com.qw.curtain.lib.IGuide
import com.qw.curtain.lib.shape.CircleShape
import kotlinx.android.synthetic.main.item_dump_apk.view.*
import kotlinx.android.synthetic.main.main_dump_apk.*
import java.util.*


class ApkActivity : BaseActivity() {

    private lateinit var adapter: ItemApkAdapter

    private var isSTM = true

    override fun initData() {
        show1()
        floatingActionButton_apk.setOnClickListener { v ->
            applyPressedViewScale(v)
            if (isSTM) {
                isSTM = false
                adapter.setList(getAppsInfo2(true))
            } else {
                isSTM = true
                adapter.setList(getAppsInfo2(false))
            }
        }

    }

    private fun show1() = if (SPUtils.getInstance().getBoolean("show1", true)) Curtain(this)
            .withShape(floatingActionButton_apk, CircleShape())
            .withPadding(floatingActionButton_apk, 16)
            .setCancelBackPressed(false)
            .setTopView(R.layout.nav_header_main)
            .setCallBack(object : Curtain.CallBack {
                override fun onDismiss(iGuide: IGuide) = Unit

                override fun onShow(iGuide: IGuide) {
                    iGuide.findViewByIdInTopView<TextView>(R.id.tv_i_know)?.setOnClickListener {
                        iGuide.dismissGuide()
                        SPUtils.getInstance().put("show1", false)
                    }
                }
            }).show() else println("2284467793")

    override fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.lvMain)
        recyclerView.addItemDecoration(RecyclerViewLinearSpaceItemDecoration.Builder(this).margin(16).create())
        adapter = ItemApkAdapter(data())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { _, view, _ ->
            val packageName = view.tv_package.text.toString()
            val path = view.tv_path.text.toString()
            ViewDialog.dump(this, packageName, path)
        }
        adapter.setDiffCallback(DiffCallback())
    }


    private fun data(): List<AppInfo> {
        //PackageManager pm = getPackageManager();
        val result = getAppsInfo2(false)
        Collections.sort(result, DisplayNameAscendComparator())
        return result
    }

    private fun getAppsInfo2(isSTM: Boolean): List<AppInfo> {
        val list: MutableList<AppInfo> = ArrayList()
        val pm = packageManager ?: return list
        val installedPackages = pm.getInstalledPackages(0)
        for (pi in installedPackages) {
            val ai = Code.getBean(pm, pi) ?: continue
            if (isSTM) {
                list.add(ai)
            } else {
                if (pi.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                    list.add(ai)
                }
            }
        }
        return list
    }

    override fun setLayoutRes(): Int {
        return R.layout.main_dump_apk
    }


    //private val REQUEST_CODE_TAKE_PHOTO = 0x110
}