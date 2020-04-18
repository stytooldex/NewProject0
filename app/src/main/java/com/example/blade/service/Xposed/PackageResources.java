package com.example.blade.service.Xposed;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageResources implements IXposedHookLoadPackage, IXposedHookInitPackageResources, IXposedHookZygoteInit {

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam p1) {
        // TODO: Implement this method
    }

    @Override
    public void initZygote(StartupParam p1) {
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam p1) {


    }
}
