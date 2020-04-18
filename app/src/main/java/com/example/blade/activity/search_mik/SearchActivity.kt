package com.example.blade.activity.search_mik

import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.blade.R
import com.example.blade.ReaderUtil
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(R.layout.activity_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackLabelView.setOnLabelClickListener { index, _, _ ->
            val s = editText.text.toString()
            when (index) {
                0 -> {
                    if (!isEmpty(s)) {
                        if (PatternsCompat.WEB_URL.matcher(s).matches()) {
                            ReaderUtil.isis(this, s)
                        } else {
                            ReaderUtil.isis(this, "https://www.baidu.com/s?wd=$s")
                        }
                    }
                }
                1 -> {
                    SPUtils.getInstance().put("shellMik", s)
                    FragmentUtils.replace(supportFragmentManager, ShellFragment(), R.id.frame_container_search)
                }
                2 -> {
                    SPUtils.getInstance().put("shellMik", s)
                    FragmentUtils.replace(supportFragmentManager, ApkFragment(), R.id.frame_container_search)
                }
                else -> {
                    ToastUtils.showLong("E:?")
                }
            }
        }
    }
}