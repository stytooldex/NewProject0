package com.example.blade.activity.tailoring;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.blade.R;
import com.example.blade.base.BaseActivity;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import static com.blankj.utilcode.util.PathUtils.getExternalPicturesPath;
import static com.blankj.utilcode.util.TimeUtils.getNowMills;

public class bin_Activity extends BaseActivity {

    private CropImageView mCropView;
    private Uri mSourceUri = null;
    private Uri mSourceUri2 = null;
    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.PNG;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mCropView = findViewById(R.id.cropImageView);
        bin();
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_bin_main;
    }

    private void bin() {
        int requestCode = 1;
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(false)
                .maxSelectable(1)
                .imageEngine(new GlideEngine())
                .showPreview(false)
                .forResult(requestCode);

    }

    private void bindViews() {
        mCropView = findViewById(R.id.cropImageView);
        findViewById(R.id.buttonDone).setOnClickListener(btnListener);
        findViewById(R.id.buttonFitImage).setOnClickListener(btnListener);
        findViewById(R.id.button1_1).setOnClickListener(btnListener);
        findViewById(R.id.button3_4).setOnClickListener(btnListener);
        findViewById(R.id.button4_3).setOnClickListener(btnListener);
        findViewById(R.id.button9_16).setOnClickListener(btnListener);
        findViewById(R.id.button16_9).setOnClickListener(btnListener);
        findViewById(R.id.buttonFree).setOnClickListener(btnListener);
        findViewById(R.id.buttonPickImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateLeft).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateRight).setOnClickListener(btnListener);
        findViewById(R.id.buttonCustom).setOnClickListener(btnListener);
        findViewById(R.id.buttonCircle).setOnClickListener(btnListener);
        findViewById(R.id.buttonShowCircleButCropAsSquare).setOnClickListener(btnListener);
    }

    private final View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonDone:
                    mCropView.crop(mSourceUri).execute(mCropCallback);
                    break;
                case R.id.buttonFitImage:
                    mCropView.setCropMode(CropImageView.CropMode.FIT_IMAGE);
                    break;
                case R.id.button1_1:
                    mCropView.setCropMode(CropImageView.CropMode.SQUARE);
                    break;
                case R.id.button3_4:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_3_4);
                    break;
                case R.id.button4_3:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                    break;
                case R.id.button9_16:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_9_16);
                    break;
                case R.id.button16_9:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);
                    break;
                case R.id.buttonCustom:
                    mCropView.setCustomRatio(7, 5);
                    break;
                case R.id.buttonFree:
                    mCropView.setCropMode(CropImageView.CropMode.FREE);
                    break;
                case R.id.buttonCircle:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
                    break;
                case R.id.buttonShowCircleButCropAsSquare:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE);
                    break;
                case R.id.buttonRotateLeft:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                    break;
                case R.id.buttonRotateRight:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                    break;
                case R.id.buttonPickImage:
                    bin();
                    //BasicFragmentPermissionsDispatcher.pickImageWithCheck(BasicFragment.this);
                    break;
            }
        }
    };
    private final CropCallback mCropCallback = new CropCallback() {
        @Override
        public void onSuccess(Bitmap cropped) {
            String s = getExternalPicturesPath() + "/" + getNowMills() + ".png";
            ImageUtils.save(cropped, s, mCompressFormat);
            ToastUtils.showLong("已保存的到" + s);
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {

        }

        @Override
        public void onError(Throwable e) {

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (data != null) {
                Uri file = Matisse.obtainResult(data).get(0);
                mSourceUri2 = file;
                //mCropView.getActualCropRect( );
                mCropView.load(file).execute(new LoadCallback() {
                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.toString());
                    }

                    @Override
                    public void onSuccess() {
                        bindViews();
                    }
                });
            }

        }
    }
}
