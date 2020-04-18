package com.example.blade

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ClickUtils.applyPressedViewScale
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ViewUtils
import com.example.blade.activity.AboutActivity
import com.example.blade.activity.search_mik.SearchActivity
import com.example.blade.main.SectionsPagerAdapter
import com.example.blade.main.fragment.*
import com.example.blade.main.fragment.files.MediaStoreFragment
import com.example.blade.util.shell.shizuku.TransparentActivity
import com.github.megatronking.stringfog.annotation.StringFogIgnore
import kotlinx.android.synthetic.main.activity_mik.*
import qiu.niorgai.StatusBarCompat.changeToLightStatusBar
import qiu.niorgai.StatusBarCompat.setStatusBarColor

@StringFogIgnore
class MainActivity : AppCompatActivity(R.layout.activity_mik) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ViewUtils.runOnUiThread {
                setStatusBarColor(this, ColorUtils.getColor(android.R.color.white), 0)
                changeToLightStatusBar(this)
            }
        }
        initView()
        //LibsBuilder().

        //FragmentUtils.add(supportFragmentManager, fragment, R.layout.libs_bu)
        //rRoot.shell(this, "am start -n com.tencent.mm/com.tencent.mm.plugin.setting.ui.setting.SettingRedesign")
        //val s = "am start -n com.tencent.mm/com.tencent.mm.plugin.setting.ui.setting.SettingRedesign"
        //SHellKt.run(null, s)
        //ActivityUtils.startActivity(TransparentActivity::class.java)
    }


    private fun initView() {
        val viewPager = view_pager_mik
        viewPager.adapter = SectionsPagerAdapter(this, supportFragmentManager, list())
        val tabs = tabs_mik
        tabs.setupWithViewPager(viewPager)
        imageView6.setOnClickListener { v ->
            applyPressedViewScale(v)
            showMenu(v)
        }
        textView2_cil.setOnClickListener { v ->
            applyPressedViewScale(v)
            ActivityUtils.startActivity(this, SearchActivity::class.java)
        }
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


    override fun onBackPressed() {
        //super.onBackPressed()
        moveTaskToBack(true)
    }

    private fun showMenu(v: View) {
        val popup = PopupMenu(this, v)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    ActivityUtils.startActivity(this, AboutActivity::class.java)
                    true
                }
                R.id.action_sh -> {
                    val myItems = listOf("Root(正常模式)", "Shizuku(免Root激活模式)")
                    MaterialDialog(this).show {
                        title(text = "选择工作模式")
                        message(text = "工作环境不一致,实现效果相同")
                        listItemsSingleChoice(items = myItems, initialSelection = SPUtils.getInstance().getInt("action_sh")) { _, index, _ ->
                            SPUtils.getInstance().put("action_sh", index)
                        }

                    }
                    true
                }
                else -> false
            }
        }
        popup.inflate(R.menu.menu_scrolling)
        popup.show()
    }
}
