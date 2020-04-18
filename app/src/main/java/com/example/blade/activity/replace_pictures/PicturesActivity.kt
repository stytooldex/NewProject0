package com.example.blade.activity.replace_pictures

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.*
import com.blankj.utilcode.util.PathUtils.getInternalAppFilesPath
import com.blankj.utilcode.util.ResourceUtils.readAssets2String
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.blade.*
import com.example.blade.base.BaseActivity
import com.example.blade.util.Code
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class PicturesActivity : BaseActivity() {

    private val destFilePath = getInternalAppFilesPath() + "/assets.zip"
    private var progressDialog: ProgressDialog? = null
    private val requestCode = 1

    override fun initData() {

    }

    override fun initView() {
        println(readAssets2String("assets.zip"))
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter: BaseQuickAdapter<*, *> = object : BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, createSampleData()) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(android.R.id.text1, item)
            }

        }
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> lo(getInternalAppFilesPath() + "/CXK.jpg");
                1 -> lo(getInternalAppFilesPath() + "/QBL.jpeg");
                2 -> Matisse.from(this@PicturesActivity)
                        .choose(MimeType.ofImage())
                        .countable(false)
                        .maxSelectable(1)
                        .imageEngine(GlideEngine())
                        .showPreview(false)
                        .forResult(requestCode)
                3 -> {
                    FileUtils.delete(_A)
                    FileUtils.delete(_B)
                    FileUtils.delete(_C)
                    FileUtils.delete(_D)
                    ToastUtils.showLong("恢复了～\n建议重启手机\n*不如分享我给朋友")
                    Code.su("am force-stop com.tencent.mobileqq", true)
                }

                else -> {

                }
            }
        }

    }

    private fun createSampleData(): ArrayList<String> {
        val arrayList = ArrayList<String>()

        arrayList.add("菜徐坤")
        arrayList.add("乔碧萝")
        arrayList.add("自定义图片")
        arrayList.add("QQ万能盒子病毒免费解药（恢复）")
        return arrayList
    }

    override fun setLayoutRes(): Int {
        return R.layout.activity_pictures
    }

    init {
        if (ResourceUtils.copyFileFromAssets("assets.zip", destFilePath)) {
            ZipUtils.unzipFile(destFilePath, getInternalAppFilesPath())
        } else {
            Code.bug();
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {

            val file = Matisse.obtainPathResult(data)[0]

            lo(file);
        }
    }

    private fun lo(s: String) {
        progressDialog = ProgressDialog.show(this, "Loading...", "正在处理...")
        GlobalScope.launch {
            suspendingSetData(s)
        }
    }

    private suspend fun suspendingSetData(i: String) {
        withContext(Dispatchers.IO) {
            listFiles(i, _A)
            listFiles(i, _B)
            listFiles(i, _C)
            listFiles(i, _D)
        }
    }

    private fun listFiles(a: String, b: String) {
        val cr = contentResolver
        try {
            val bmp = BitmapFactory.decodeStream(cr.openInputStream(UriUtils.file2Uri(File(a))))
            val files = File(b).listFiles()
                    ?: //ToastUtils.showLong("没安装");
                    return
            val c = Vector<String>()
            for (f in files) {
                if (f.isDirectory) {
                    val arrayOfFiles = f.listFiles() ?: continue
                    for (inner_f in arrayOfFiles) {
                        if (inner_f.isFile) {
                            c.add(inner_f.absolutePath)
                        }
                    }
                }
                if (f.isFile) {
                    c.add(f.absolutePath)
                }
            }
            for (s in c) {
                val ops: OutputStream = FileOutputStream(s)
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, ops)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        ToastUtils.showLong("替换完成\n重启QQ看到效果")
        progressDialog?.cancel()
        Code.su("am force-stop com.tencent.mobileqq", true)
    }
}
