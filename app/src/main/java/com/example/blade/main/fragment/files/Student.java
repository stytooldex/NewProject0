package com.example.blade.main.fragment.files;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Student {
    private static String suffix = "";

    public static ArrayList<File> getList(String pathName) {
        File file = new File(pathName);
        File[] files = FileUtils.listFilesInDirWithFilter(file, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile() && !suffix.isEmpty()) {
                    return pathname.getName().endsWith(suffix);
                }
                return true;
            }
        }, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                return o1.getName().compareTo(o2.getName());
            }

        }).toArray(new File[0]);
        List<File> result = Arrays.asList(files);
        return new ArrayList<>(result);
    }
}
