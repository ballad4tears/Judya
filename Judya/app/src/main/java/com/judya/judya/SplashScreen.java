package com.judya.judya;//package com.ServiceDesk.ServiceDesk;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class SplashScreen extends AppCompatActivity {
 boolean check_load=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Load_data();
    }

    private void Load_data() {
        // TODO Auto-generated method stub
        if (isOnline()) {


            if (isGpsEnabled(this)){
                //    Loadding();
                Load_List();
                check_load=true;
                // intent_activity();
            }else{
                showGPSDisabledAlertToUser();
            }

        } else {

            if (isGpsEnabled(this)){
                //    Loadding();
                check_load=false;
                intent_activity();
                // intent_activity();
            }else{
                showGPSDisabledAlertToUser();
            }


           // retryPopup();
        }
    }

    private void intent_activity() {
        Gson gson = new Gson();
        SharedPreferences database_list = getSharedPreferences("DATABASELIST", 0);
        String List_Data = database_list.getString("databaselist", "");
        Debug.out(List_Data);

        List_Data  getOutput_List_Data=   gson.fromJson(List_Data, List_Data.class);
        File_Confix_Data.getOutput_List_Data= getOutput_List_Data.getOutput();




//
//        SharedPreferences database_knowledge = getSharedPreferences("DATABASELISTKNOWLESGE", 0);
//        String List_knowledge = database_knowledge.getString("databaselistknowledge", "");
//        Debug.out(List_knowledge);
//
//        Knowledge  getOutput_List_Knowledge=   gson.fromJson(List_knowledge, Knowledge.class);
//        File_Confix_Data.getOutput_List_DataKnowledge= getOutput_List_Knowledge.getOutput();
//
//





        SharedPreferences database_cat = getSharedPreferences("DATABASELISTCATEGORY", 0);
        String str_cat = database_cat.getString("databaselistcategory", "");
        Debug.out(str_cat);

        List_Data_Cat  category_data=   gson.fromJson(str_cat, List_Data_Cat.class);
        File_Confix_Data.getOutput_data_Category= category_data.getOutput();




        displayLocation();

//        Intent intent = new Intent(SplashScreen.this, MainActivity_Home.class);
//        startActivity(intent);
//        finish();


    }


    private void displayLocation() {





//            SharedPreferences database_list_food = getSharedPreferences("DATABASELISTFOOD", 0);
//            String List_Data_food = database_list_food.getString("databaselistfood", "");
//



            Gson gson = new Gson();

//
//            List_Data_food  List_Data_foodS=   gson.fromJson(List_Data_food.toString(), List_Data_food.class);
//            File_Confix_Data.getOutput_data_List_Data_food= List_Data_foodS.getOutput();
//



            Set_hospital();




    }
    private void Set_hospital() {


//
//        SharedPreferences database_list_hospital= getSharedPreferences("DATABASELISTHOSPITAL", 0);
//        String List_Data_hospital = database_list_hospital.getString("databaselisthospital", "");
//
//
//        Gson gson = new Gson();
//
//        List_Data_hospital  list_Data_hospitalS=   gson.fromJson(List_Data_hospital.toString(), List_Data_hospital.class);
//        File_Confix_Data.getOutput_data_List_Data_hospital= list_Data_hospitalS.getOutput();


        final int finalRun = 500;
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(finalRun);
                } catch (InterruptedException e) { }
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();

            }
        }).start();
    }
//Map-Mesure Travel MainActivity_ManinMenu
private void Load_List() {
    AsyncHttpClient client = new AsyncHttpClient();
    client.get("http://projectandroid.top/Emergency/index.php/APIHealthy", new AsyncHttpResponseHandler() {

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


            SharedPreferences settings = getSharedPreferences("DATABASELIST", 0);
            SharedPreferences.Editor prefsEd = settings.edit();
            prefsEd.putString("databaselist", str);
            prefsEd.commit();
            LoadCategory();
         //   Load_List_Food();

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
//    private void Load_List_Food() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getDataFood", new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                // called before request is started
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                String str = null;
//                try {
//                    str = new String(response, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//
//                SharedPreferences settings = getSharedPreferences("DATABASELISTFOOD", 0);
//                SharedPreferences.Editor prefsEd = settings.edit();
//                prefsEd.putString("databaselistfood", str);
//                prefsEd.commit();
//
//                Load_List_Hospital();
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried http://projectandroid.top/Travel
//            }
//        });
//    }
//    private void Load_List_Hospital() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getDataHospital", new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                // called before request is started
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                String str = null;
//                try {
//                    str = new String(response, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//
//                SharedPreferences settings = getSharedPreferences("DATABASELISTHOSPITAL", 0);
//                SharedPreferences.Editor prefsEd = settings.edit();
//                prefsEd.putString("databaselisthospital", str);
//                prefsEd.commit();
//
//                LoadCategory();
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried http://projectandroid.top/Travel
//            }
//        });
//    }
   //
    private void LoadCategory() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getDataSearchCategory", new AsyncHttpResponseHandler() {

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

                SharedPreferences settings = getSharedPreferences("DATABASELISTCATEGORY", 0);
                SharedPreferences.Editor prefsEd = settings.edit();
                prefsEd.putString("databaselistcategory", str);
                prefsEd.commit();
                intent_activity();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
//    private void LoadKnowledge() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getDataKnowledge", new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                // called before request is started
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                String str = null;
//                try {
//                    str = new String(response, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                SharedPreferences settings = getSharedPreferences("DATABASELISTKNOWLESGE", 0);
//                SharedPreferences.Editor prefsEd = settings.edit();
//                prefsEd.putString("databaselistknowledge", str);
//                prefsEd.commit();
//                intent_activity();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//            }
//        });
//    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    @Override
    protected void onResume() {
      //  CMMapUtil.turnLocationTrackingOn(SplashScreen.this);

        super.onResume();
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("deprecation")
    public static boolean isGpsEnabled(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            String providers = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (TextUtils.isEmpty(providers)) {
                return false;
            }
            return providers.contains(LocationManager.GPS_PROVIDER);
        } else {
            final int locationMode;
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            switch (locationMode) {

                case Settings.Secure.LOCATION_MODE_HIGH_ACCURACY:
                case Settings.Secure.LOCATION_MODE_SENSORS_ONLY:
                    return true;
                case Settings.Secure.LOCATION_MODE_BATTERY_SAVING:
                case Settings.Secure.LOCATION_MODE_OFF:
                default:
                    return false;
            }
        }
    }
    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS If Android KITKAT Settings LOCATION MODE",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){

                                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);

                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
