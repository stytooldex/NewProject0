package com.example.blade.activity.tools;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.ZipUtils;
import com.example.blade.R;
import com.example.blade.activity.tools.file_.Shell_run;
import com.example.blade.activity.tools.file_.file_Run;
import com.example.blade.util.Code;

import java.io.IOException;

import static com.example.blade.util.Code.findPre;


public class PreferenceTestFragment extends PreferenceFragmentCompat {
    //File sdCard = Environment.getExternalStorageDirectory();
    //ms -> {
    //     if (!ms) {
    //arg0.setDefaultValue(false);
    //    }
    //}
    @Override
    public void onCreatePreferences(Bundle p1, String p2) {
        // TODO: Implement this method
        addPreferencesFromResource(R.xml.pref_setting_sip);
    }

    //@SuppressWarnings("all")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setDividerHeight(4);
        // setDivider(null);
        //Utils.init(requireActivity());

        File();


        findPre(this, "setting_1", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.font_info", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_2", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.pendant", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_4", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.font_effect", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_5_0", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                String a = SDCardUtils.getSDCardPathByEnvironment() + "/netease/cloudmusic/Ad";
                if ((boolean) newValue) {
                    Shell_run.mTar(true, requireActivity(), "chmod 000 " + a);
                } else {
                    Shell_run.mTar(true, requireActivity(), "chmod 777 " + a);
                }
            }
        });
        findPre(this, "setting_6", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.sticker_recommended_pics", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_7", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/DoutuRes", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_8", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.gift", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_9", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.troop", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_10", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.CorlorNick", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_11", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                @SuppressLint("SdCardPath") String a = "/data/data/com.tencent.mobileqq/files/bubble_info";
                if ((boolean) newValue) {
                    Shell_run.mTar(true, requireActivity(), "chmod 000 " + a);
                } else {
                    Shell_run.mTar(true, requireActivity(), "chmod 777 " + a);
                }
            }
        });
        findPre(this, "setting_6_2", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/tencent/MobileQQ/.emotionsm", (boolean) newValue, true);
            }
        });
        findPre(this, "setting_11", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                @SuppressLint("SdCardPath") String a = "/data/data/com.tencent.mobileqq/files/color_screen/";
                if ((boolean) newValue) {
                    Shell_run.mTar(true, requireActivity(), "chmod 000 " + a);
                } else {
                    Shell_run.mTar(true, requireActivity(), "chmod 777 " + a);
                }
            }
        });
        findPre(this, "setting_13", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/Android/data/com.tencent.tmgp.sgame", (boolean) newValue, false);
            }
        });
        findPre(this, "setting_14", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                file_Run.mTar2(requireActivity(), "/Android/data/com.tencent.tmgp.pubgmhd", (boolean) newValue, false);
            }
        });
        findPre(this, "setting_15", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                if ((boolean) newValue) {
                    if (FileUtils.isDir(PathUtils.getExternalAppCachePath() + "/shll")) {
                        Shell_run.mTar(false, requireActivity(), FileIOUtils.readFile2String(PathUtils.getExternalAppCachePath() + "/shll/抖音去广告.sh"));
                    } else {
                        File();
                        ToastUtils.showLong("(Dir)Exception");
                    }
                } else {
                    if (FileUtils.isDir(PathUtils.getExternalAppCachePath() + "/shll")) {
                        Shell_run.mTar(false, requireActivity(), FileIOUtils.readFile2String(PathUtils.getExternalAppCachePath() + "/shll/抖音去广告还原.sh"));
                    }
                }
            }
        });
        findPre(this, "setting_16", new Code.OnPreferenceClick() {
            @Override
            public void onClick(Preference preference, Object newValue) {
                if ((boolean) newValue) {
                    if (FileUtils.isDir(PathUtils.getExternalAppCachePath() + "/shll")) {
                        Shell_run.mTar(false, requireActivity(), FileIOUtils.readFile2String(PathUtils.getExternalAppCachePath() + "/shll/去除更新.sh"));
                    } else {
                        File();
                        ToastUtils.showLong("(Dir)Exception");
                    }
                } else {
                    if (FileUtils.isDir(PathUtils.getExternalAppCachePath() + "/shll")) {
                        Shell_run.mTar(false, requireActivity(), FileIOUtils.readFile2String(PathUtils.getExternalAppCachePath() + "/shll/抖音去广告还原.sh"));
                    }
                }
            }
        });


    }

    private void File() {
        if (ResourceUtils.copyFileFromAssets("dy.zip", PathUtils.getExternalAppCachePath() + "/dy.zip")) {
            try {
                ZipUtils.unzipFile(PathUtils.getExternalAppCachePath() + "/dy.zip", PathUtils.getExternalAppCachePath() + "/shll");
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtils.showLong("(ZIP)Exception");
            }
        } else {
            ToastUtils.showLong("IOException");
        }
    }

}
