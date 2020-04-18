package com.example.blade.activity

import com.blankj.utilcode.util.FragmentUtils
import com.example.blade.R
import com.example.blade.base.BaseActivity
import com.mikepenz.aboutlibraries.LibsBuilder

class AboutActivity : BaseActivity() {
    override fun initData() {

    }

    override fun initView() {
        val fragment = LibsBuilder()
                .withVersionShown(true)
                .withLicenseShown(true)
                //.withLibraryModification("androidx_activity__activity", Libs.LibraryFields.LIBRARY_NAME, "Activity Support")
                //.withLibraryEnchantment("com_mikepenz__fastadapter", "fastadapter")
                .supportFragment()

        FragmentUtils.replace(supportFragmentManager, fragment, R.id.frame_container)
    }

    override fun setLayoutRes(): Int {
        return R.layout.activity_about
    }
}