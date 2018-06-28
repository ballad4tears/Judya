package com.judya.judya;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;


public class Register extends Activity {
	ProgressDialog pDialog ;
	private Bitmap bitmapSave=null;
	public static final int REQUEST_GALLERY = 1;
	static final int DATE_DIALOG_ID = 999;
 

	private EditText text1;
	private EditText text2;
	private EditText text3;
	private EditText text4;
	private EditText text5;
	private EditText text6;


	private Button btnCancel,btnSubmit;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main_register121);
	    

	    text1 = (EditText) findViewById(R.id.text1);
	    text2 = (EditText) findViewById(R.id.text2);
	    text3 = (EditText) findViewById(R.id.text3);
	    text4 = (EditText) findViewById(R.id.text4);
	    text5 = (EditText) findViewById(R.id.text5);
	    text6= (EditText) findViewById(R.id.text6);






		btnSubmit= (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				  pDialog = new ProgressDialog(Register.this);
			        pDialog.setMessage("Loading ....");
			        pDialog.setIndeterminate(false);
			        pDialog.setCancelable(false);
			        pDialog.show();

                 AsyncHttpClient client = new AsyncHttpClient();

                 RequestParams params = new RequestParams();

                     params.put("username", text1.getText().toString());
                     params.put("password", text2.getText().toString());
                     params.put("name", text3.getText().toString());
                     params.put("lastname", text4.getText().toString());
                     params.put("address", text5.getText().toString());
                     params.put("tel", text6.getText().toString());



                     client.post("http://projectandroid.top/Emergency/index.php/APIHealthy/add_re", params, new AsyncHttpResponseHandler() {
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

         					 Load1();

         					Log.i("", str);
                             Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                         }

                     });


			}
		});

		btnCancel= (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				text1.setText(" ");
				text2.setText(" ");
				text3.setText(" ");
				text4.setText(" ");
				text5.setText(" ");
				text6.setText(" ");


			}
		});
	}
 

 
	@Override
	public void onBackPressed() {
		Register.this.finish();
		   Intent i = new Intent(Register.this, Login.class);
           startActivity(i);
       // overridePendingTransition(R.anim.right_to_center, R.anim.center_to_right);         
    }


	private void Load1() {
		// TODO Auto-generated method stub
		 AsyncHttpClient client = new AsyncHttpClient();
		 client.setTimeout(120000);
		 client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getAll", new AsyncHttpResponseHandler() {
	 
			
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
				
				   SharedPreferences Categorysettings = getSharedPreferences("USER", 0);
			       SharedPreferences.Editor prefsEdCategory = Categorysettings.edit();
			       prefsEdCategory.putString("user", str);
			       prefsEdCategory.commit();

			  	   pDialog.cancel();
                 
			    Intent intent = new Intent(Register.this, Login.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);
				   	
			       
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
			 
			}
			
		 });
	}
}
