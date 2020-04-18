package com.example.blade.util.shell

import android.content.pm.ComponentInfo

interface IController {

    /**
     * a method to change a component's state
     *
     * @param packageName   package name
     * @param componentName component name
     * @param state         PackageManager.COMPONENT_ENABLED_STATE_ENABLED: enable component
     * COMPONENT_ENABLED_STATE_DISABLED: disable component
     * @return true : changed component state successfully
     * false: cannot disable component
     */
    fun switchComponent(packageName: String, componentName: String, state: Int): Boolean

    fun enable(packageName: String, componentName: String): Boolean

    fun disable(packageName: String, componentName: String): Boolean

    fun batchEnable(componentList: List<ComponentInfo>, action: (info: ComponentInfo) -> Unit): Int

    fun batchDisable(componentList: List<ComponentInfo>, action: (info: ComponentInfo) -> Unit): Int

    fun checkComponentEnableState(packageName: String, componentName: String): Boolean
}