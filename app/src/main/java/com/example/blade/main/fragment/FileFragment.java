package com.example.blade.main.fragment;


import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.blade.R;
import com.example.blade.base.BaseFLazyFragment;
import com.example.blade.main.fragment.files.FileAdapter;
import com.example.blade.main.fragment.files.face.DiffDemoCallback;

import java.io.File;

public class FileFragment extends BaseFLazyFragment implements OnItemClickListener {


    private Button mBack;
    private FileAdapter adapter;


    private void refreshFileList(File file) {

        File[] subFiles = file.listFiles();
        if (subFiles != null) {
            for (File subFile : subFiles) {
                if (!subFile.getName().startsWith(".") && !subFile.isHidden()) ;
                //this.mFileList.add(subFile);
            }
        }
        //Collections.sort(this.mFileList, this);

    }


    interface FileReaderDialogCallback {
        void onFileSelect(String fileName, FrameLayout layout);

        boolean canBackPress();
    }

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_file0;
    }

    @Override
    public void initView(View viv) {

        mBack = viv.findViewById(R.id.button4);
        final RecyclerView recyclerView = viv.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        adapter = new FileAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setDiffCallback(new DiffDemoCallback());
        // View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) recyclerView.getParent(), false);
        // adapter.setHeaderView(view);
        //adapter.setAnimationEnable(true);
        //demoAdapter.setAnimationFirstOnly(false);
        //adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        //itemClick(view);
    }


    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

    }
}
