package com.example.blade.service;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class WeChatMotionMonitor {

    public static void policy(AccessibilityNodeInfo nodeInfo, String packageName, String className) {
        if (nodeInfo == null) {
            return;
        }

        if (!"com.tencent.mobileqq".equals(packageName)) {
            return;
        }

        // 该界面下所有 ViewId 节点
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mobileqq:id/dyw");
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                // 防止点赞自己，跳转到其他界面
                continue;
            }

            if (list.get(i).isClickable()) {
                list.get(i).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.d("1", "clickBtnByResId = " + list.get(i).toString());
            }
        }
    }
}