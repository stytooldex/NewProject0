package com.example.blade.test;

import android.content.pm.IPackageManager;
import android.os.RemoteException;

import moe.shizuku.api.ShizukuBinderWrapper;
import moe.shizuku.api.SystemServiceHelper;

public class shoe {
    public static void shell() throws RemoteException {
        IPackageManager pm = IPackageManager.Stub.asInterface(new ShizukuBinderWrapper(SystemServiceHelper.getSystemService("package")));
        pm.getInstalledPackages(0, 0);
    }
}
