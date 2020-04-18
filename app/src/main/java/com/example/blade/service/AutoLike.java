package com.example.blade.service;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.blankj.utilcode.util.SPUtils;
import com.example.blade.util.accessibility.BaseAccessibilityService;

import java.util.ArrayList;
import java.util.List;

import static com.example.blade.ConstantKt.spName;
import static com.example.blade.service.WeChatMotionMonitor.policy;

public class AutoLike extends BaseAccessibilityService {


    private List<String> allNameList = new ArrayList<>();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();
        String packageName = event.getPackageName().toString();
        String className = event.getClassName().toString();
        int eventType = event.getEventType();
        //List<AccessibilityNodeInfo> listNodes = source.findAccessibilityNodeInfosByViewId("android:id/list");
        // AccessibilityNodeInfo listNode = listNodes.get(0);
        // listNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                if ("com.tencent.mobileqq".equals(packageName)) {
                    if (SPUtils.getInstance(spName).getBoolean("flutter.user_AutoLike", false)) {
                        policy(source, packageName, "");
                        keepAppPraise("com.tencent.mobileqq:id/dyw");

                        if (SPUtils.getInstance(spName).getBoolean("flutter.display_more", false)) {
                            clickTextViewByText("显示更多");
                        }

                    }

                }
                break;

            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                break;
            case AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT:
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                break;
            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                break;
        }
    }

    private void keepAppPraise(String id) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();

        if (nodeInfo != null) {
            // 该界面下所有 ViewId 节点
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(id);
            for (AccessibilityNodeInfo item : list) {
                if (item.isClickable()) {
                    Log.d("2", "keepAppPraise = " + item.getClassName());
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }

    }


}
