package com.example.blade.main.fragment.files.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blade.R;
import com.example.blade.base.BaseFLazyFragment;
import com.example.blade.main.fragment.files.adapter.FolderDataRecycleAdapter;
import com.example.blade.main.fragment.files.model.FileInfo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderDataFragment extends BaseFLazyFragment {

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_folder_data;
    }


    @Override
    public void initView(View rootView) {
        RecyclerView rvDoc = rootView.findViewById(R.id.rv_doc);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            List<FileInfo> data = bundle.getParcelableArrayList("file_data");
            boolean isImage = bundle.getBoolean("is_image");
            rvDoc.setLayoutManager(new LinearLayoutManager(requireActivity()));
            FolderDataRecycleAdapter pptListAdapter = new FolderDataRecycleAdapter(data, isImage);
            rvDoc.setAdapter(pptListAdapter);
        }

    }

}
