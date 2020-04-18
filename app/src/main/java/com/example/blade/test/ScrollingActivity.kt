package com.example.blade.test

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.blade.R
import com.example.blade.main.SectionsPagerAdapter
import com.example.blade.main.fragment.*
import com.example.blade.main.fragment.files.MediaStoreFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import qiu.niorgai.StatusBarCompat
import java.util.*


class ScrollingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutRes())
        initView()
        initData()
    }

    private fun initData() {
        val load = "http://tiebapic.baidu.com/forum/pic/item/17b72edda3cc7cd915d411bb2e01213fb90e91eb.jpg"
        //Glide.with(this).load(load).transform(BlurTransformation(25)).into(imageView3)
        //renderScriptBlur(view2Bitmap(imageView3), 25f)
       
    }

    private fun initView() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val appBarLayout = findViewById<AppBarLayout>(R.id.app_bar)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.barLayout)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, list())
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        //
        StatusBarCompat.setStatusBarColorForCollapsingToolbar(this, appBarLayout, collapsingToolbarLayout, toolbar, 0)

    }

    private fun list(): ArrayList<Fragment> {
        val list = ArrayList<Fragment>()
        list.add(dailyFragment())
        list.add(PicFragment())
        list.add(STMFragment())
        list.add(QQFragment())
        list.add(MGameFragment())
        list.add(MediaStoreFragment())
        return list
    }

    @LayoutRes
    private fun setLayoutRes(): Int {
        return R.layout.activity_scrolling
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        moveTaskToBack(true)
    }


}