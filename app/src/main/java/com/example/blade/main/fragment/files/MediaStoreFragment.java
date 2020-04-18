package com.example.blade.main.fragment.files;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.blade.R;
import com.example.blade.base.BaseFLazyFragment;
import com.example.blade.main.fragment.FileFragment;
import com.example.blade.main.fragment.files.adapter.PublicTabViewPagerAdapter;
import com.example.blade.main.fragment.files.fragment.FolderDataFragment;
import com.example.blade.main.fragment.files.model.FileInfo;
import com.example.blade.main.fragment.files.utils.FileUtil;
import com.example.blade.util.permission.Permits;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.constant.PermissionConstants.STORAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediaStoreFragment extends BaseFLazyFragment {
    private TabLayout tlFile;
    private ViewPager vpFile;

    private ArrayList<FileInfo> imageData = new ArrayList<>();
    private ArrayList<FileInfo> wordData = new ArrayList<>();
    private ArrayList<FileInfo> xlsData = new ArrayList<>();
    private ArrayList<FileInfo> pptData = new ArrayList<>();
    private ArrayList<FileInfo> pdfData = new ArrayList<>();

    private ProgressDialog progressDialog;

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                initData();
            }
        }
    };

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_media_store;
    }

    @Override
    public void initView(View view) {


        tlFile = view.findViewById(R.id.tl_file);
        vpFile = view.findViewById(R.id.vp_file);


        progressDialog = new ProgressDialog(requireActivity(), ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("加载...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Permits.iniQ(requireActivity(), new Permits.Callback() {
            @Override
            public void ok() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        getFolderData();
                    }
                }.start();
            }
        }, STORAGE);

    }

    /**
     * 遍历文件夹中资源
     */
    private void getFolderData() {

        getImages();

        getDocumentData(1);
        getDocumentData(2);
        getDocumentData(3);
        getDocumentData(4);

        handler.sendEmptyMessage(1);
    }

    private void initData() {

        List<String> mTabTitle = new ArrayList<>();
        List<Fragment> mFragment = new ArrayList<>();

        mTabTitle.add("FileFragment");
        mTabTitle.add("图片");
        mTabTitle.add("word");
        mTabTitle.add("xls");
        mTabTitle.add("ppt");
        mTabTitle.add("pdf");

        mFragment.add(new FileFragment());
        FolderDataFragment imageFragment = new FolderDataFragment();
        Bundle imageBundle = new Bundle();
        imageBundle.putParcelableArrayList("file_data", imageData);
        imageBundle.putBoolean("is_image", true);
        imageFragment.setArguments(imageBundle);
        mFragment.add(imageFragment);

        FolderDataFragment wordFragment = new FolderDataFragment();
        Bundle wordBundle = new Bundle();
        wordBundle.putParcelableArrayList("file_data", wordData);
        wordBundle.putBoolean("is_image", false);
        wordFragment.setArguments(wordBundle);
        mFragment.add(wordFragment);

        FolderDataFragment xlsFragment = new FolderDataFragment();
        Bundle xlsBundle = new Bundle();
        xlsBundle.putParcelableArrayList("file_data", xlsData);
        xlsBundle.putBoolean("is_image", false);
        xlsFragment.setArguments(xlsBundle);
        mFragment.add(xlsFragment);

        FolderDataFragment pptFragment = new FolderDataFragment();
        Bundle pptBundle = new Bundle();
        pptBundle.putParcelableArrayList("file_data", pptData);
        pptBundle.putBoolean("is_image", false);
        pptFragment.setArguments(pptBundle);
        mFragment.add(pptFragment);

        FolderDataFragment pdfFragment = new FolderDataFragment();
        Bundle pdfBundle = new Bundle();
        pdfBundle.putParcelableArrayList("file_data", pdfData);
        pdfBundle.putBoolean("is_image", false);
        pdfFragment.setArguments(pdfBundle);
        mFragment.add(pdfFragment);

        FragmentManager fragmentManager = getChildFragmentManager();

        PublicTabViewPagerAdapter tabViewPagerAdapter = new PublicTabViewPagerAdapter(fragmentManager, mFragment, mTabTitle);
        vpFile.setAdapter(tabViewPagerAdapter);

        tlFile.setupWithViewPager(vpFile);

        tlFile.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpFile.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        progressDialog.dismiss();
    }

    /**
     * 加载图片
     */
    private void getImages() {

        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DISPLAY_NAME};

        //asc 按升序排列
        //desc 按降序排列
        //projection 是定义返回的数据，selection 通常的sql 语句，例如  selection=MediaStore.Images.ImageColumns.MIME_TYPE+"=? " 那么 selectionArgs=new String[]{"jpg"};
        ContentResolver mContentResolver = requireActivity().getContentResolver();
        Cursor cursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_MODIFIED + "  desc");


        String imageId = null;

        String fileName;

        String filePath;

        while (cursor.moveToNext()) {

            imageId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID));

            fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));

            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));


            Log.e("photo", imageId + " -- " + fileName + " -- " + filePath);
            FileInfo fileInfo = FileUtil.getFileInfoFromFile(new File(filePath));
            imageData.add(fileInfo);
        }
        cursor.close();

        cursor = null;
    }

    /**
     * 获取手机文档数据
     *
     * @param selectType
     */
    private void getDocumentData(int selectType) {

        String[] columns = new String[]{MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns.SIZE, MediaStore.Files.FileColumns.DATE_MODIFIED, MediaStore.Files.FileColumns.DATA};

        String select = "";

        switch (selectType) {
            //word
            case 1:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" + ")";
                break;
            //xls
            case 2:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.xls'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.xlsx'" + ")";
                break;
            //ppt
            case 3:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.ppt'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.pptx'" + ")";
                break;
            //pdf
            case 4:
                select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.pdf'" + ")";
                break;
        }

//        List<FileInfo> dataList = new ArrayList<>();
        ContentResolver contentResolver = requireActivity().getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), columns, select, null, null);
//        Cursor cursor = contentResolver.query(Uri.parse(Environment.getExternalStorageDirectory() + "/tencent/QQfile_recv/"), columns, select, null, null);

        int columnIndexOrThrow_DATA = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

        if (cursor != null) {
            while (cursor.moveToNext()) {

                String path = cursor.getString(columnIndexOrThrow_DATA);

                FileInfo document = FileUtil.getFileInfoFromFile(new File(path));

//                dataList.add(document);
                switch (selectType) {
                    //word
                    case 1:
                        wordData.add(document);
                        break;
                    //xls
                    case 2:
                        xlsData.add(document);
                        break;
                    //ppt
                    case 3:
                        pptData.add(document);
                        break;
                    //pdf
                    case 4:
                        pdfData.add(document);
                        break;
                }
            }
            cursor.close();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

}
