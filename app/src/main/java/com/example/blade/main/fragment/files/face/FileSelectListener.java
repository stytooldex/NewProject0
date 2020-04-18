package com.example.blade.main.fragment.files.face;

import java.io.File;

public interface FileSelectListener {
    void onFileSelect(File selectedFile);

    void onDirSelect(File selectedDir);
}
