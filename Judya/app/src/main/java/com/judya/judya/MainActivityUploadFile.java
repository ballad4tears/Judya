package com.judya.judya;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codemobiles.cmuploadimage.util.CameraIntentHelperActivity;


public class MainActivityUploadFile extends CameraIntentHelperActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    private ImageView selectImageInCameraBtn;
    private ImageView selectImageInGalleryBtn;
    private TextView imageFileName;
    private ImageView imageView;
    private Bitmap mPhotoBitMap;
    public String mUploadedFileName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_upload_file);

        bindWidget();
        setWidgetListener();
        requestAdhocPermission();
    }

    private void requestAdhocPermission() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_CAMERA || requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {

            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You cannot use this app because requried permission was not allowed", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }




    private void bindWidget() {
        selectImageInCameraBtn = (ImageView) findViewById(R.id.selectImageInCameraBtn);
        selectImageInGalleryBtn = (ImageView) findViewById(R.id.selectImageInGalleryBtn);
        imageFileName = (TextView) findViewById(R.id.imagename);
        imageView = (ImageView) findViewById(R.id.imageview);

    }



//    public class UploadImageTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getApplicationContext(), "Uploading...", Toast.LENGTH_SHORT).show();
//        }
//
//        protected String doInBackground(String... params) {
//
//            String url = params[0];
//            mUploadedFileName = UploadImageUtils.getRandomFileName();
//            final String result = UploadImageUtils.uploadFile(mUploadedFileName, url, mPhotoBitMap);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            if (result != null) {
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//    }

    private void setWidgetListener() {

        // Select image from camera
        selectImageInCameraBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                startCameraIntent(500); // 500 is max pixel, -1 is not compress
            }
        });

        // Select image from gallery
        selectImageInGalleryBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startGalleryIntent(500); // 500 is max pixel, -1 is not compress
            }
        });
    }

    @Override
    protected void onPhotoUriFound(Uri _photoUri, Bitmap _photoBitMap, String _filePath) {
        super.onPhotoUriFound(_photoUri, _photoBitMap, _filePath);

        mPhotoBitMap = _photoBitMap;
        imageFileName.setText(_filePath);
        imageView.setImageBitmap(mPhotoBitMap);
        File_Confix_Data.mPhotoBitMap=mPhotoBitMap;
        File_Confix_Data.image_set=true;
      //  new UploadImageTask().execute("http://10.1.12.82/uploads/up.php");
    }
}