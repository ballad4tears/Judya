package com.judya.judya;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Main_Add_data_ShowAnimal extends AppCompatActivity {
    ImageView imageView1;
    private Bitmap bitmapSave=null;
    public static final int REQUEST_GALLERY = 1;
    private String fname ;
    private Uri part;


    private Database_Data Data_all;
    private String[] SelectDataCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal_aaaa);
        // Set up the input
        Data_all =new Database_Data(this);

        final TextView text1 = (TextView)   findViewById(R.id.text1);
        final TextView text2 = (TextView)   findViewById(R.id.text2);
        final TextView text3 = (TextView)   findViewById(R.id.text3);
        final TextView text4 = (TextView)   findViewById(R.id.text4);
        final TextView text5 = (TextView)   findViewById(R.id.text5);
        final TextView text6 = (TextView)   findViewById(R.id.text6);
        SelectDataCode= Data_all.SelectDataCode(File_Confix_Data.Code_data);
        text1.setText(SelectDataCode[1]);
        text2.setText(SelectDataCode[2]);
        text3.setText(SelectDataCode[3]);
        text4.setText(SelectDataCode[4]);
        text5.setText(SelectDataCode[5]);
        text6.setText(SelectDataCode[6]);


        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        File file= new File(Environment.getExternalStorageDirectory(),SelectDataCode[8]);
        Bitmap bitmapImage = BitmapFactory.decodeFile(file.getAbsolutePath());

        filePath=SelectDataCode[8];
        Bitmap bmp =StringToBitMap(SelectDataCode[8]);// BitmapFactory.decodeFile(SelectDataCode[8]);
        imageView1.setImageBitmap( bmp);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setText("กลับ");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                if(text1.getText().toString().equals("")||text2.getText().toString().equals("") || filePath.equals("")){
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
//                }else{
//
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//                    String currentDateandTime = sdf.format(new Date());
//                    Data_all.UpdateData(File_Confix_Data.Code_data,text1.getText().toString(),text2.getText().toString()
//                            ,text3.getText().toString(),text4.getText().toString()
//                            ,text4.getText().toString(),text6.getText().toString()
//                            ,File_Confix_Data.Code,filePath,currentDateandTime);
//
//                    Toast.makeText(getApplicationContext(), "แก้ไขเรียบร้อย", Toast.LENGTH_LONG).show();
//                }
            }
        });

        Button btnCancel= (Button) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.GONE);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
    }


    String filePath="";
    @Override
    public void onBackPressed() {

        finish();
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
