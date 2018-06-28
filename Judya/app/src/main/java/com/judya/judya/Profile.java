package com.judya.judya;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Profile extends AppCompatActivity {
    TextView imageView1;
    private Bitmap bitmapSave=null;
    public static final int REQUEST_GALLERY = 1;
    private String fname ;
    private Uri part;

    String filePath="";
    private Databaseregister register;
    private String[] SelectDataCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);
        // Set up the input
        register =new Databaseregister(this);

        final EditText text1 = (EditText)   findViewById(R.id.text1);
        final EditText text2 = (EditText)   findViewById(R.id.text2);
        final EditText text3 = (EditText)   findViewById(R.id.text3);
        final EditText text4 = (EditText)   findViewById(R.id.text4);
        final EditText text5 = (EditText)   findViewById(R.id.text5);
        final EditText text6 = (EditText)   findViewById(R.id.text6);
        final EditText text7 = (EditText)   findViewById(R.id.text7);

        SelectDataCode=      register.SelectDataCode(File_Confix_Data.Code);
        text1.setText(SelectDataCode[1]);
        text2.setText(SelectDataCode[2]);
        text3.setText(SelectDataCode[3]);
        text4.setText(SelectDataCode[4]);
        text5.setText(SelectDataCode[5]);
        text6.setText(SelectDataCode[6]);
        text7.setText(SelectDataCode[7]);


        imageView1 = (TextView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GALLERY);
            }
        });


        File file= new File(Environment.getExternalStorageDirectory(),SelectDataCode[8]);
        Bitmap bitmapImage = BitmapFactory.decodeFile(file.getAbsolutePath());

        filePath=SelectDataCode[8];
        Bitmap bmp = BitmapFactory.decodeFile(SelectDataCode[8]);
        Drawable drawableS = new BitmapDrawable(getResources(), bitmapImage);
        imageView1.setBackground(drawableS);


        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setText("แก้ไข");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().equals("")||text2.getText().toString().equals("")||
                 filePath.equals("")){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                }else{

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());
                    register.UpdateData(File_Confix_Data.Code,text1.getText().toString(),text2.getText().toString()
                            ,text3.getText().toString(),text4.getText().toString()
                            ,text4.getText().toString(),text6.getText().toString()
                            ,text7.getText().toString(),filePath,currentDateandTime);
                    Toast.makeText(getApplicationContext(), "แก้ไขเรียบร้อย", Toast.LENGTH_LONG).show();

                }
            }
        });

        Button btnCancel= (Button) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.GONE);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            part = data.getData();

            try {
                bitmapSave = MediaStore.Images.Media.getBitmap(this.getContentResolver(), part);
                bitmapSave= Bitmap.createScaledBitmap(bitmapSave, 512, 512, false);
                BitmapDrawable bdrawable = new BitmapDrawable(bitmapSave);
                imageView1.setBackgroundDrawable(bdrawable);

                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                fname = "Image-"+ n +".jpg";
                storeImage(bitmapSave,fname);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean storeImage(Bitmap imageData, String filename) {
        //get path to external storage (SD card)
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/myAppDirs/myImagesLocation/";
        File sdIconStorageDir = new File(iconsStoragePath);
        sdIconStorageDir.mkdirs();

        try {
              filePath = sdIconStorageDir.toString() + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            imageData.compress(Bitmap.CompressFormat.PNG, 90, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }


        return true;
    }

}
