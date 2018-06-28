package com.judya.judya;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main_Add_data_Animal extends AppCompatActivity {
    ImageView imageView1;
    private Bitmap bitmapSave=null;
    public static final int REQUEST_GALLERY = 1;
    private String fname ;
    private Uri part;


    private Database_Data Data_all;
    private Database_Data_sound  sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        // Set up the input
        Data_all =new Database_Data(this);
        sound =new Database_Data_sound(this);
        final EditText text1 = (EditText)   findViewById(R.id.text1);
        final EditText text2 = (EditText)   findViewById(R.id.text2);
        final EditText text3 = (EditText)   findViewById(R.id.text3);
        final EditText text4 = (EditText)   findViewById(R.id.text4);
        final EditText text5 = (EditText)   findViewById(R.id.text5);
        final EditText text6 = (EditText)   findViewById(R.id.text6);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy , HH:mm");
        String dateSS = df.format(Calendar.getInstance().getTime());
        text6.setText(dateSS);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                File_Confix_Data.image_set=false;
                Intent intent = new Intent(Main_Add_data_Animal.this, MainActivityUploadFile.class);
                startActivity(intent);
            }
        });
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().equals("")||text2.getText().toString().equals("")|| filePath.equals("") ){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                }else{

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());

                    Data_all.InsertData(null,
                            text1.getText().toString(),text2.getText().toString()
                            ,text3.getText().toString(),text4.getText().toString()
                            ,text4.getText().toString(),Integer.toString(File_Confix_Data.position_id)
                            ,File_Confix_Data.Code,filePath,currentDateandTime);

                    File_Confix_Data.position_id_s= Data_all.SelectAllData().get(Data_all.SelectAllData().size()-1).get("Code");



                    sound.InsertData(null,
                            File_Confix_Data.position_id_s
                    ,  "",
                            currentDateandTime
                    , currentDateandTime
                    ,  currentDateandTime
                    ,  currentDateandTime
                    , currentDateandTime,currentDateandTime,currentDateandTime);
                    text1.setText(" ");
                    text2.setText(" ");
                    text3.setText(" ");
                    text4.setText(" ");
                    text5.setText(" ");
                    text6.setText(" ");

                    imageView1.setBackgroundResource(R.drawable.png);
                    Toast.makeText(getApplicationContext(), "บันทึกเรียบร้อย", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnCancel= (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text1.setText(" ");
                text2.setText(" ");
                text3.setText(" ");
                text4.setText(" ");
                text5.setText(" ");
                text6.setText(" ");

                imageView1.setBackgroundResource(R.drawable.png);
            }
        });
    }


    String filePath="";
    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(File_Confix_Data.image_set){


            filePath= BitMapToString(File_Confix_Data.mPhotoBitMap);
            imageView1.setImageBitmap( File_Confix_Data.mPhotoBitMap);
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
