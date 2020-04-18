package com.example.blade.util.shell.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.CRC32;

public class IOUtils {
    private static final String TAG = "IOUtils";

    public static void copyStream(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[1024 * 1024];
        int len;
        while ((len = from.read(buf)) > 0) {
            to.write(buf, 0, len);
        }
    }

    public static void copyFile(File original, File destination) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(original); FileOutputStream outputStream = new FileOutputStream(destination)) {
            copyStream(inputStream, outputStream);
        }
    }

    public static void copyFileFromAssets(Context context, String assetFileName, File destination) throws IOException {
        try (InputStream inputStream = context.getAssets().open(assetFileName); FileOutputStream outputStream = new FileOutputStream(destination)) {
            copyStream(inputStream, outputStream);
        }
    }

    public static void deleteRecursively(File f) {
        if (f.isDirectory()) {
            for (File child : f.listFiles())
                deleteRecursively(child);
        }
        f.delete();
    }

    public static long calculateFileCrc32(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            CRC32 crc32 = new CRC32();
            byte[] buffer = new byte[1024 * 1024];
            int read;

            while ((read = fileInputStream.read(buffer)) > 0)
                crc32.update(buffer, 0, read);

            return crc32.getValue();
        }
    }

    public static Thread writeStreamToStringBuilder(StringBuilder builder, InputStream inputStream) {
        Thread t = new Thread(() -> {
            try {
                char[] buf = new char[1024];
                int len;
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((len = reader.read(buf)) > 0)
                    builder.append(buf, 0, len);

                reader.close();
            } catch (Exception e) {
                Log.wtf(TAG, e);
            }
        });
        t.start();
        return t;
    }

    public static String readStream(InputStream aInputStream, Charset charset) throws IOException {
        try (InputStream inputStream = aInputStream; ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            copyStream(inputStream, outputStream);
            return new String(outputStream.toByteArray(), charset);
        }
    }

}