package com.codemobiles.cmuploadimage.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.Date;
import java.util.Locale;

/**
 * A helper Activity to start the camera activity and retrieve the location
 * and orientation of the photo.
 *
 * @author Ralf Gehrer <ralf@ecotastic.de>
 */
public class CameraIntentHelperActivity extends AppCompatActivity {
    private static final String DATE_CAMERA_INTENT_STARTED_STATE = "de.ecotastic.android.camerautil.example.TakePhotoActivity.dateCameraIntentStarted";
    private static final String CAMERA_PIC_URI_STATE = "de.ecotastic.android.camerautil.example.TakePhotoActivity.CAMERA_PIC_URI_STATE";
    private static final String PHOTO_URI_STATE = "de.ecotastic.android.camerautil.example.TakePhotoActivity.PHOTO_URI_STATE";
    private static final String ROTATE_X_DEGREES_STATE = "de.ecotastic.android.camerautil.example.TakePhotoActivity.ROTATE_X_DEGREES_STATE";


    /**
     * Date and time the camera intent was started.
     */
    protected static Date dateCameraIntentStarted = null;
    /**
     * Default location where we want the photo to be ideally stored.
     */
    protected static Uri preDefinedCameraUri = null;
    /**
     * Potential 3rd location of photo data.
     */
    protected static Uri photoUriIn3rdLocation = null;
    /**
     * Retrieved location of the photo.
     */
    protected static Uri photoUri = null;
    /**
     * Orientation of the retrieved photo.
     */
    protected static int rotateXDegrees = 0;

    protected static int mMaxPixel = 500;


    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private final static int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 101;
    private Bitmap mPhotoBitMap;

    /**
     * Saves the current state of the activity.
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (dateCameraIntentStarted != null) {
            savedInstanceState.putString(DATE_CAMERA_INTENT_STARTED_STATE, DateParser.dateToString(dateCameraIntentStarted));
        }
        if (preDefinedCameraUri != null) {
            savedInstanceState.putString(CAMERA_PIC_URI_STATE, preDefinedCameraUri.toString());
        }
        if (photoUri != null) {
            savedInstanceState.putString(PHOTO_URI_STATE, photoUri.toString());
        }
        savedInstanceState.putInt(ROTATE_X_DEGREES_STATE, rotateXDegrees);
    }

    /**
     * Reinitializes a saved state of the activity.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(DATE_CAMERA_INTENT_STARTED_STATE)) {
            dateCameraIntentStarted = DateParser.stringToDate(savedInstanceState.getString(DATE_CAMERA_INTENT_STARTED_STATE));
        }
        if (savedInstanceState.containsKey(CAMERA_PIC_URI_STATE)) {
            preDefinedCameraUri = Uri.parse(savedInstanceState.getString(CAMERA_PIC_URI_STATE));
        }
        if (savedInstanceState.containsKey(PHOTO_URI_STATE)) {
            photoUri = Uri.parse(savedInstanceState.getString(PHOTO_URI_STATE));
        }
        rotateXDegrees = savedInstanceState.getInt(ROTATE_X_DEGREES_STATE);
    }

    /**
     * Starts the camera intent depending on the device configuration.
     * <p/>
     * <b>for Samsung and Sony devices:</b>
     * We call the camera activity with the method call to startActivityForResult. We only set the constant CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE. We do NOT set any other intent extras.
     * <p/>
     * <b>for all other devices:</b>
     * We call the camera activity with the method call to startActivityForResult as previously. This time, however, we additionally set the intent extra MediaStore.EXTRA_OUTPUT and provide an URI, where we want the image to be stored.
     * <p/>
     * In both cases we remember the time the camera activity was started.
     */
    protected void startCameraIntent(final double maxPixel) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // NOTE: Do NOT SET: intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraPicUri)
                // on Samsung Galaxy S2/S3/.. for the following reasons:
                // 1.) it will break the correct picture orientation
                // 2.) the photo will be stored in two locations (the given path and, additionally, in the MediaStore)
                String manufacturer = android.os.Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
                String model = android.os.Build.MODEL.toLowerCase(Locale.ENGLISH);
                String buildType = android.os.Build.TYPE.toLowerCase(Locale.ENGLISH);
                String buildDevice = android.os.Build.DEVICE.toLowerCase(Locale.ENGLISH);
                String buildId = android.os.Build.ID.toLowerCase(Locale.ENGLISH);
//				String sdkVersion = android.os.Build.VERSION.RELEASE.toLowerCase(Locale.ENGLISH);

                boolean setPreDefinedCameraUri = false;
                if (!(manufacturer.contains("samsung")) && !(manufacturer.contains("sony"))) {
                    setPreDefinedCameraUri = true;
                }
                if (manufacturer.contains("samsung") && model.contains("galaxy nexus")) { //TESTED
                    setPreDefinedCameraUri = true;
                }
                if (manufacturer.contains("samsung") && model.contains("gt-n7000") && buildId.contains("imm76l")) { //TESTED
                    setPreDefinedCameraUri = true;
                }

                if (buildType.contains("userdebug") && buildDevice.contains("ariesve")) {  //TESTED
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("crespo")) {   //TESTED
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("gt-i9100")) { //TESTED
                    setPreDefinedCameraUri = true;
                }

                ///////////////////////////////////////////////////////////////////////////
                // TEST
                if (manufacturer.contains("samsung")) { //T-Mobile LTE enabled Samsung S3
                    setPreDefinedCameraUri = true;
                    rotateXDegrees = 90;
                }
                if (buildDevice.contains("cooper")) {
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("t0lte")) {
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("kot49h")) {
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("t03g")) {
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("gt-i9300")) {
                    setPreDefinedCameraUri = true;
                }
                if (buildType.contains("userdebug") && buildDevice.contains("gt-i9195")) {
                    setPreDefinedCameraUri = true;
                }
                if (manufacturer.contains("asus")){
                    setPreDefinedCameraUri = true;
                    rotateXDegrees = 0;

                }

                ///////////////////////////////////////////////////////////////////////////


                dateCameraIntentStarted = new Date();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (setPreDefinedCameraUri) {
                    String filename = System.currentTimeMillis() + ".jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, filename);
                    preDefinedCameraUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, preDefinedCameraUri);
                }
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            } catch (ActivityNotFoundException e) {
                logException(e);
                onCouldNotTakePhoto();
            }
        } else {
            onSdCardNotMounted();
        }
    }


    protected void startGalleryIntent(final double maxPixel) {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                // NOTE: Do NOT SET: intent.putExtra(MediaStore.EXTRA_OUTPUT,
                // cameraPicUri)
                // on Samsung Galaxy S2/S3/.. for the following reasons:
                // 1.) it will break the correct picture orientation
                // 2.) the photo will be stored in two locations (the given path
                // and, additionally, in the MediaStore)
                String manufacturer = android.os.Build.MANUFACTURER
                        .toLowerCase(Locale.ENGLISH);
                Log.i("codemobiles", manufacturer);

                String model = android.os.Build.MODEL
                        .toLowerCase(Locale.ENGLISH);
                String buildType = android.os.Build.TYPE
                        .toLowerCase(Locale.ENGLISH);
                String buildDevice = android.os.Build.DEVICE
                        .toLowerCase(Locale.ENGLISH);
                String buildId = android.os.Build.ID
                        .toLowerCase(Locale.ENGLISH);
                String sdkVersion = android.os.Build.VERSION.RELEASE
                        .toLowerCase(Locale.ENGLISH);
                if (manufacturer.contains("samsung")) { // TESTED
                    rotateXDegrees = 90;
                }else if (manufacturer.contains("asus")){
                    rotateXDegrees = 0;

                }


            } catch (Exception e) {

            }
        }

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                GALLERY_IMAGE_ACTIVITY_REQUEST_CODE);

    }


    /**
     * Receives all activity results and triggers onCameraIntentResult if the
     * requestCode matches.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
                onCameraIntentResult(requestCode, resultCode, intent);
                break;
            }
            case GALLERY_IMAGE_ACTIVITY_REQUEST_CODE: {
                try {
                    photoUri = intent.getData();
                    final String filePath = UploadImageUtils.getImageFilePath(photoUri, this);
                    mPhotoBitMap = BitmapHelper.readBitmap(this, photoUri);

                    if (mPhotoBitMap != null && mMaxPixel != -1) {
                        mPhotoBitMap = BitmapHelper.shrinkBitmap(mPhotoBitMap, mMaxPixel,
                                rotateXDegrees);
                    }
                    onPhotoUriFound(photoUri,mPhotoBitMap, filePath);
                } catch (Exception e) {

                }
                break;

            }
        }
    }


    /**
     * On camera activity result, we try to locate the photo.
     * <p/>
     * <b>Mediastore:</b>
     * First, we try to read the photo being captured from the MediaStore. Using a ContentResolver on the MediaStore content, we retrieve the latest image being taken, as well as its orientation property and its timestamp. If we find an image and it was not taken before the camera intent was called, it is the image we were looking for. Otherwise, we dismiss the result and try one of the following approaches.
     * <b>Intent extra:</b>
     * Second, we try to get an image Uri from intent.getData() of the returning intent. If this is not successful either, we continue with step 3.
     * <b>Default photo Uri:</b>
     * If all of the above mentioned steps did not work, we use the image Uri we passed to the camera activity.
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    protected void onCameraIntentResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Cursor myCursor = null;
            Date dateOfPicture = null;
            try {
                // Create a Cursor to obtain the file Path for the large image
                String[] largeFileProjection = {MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA,
                        MediaStore.Images.ImageColumns.ORIENTATION,
                        MediaStore.Images.ImageColumns.DATE_TAKEN};
                String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
                myCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        largeFileProjection,
                        null, null,
                        largeFileSort);
                myCursor.moveToFirst();
                if (!myCursor.isAfterLast()) {
                    // This will actually give you the file path location of the image.
                    String largeImagePath = myCursor.getString(myCursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    photoUri = Uri.fromFile(new File(largeImagePath));
                    if (photoUri != null) {
                        dateOfPicture = new Date(myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)));
                        if (dateOfPicture != null && dateOfPicture.after(dateCameraIntentStarted)) {
                            rotateXDegrees = myCursor.getInt(myCursor
                                    .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION));
                        } else {
                            photoUri = null;
                        }
                    }
                    if (myCursor.moveToNext() && !myCursor.isAfterLast()) {
                        String largeImagePath3rdLocation = myCursor.getString(myCursor
                                .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                        Date dateOfPicture3rdLocation = new Date(myCursor.getLong(myCursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)));
                        if (dateOfPicture3rdLocation != null && dateOfPicture3rdLocation.after(dateCameraIntentStarted)) {
                            photoUriIn3rdLocation = Uri.fromFile(new File(largeImagePath3rdLocation));
                        }
                    }
                }
            } catch (Exception e) {
                logException(e);
            } finally {
                if (myCursor != null && !myCursor.isClosed()) {
                    myCursor.close();
                }
            }

            if (photoUri == null) {
                try {
                    photoUri = intent.getData();
                } catch (Exception e) {
                }
            }

            if (photoUri == null) {
                photoUri = preDefinedCameraUri;
            }

            try {
                if (photoUri != null && new File(photoUri.getPath()).length() <= 0) {
                    if (preDefinedCameraUri != null) {
                        Uri tempUri = photoUri;
                        photoUri = preDefinedCameraUri;
                        preDefinedCameraUri = tempUri;
                    }
                }
            } catch (Exception e) {
            }

            photoUri = getFileUriFromContentUri(photoUri);
            preDefinedCameraUri = getFileUriFromContentUri(preDefinedCameraUri);
            try {
                if (photoUriIn3rdLocation != null) {
                    if (photoUriIn3rdLocation.equals(photoUri) || photoUriIn3rdLocation.equals(preDefinedCameraUri)) {
                        photoUriIn3rdLocation = null;
                    } else {
                        photoUriIn3rdLocation = getFileUriFromContentUri(photoUriIn3rdLocation);
                    }
                }
            } catch (Exception e) {
            }

            if (photoUri != null) {
                final String filePath = UploadImageUtils.getImageFilePath(photoUri, this);
                mPhotoBitMap = BitmapHelper.readBitmap(this, photoUri);
                mPhotoBitMap = BitmapHelper.readBitmap(this, photoUri);

                if (mPhotoBitMap != null && mMaxPixel != -1) {
                    mPhotoBitMap = BitmapHelper.shrinkBitmap(mPhotoBitMap, mMaxPixel,
                            rotateXDegrees);
                }
                onPhotoUriFound(photoUri,mPhotoBitMap, filePath);
            } else {
                onPhotoUriNotFound();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            onCanceled();
        } else {
            onCanceled();
        }
    }

    /**
     * Being called if the photo could be located. The photo's Uri
     * and its orientation could be retrieved.
     */
    protected void onPhotoUriFound(Uri _photoUri, Bitmap _photoBitMap, final String _filePath) {
        logMessage("Your photo is stored under: " + _photoUri.toString());
    }

    /**
     * Being called if the photo could not be located (Uri == null).
     */
    protected void onPhotoUriNotFound() {
        logMessage("Could not find a photoUri that is != null");
    }

    /**
     * Being called if the camera intent could not be started or something else went wrong.
     */
    protected void onCouldNotTakePhoto() {
        Toast.makeText(getApplicationContext(), "Could not take photo", Toast.LENGTH_LONG).show();
    }

    /**
     * Being called if the SD card (or the internal mass storage respectively) is not mounted.
     */
    protected void onSdCardNotMounted() {
        Toast.makeText(getApplicationContext(), "Could not mount sdcard", Toast.LENGTH_LONG).show();
    }

    /**
     * Being called if the camera intent was canceled.
     */
    protected void onCanceled() {
        logMessage("Camera Intent was canceled");
    }

    /**
     * Logs the passed exception.
     *
     * @param exception
     */
    protected void logException(Exception exception) {
        logMessage(exception.toString());
    }

    /**
     * Logs the passed exception messages.
     *
     * @param exceptionMessage
     */
    protected void logMessage(String exceptionMessage) {
        Log.d(getClass().getName(), exceptionMessage);
    }

    /**
     * Given an Uri that is a content Uri (e.g.
     * content://media/external/images/media/1884) this function returns the
     * respective file Uri, that is e.g. file://media/external/DCIM/abc.jpg
     *
     * @param cameraPicUri
     * @return Uri
     */
    private Uri getFileUriFromContentUri(Uri cameraPicUri) {
        try {
            if (cameraPicUri != null
                    && cameraPicUri.toString().startsWith("content")) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(cameraPicUri, proj, null, null, null);
                cursor.moveToFirst();
                // This will actually give you the file path location of the image.
                String largeImagePath = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                return Uri.fromFile(new File(largeImagePath));
            }
            return cameraPicUri;
        } catch (Exception e) {
            return cameraPicUri;
        }
    }
}
