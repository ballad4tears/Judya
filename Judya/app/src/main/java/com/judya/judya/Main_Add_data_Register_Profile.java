package com.judya.judya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.codemobiles.cmuploadimage.util.UploadImageUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class Main_Add_data_Register_Profile extends AppCompatActivity {
    ImageView imageView1;
    private Bitmap bitmapSave=null;
    public static final int REQUEST_GALLERY = 1;
    private String fname ;
    private Uri part;

    ProgressDialog pDialog;
    public String mUploadedFileName="";


    Spinner spinnerDropDown2;
    Spinner spinnerDropDown3;
    Spinner spinnerDropDown4;

    String[] data2 = {
            "ชาย",
            "หญิง"
    };
    String[] data3 = {
            "มี",
            "ไม่มี"
    };
    String[] data4= {
            "เคย",
            "ไม่เคย"
    };


    String input2="";
    String input3="";
    String input4="";
    String input5="";
    String input6="";
    String input7="";
    String input8="";
    String input9="";
    String input10="";
    String input11="";
    String input12="";
      EditText text191,text1,text2,text3,text4,text11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        // Set up the input


            text1 = (EditText)   findViewById(R.id.text1);
            text2 = (EditText)   findViewById(R.id.text2);
             text3 = (EditText)   findViewById(R.id.text3);
             text4 = (EditText)   findViewById(R.id.text4);
             text191 = (EditText)   findViewById(R.id.text191);
            text11 = (EditText)   findViewById(R.id.text11);






        LinearLayout viwegone = (LinearLayout)   findViewById(R.id.viwegone);
        viwegone.setVisibility(View.GONE);



        spinnerDropDown2 =(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,data2);
        spinnerDropDown2.setAdapter(adapter2);
        spinnerDropDown2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid=spinnerDropDown2.getSelectedItemPosition();
                input2=data2[sid];
              //  Toast.makeText(getBaseContext(), "You have selected City : " + data2[sid], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerDropDown3 =(Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,data3);
        spinnerDropDown3.setAdapter(adapter3);
        spinnerDropDown3.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid=spinnerDropDown3.getSelectedItemPosition();
                input3=data3[sid];
              //  Toast.makeText(getBaseContext(), "You have selected City : " + data3[sid], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinnerDropDown4 =(Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,data4);
        spinnerDropDown4.setAdapter(adapter4);
        spinnerDropDown4.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid=spinnerDropDown4.getSelectedItemPosition();
                input4=data4[sid];
               // Toast.makeText(getBaseContext(), "You have selected City : " + data4[sid], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

//        spinnerDropDown5 =(Spinner)findViewById(R.id.spinner5);
//        ArrayAdapter<String> adapter5= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,data5);
//        spinnerDropDown5.setAdapter(adapter5);
//        spinnerDropDown5.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                // Get select item
//                int sid=spinnerDropDown5.getSelectedItemPosition();
//                input5=data5[sid];
//               // Toast.makeText(getBaseContext(), "You have selected City : " + data5[sid], Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                File_Confix_Data.image_set=false;
                Intent intent = new Intent(Main_Add_data_Register_Profile.this, MainActivityUploadFile.class);
                startActivity(intent);
            }
        });
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().equals("")||text2.getText().toString().equals("")||mUploadedFileName.equals("") ){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();
                }else{
                    pDialog = new ProgressDialog(Main_Add_data_Register_Profile.this);
                    pDialog.setMessage("Loading ....");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                SaveData();





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

                imageView1.setBackgroundResource(R.drawable.png);
            }
        });
    }

    private void SaveData() {
        input5=text11.getText().toString();
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        params.put("type1", "ประวัติผู้ใช้");
        params.put("type2", input2);
        params.put("type3", input3);
        params.put("type4", input4);
        params.put("type5", input5);
        params.put("type6", text1.getText().toString());
        params.put("type7", text2.getText().toString());
        params.put("type8", text3.getText().toString());
        params.put("type9", text4.getText().toString());
        params.put("latitude",  File_Confix_Data.latitude);
        params.put("longitude", File_Confix_Data.longitude);
        params.put("name", text191.getText().toString());
        params.put("registeruser_id",  File_Confix_Data.data_list_user.get( File_Confix_Data.position_id_regist).getId());

        params.put("image_name", mUploadedFileName);
        client.post("http://projectandroid.top/Emergency/index.php/APIHealthy/add_register", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }
            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                // TODO Auto-generated method stub
                pDialog.cancel();


            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                String str = null;
                try {
                    str = new String(arg2, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pDialog.cancel();
                text1.setText(" ");
                text2.setText(" ");
                text3.setText(" ");
                text4.setText(" ");

                imageView1.setBackgroundResource(R.drawable.png);
                Toast.makeText(getApplicationContext(), "บันทึกเรียบร้อย", Toast.LENGTH_LONG).show();


            }

        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(File_Confix_Data.image_set){
            new UploadImageTask().execute("http://projectandroid.top/Emergency/uploads/up.php");
            imageView1.setImageBitmap( File_Confix_Data.mPhotoBitMap);
        }

    }

        public class UploadImageTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Uploading...", Toast.LENGTH_SHORT).show();
        }

        protected String doInBackground(String... params) {

            String url = params[0];
            mUploadedFileName = UploadImageUtils.getRandomFileName();
            final String result = UploadImageUtils.uploadFile(mUploadedFileName, url, File_Confix_Data.mPhotoBitMap);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }


    }

}
