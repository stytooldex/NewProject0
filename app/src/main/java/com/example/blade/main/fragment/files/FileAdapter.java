package com.example.blade.main.fragment.files;


import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.blade.R;

import java.io.File;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.blankj.utilcode.util.FileUtils.delete;
import static com.blankj.utilcode.util.FileUtils.getSize;
import static com.blankj.utilcode.util.FileUtils.isDir;
import static com.blankj.utilcode.util.FileUtils.rename;

/**
 * @author Nico
 */
public class FileAdapter extends BaseQuickAdapter<File, BaseViewHolder> {

    public FileAdapter(List<File> list) {
        super(R.layout.simple_list_item_2, list);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final File item) {
        helper
                .setText(R.id.text1, item.getName())
                .setText(R.id.text2, item.getPath())
                .setText(R.id.textSize, getSize(item.getPath()));
        ImageView button = helper.getView(R.id.imageView);
        if (isDir(item.getPath())) {
            button.setVisibility(GONE);
        } else {
            button.setVisibility(VISIBLE);
            Glide.with(getContext()).load(item.getPath()).into(button);
        }
        ImageView imageView2 = helper.getView(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, item.getAbsolutePath(), helper.getAdapterPosition());
            }
        });
        View textView = helper.getView(R.id.view);
        textView.setVisibility(isDir(item.getPath()) ? GONE : VISIBLE);

    }

    private void showMenu(View v, final String path, final int adapterPosition) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actions_Rename:
                        rename(path, "bbbbbbbb");
                        return true;
                    case R.id.actions_delete:
                        delete(path);
                        remove(adapterPosition - 1);
                        return true;
                    case R.id.actions_share:
                      /*  Shelly.share(getContext())
                                .text("text with image")
                                .image(Uri.parse(path))
                                .send();*/

                        return true;
                    case R.id.actions_Compressed_folder:
                        /*  try {
                          if (ZipUtils.zipFile(path, sd + "/" + path + ".zip")) {
                                ToastUtils.showLong("ok");
                            } else {
                                ToastUtils.showLong("No");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtils.showLong(e.getMessage());
                        }*/
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.actions_ap);
        popup.show();
    }
}