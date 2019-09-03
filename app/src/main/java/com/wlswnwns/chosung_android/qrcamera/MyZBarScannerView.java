package com.wlswnwns.chosung_android.qrcamera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;

import androidx.appcompat.app.AppCompatActivity;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import me.dm7.barcodescanner.core.DisplayUtils;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;


public class MyZBarScannerView extends MyBarcodeScannerView {
    private static final String TAG = "ZBarScannerView";

    public interface ResultHandler {
        public void handleResult(Result rawResult, byte[] imageData);
    }

    static {
        System.loadLibrary("iconv");
    }

    private ImageScanner mScanner;
    private List<BarcodeFormat> mFormats;
    private ResultHandler mResultHandler;
    private com.wlswnwns.chosung_android.qrcamera.Camera.Presenter presenter;
    private File mFile;
    private byte[] mImageData;
    private Camera mCamera;
    int width;
    int height;
    int cameraId = 1;
    AppCompatActivity appCompatActivity;


    public byte[] getImageData() {
        return mImageData;
    }

    public Camera getCamera() {
        return mCamera;
    }

    public MyZBarScannerView(Context context) {
        super(context);
        setupScanner();
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setPresenter(com.wlswnwns.chosung_android.qrcamera.Camera.Presenter presenter) {
        this.presenter = presenter;
    }

    public MyZBarScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setupScanner();
    }

    public void setFormats(List<BarcodeFormat> formats) {
        mFormats = formats;
        setupScanner();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        mResultHandler = resultHandler;
    }

    public Collection<BarcodeFormat> getFormats() {
        if (mFormats == null) {
            return BarcodeFormat.ALL_FORMATS;
        }
        return mFormats;
    }

    public void setupScanner() {
        mScanner = new ImageScanner();
        mScanner.setConfig(0, Config.X_DENSITY, 3);
        mScanner.setConfig(0, Config.Y_DENSITY, 3);

        mScanner.setConfig(Symbol.NONE, Config.ENABLE, 0);
        for (BarcodeFormat format : getFormats()) {
            mScanner.setConfig(format.getId(), Config.ENABLE, 1);
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (mResultHandler == null) {
            return;
        }

        try {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();
            width = size.width;
            height = size.height;


            this.mImageData = data;
            this.mCamera = camera;

            if (DisplayUtils.getScreenOrientation(getContext()) == Configuration.ORIENTATION_PORTRAIT) {
                int rotationCount = getRotationCount();
                if (rotationCount == 1 || rotationCount == 3) {
                    int tmp = width;
                    width = height;
                    height = tmp;
                }
                data = getRotatedData(data, camera);
            }


            Rect rect = getFramingRectInPreview(width, height);
            Image barcode = new Image(width, height, "Y800");
            barcode.setData(data);
            barcode.setCrop(rect.left, rect.top, rect.width(), rect.height());

            int result = mScanner.scanImage(barcode);


            if (result != 0) {
                //   camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                SymbolSet syms = mScanner.getResults();
                final Result rawResult = new Result();
                for (Symbol sym : syms) {
                    // In order to retreive QR codes containing null bytes we need to
                    // use getDataBytes() rather than getData() which uses C strings.
                    // Weirdly ZBar transforms all data to UTF-8, even the data returned
                    // by getDataBytes() so we have to decode it as UTF-8.
                    String symData;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        symData = new String(sym.getDataBytes(), StandardCharsets.UTF_8);
                    } else {
                        symData = sym.getData();
                    }
                    if (!TextUtils.isEmpty(symData)) {
                        rawResult.setContents(symData);
                        rawResult.setBarcodeFormat(BarcodeFormat.getFormatById(sym.getType()));
                        break;
                    }
                }

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Stopping the preview can take a little long.
                        // So we want to set result handler to null to discard subsequent calls to
                        // onPreviewFrame.

                        byte[] currentData = null;

                        if (camera != null) {
                            Camera.Parameters parameters = camera.getParameters();
                            int imageFormat = parameters.getPreviewFormat();
                            Bitmap bitmap = null;

                            if (imageFormat == ImageFormat.NV21) {
                                int w = parameters.getPreviewSize().width;
                                int h = parameters.getPreviewSize().height;

                                YuvImage yuvImage = new YuvImage(mImageData, imageFormat, w, h, null);
                                Rect rect = new Rect(0, 0, w, h);
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                yuvImage.compressToJpeg(rect, 100, outputStream);

                                bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size());
                            } else if (imageFormat == ImageFormat.JPEG || imageFormat == ImageFormat.RGB_565) {
                                bitmap = BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length);
                            }

                            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                            Camera.getCameraInfo(mCameraWrapper.mCameraId, cameraInfo);

                            int mDisplayOrientation = appCompatActivity.getWindowManager().getDefaultDisplay().getRotation();

                            //이미지를 디바이스 방향으로 회전
                            Matrix matrix = new Matrix();
                            matrix.postRotate(calculatePreviewOrientation(cameraInfo, mDisplayOrientation));
                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);


                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            currentData = stream.toByteArray();
                            Log.e("Log", "onPreviewFrame");
                        } else {
                            Log.e("Log", "Camera is null");
                        }


//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length, options);
//
//                        Log.e("비트맵이 널인듯",bitmap+"");
//
//                        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//                        Camera.getCameraInfo(mCameraWrapper.mCameraId, cameraInfo);
//
//                        int mDisplayOrientation = appCompatActivity.getWindowManager().getDefaultDisplay().getRotation();
//
//                        //이미지를 디바이스 방향으로 회전
//                        Matrix matrix = new Matrix();
//                        matrix.postRotate(calculatePreviewOrientation(cameraInfo, mDisplayOrientation));
//                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, 1920, 1080, matrix, true);
//
//                        //bitmap을 byte array로 변환
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                        byte[] currentData = stream.toByteArray();


                        ResultHandler tmpResultHandler = mResultHandler;
                        mResultHandler = null;

                        stopCameraPreview();

                        if (tmpResultHandler != null) {

                            tmpResultHandler.handleResult(rawResult, currentData);
                        }
                    }
                });


            } else {
                camera.setOneShotPreviewCallback(this);
            }
        } catch (RuntimeException e) {
            // TODO: Terrible hack. It is possible that this method is invoked after camera is released.
            Log.e(TAG, e.toString(), e);
        }
    }

    public int calculatePreviewOrientation(Camera.CameraInfo info, int rotation) {
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }


    public void resumeCameraPreview(ResultHandler resultHandler) {
        mResultHandler = resultHandler;
        super.resumeCameraPreview();
    }
}
