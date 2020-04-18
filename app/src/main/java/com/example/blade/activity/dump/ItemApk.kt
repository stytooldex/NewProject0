package com.example.blade.activity.dump

import com.blankj.utilcode.util.AppUtils.AppInfo
import java.io.File
import java.util.*

internal class ItemApk {


    class DisplayNameAscendComparator : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            return apk1.name.compareTo(apk2.name)
        }
    }

    internal class DisplayNameAscendComparatorIC : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            return apk1.name.compareTo(apk2.name, ignoreCase = true)
        }
    }

    internal class DisplayNameDescendComparator : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            return -apk1.name.compareTo(apk2.name)
        }
    }

    internal class DisplayNameDescendComparatorIC : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            return -apk1.name.compareTo(apk2.name, ignoreCase = true)
        }
    }

    internal class SizeAscendComparator : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            val diff = (File(apk1.packagePath).length() - File(apk1.packagePath).length()).toInt()
            return diff.compareTo(0)
        }
    }

    internal class SizeDescendComparator : Comparator<AppInfo> {
        override fun compare(apk1: AppInfo, apk2: AppInfo): Int {
            return SizeAscendComparator().compare(apk2, apk1)
        }
    }
}