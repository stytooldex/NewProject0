package com.example.blade.activity.search_mik

import android.content.pm.ApplicationInfo
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.SPUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.blade.R
import com.example.blade.base.BaseFLazyFragment
import com.example.blade.util.Code.getBean
import kotlinx.android.synthetic.main.fragment_apk_list.view.*


/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [ ]
 * interface.
 */
class ApkFragment : BaseFLazyFragment() {
    override fun setLayoutRes(): Int {
        return R.layout.fragment_apk_list
    }

    override fun initView(view: View) {
        val name = SPUtils.getInstance().getString("shellMik", "su")
        val recyclerView = view.list_apk_2
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter: BaseQuickAdapter<*, *> = object : BaseQuickAdapter<AppUtils.AppInfo, BaseViewHolder>(android.R.layout.activity_list_item, getAppsInfo2(name)) {
            override fun convert(holder: BaseViewHolder, item: AppUtils.AppInfo) {
                holder.setText(android.R.id.text1, item.name)
                holder.setImageDrawable(android.R.id.icon, item.icon)
            }

        }
        /* recyclerView.adapter = adapter
         adapter.setOnItemClickListener { _, view, _ ->
             val packageName = view.tv_package.text.toString()
             val path = view.tv_path.text.toString()
             ViewDialog.dump(this, packageName, path)
         }*/
    }

    private fun getAppsInfo2(i: String): MutableList<AppUtils.AppInfo> {
        val list: MutableList<AppUtils.AppInfo> = ArrayList()
        val pm = requireActivity().packageManager ?: return list
        val installedPackages = pm.getInstalledPackages(0)
        for (pi in installedPackages) {
            val ai = getBean(pm, pi) ?: continue
            if (pi.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                if (pi.packageName.contains(i)) {
                    list.add(ai)
                }
            }
        }
        return list
    }
}