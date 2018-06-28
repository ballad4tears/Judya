package com.judya.judya;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {



	final private static int DIALOG_LOGIN = 1;



	 EditText User,Passpord;
	// private ProgressDialog progressDialog;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.login);
            User = (EditText)findViewById(R.id.username_edtext);
            Passpord = (EditText)findViewById(R.id.passwd_edtext);





			Button buttonEnter = (Button)findViewById(R.id.login_button);
			buttonEnter.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					if(User.getText().toString()==null||User.getText().toString().equals("")
							||Passpord.getText().toString()==null||Passpord.getText().toString().equals("")){
						Toast.makeText(getApplicationContext(), "กรุณากรอก user password", Toast.LENGTH_LONG).show();

					}else{
						LoginPass();
						
					}
					  
				}

			
			});
			
			Button buttonEnterregister = (Button)findViewById(R.id.regist);
			buttonEnterregister.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					 Intent i = new Intent(Login.this, Register.class);
			           startActivity(i);	 
					  
				}
			});
			Button addmin = (Button)findViewById(R.id.addmin);
			addmin.setVisibility(View.GONE);
			addmin.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {



				}


			});

		}

	@Override
	protected void onStart() {
		super.onStart();
    if(isOnline()){
		Load_USER();
	}


	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	private void intent_activity() {




		Gson gson = new Gson();

		SharedPreferences database_list = getSharedPreferences("USER", 0);
		String str = database_list.getString("user", "");
		Debug.out(str);



		List_User  data_list_user=   gson.fromJson(str, List_User.class);
		File_Confix_Data.data_list_user= data_list_user.getOutput();


	}


	private void Load_USER() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getAll", new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				// called before request is started
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				// called when response HTTP status is "200 OK"
				String str = null;
				try {
					str = new String(response, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SharedPreferences settings = getSharedPreferences("USER", 0);
				SharedPreferences.Editor prefsEd = settings.edit();
				prefsEd.putString("user", str);
				prefsEd.commit();

				intent_activity();

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
			}

			@Override
			public void onRetry(int retryNo) {
				// called when request is retried http://projectandroid.top/Travel
			}
		});
	}
	private void LoginPass() {
			// TODO Auto-generated method stub

			String username=User.getText().toString();
			String password=Passpord.getText().toString();

            boolean check_user_login=false;
			for(int i=0;i<File_Confix_Data.data_list_user.size();i++){
				if(File_Confix_Data.data_list_user.get(i).getUsername().equals(username)&&File_Confix_Data.data_list_user.get(i).getPassword().equals(password)){
					check_user_login=true;
					File_Confix_Data.position_id_regist=i;


				}

			}

			if(check_user_login){

					 Intent i = new Intent(Login.this, GridViewImageTextActivity.class);
					 startActivity(i);


			}else{
				Toast.makeText(getApplicationContext(), "User or Passpord  ผิด", Toast.LENGTH_LONG).show();
			}

		}
		@Override
		public void onBackPressed() {
			Intent intent = new Intent(Intent.ACTION_MAIN);
    		intent.addCategory(Intent.CATEGORY_HOME);
    		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity(intent);  
	         Login.this.finish();
		}






	}
