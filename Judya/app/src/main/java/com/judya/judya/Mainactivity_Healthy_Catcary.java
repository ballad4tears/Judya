package com.judya.judya;


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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * @author Adil Soomro
 *
 */
public class Mainactivity_Healthy_Catcary extends AppCompatActivity {


      protected static final int RESULT_SPEECH = 1;

     // private int count=0;
     ListView listListView;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_food);


        TextView titel = (TextView) findViewById(R.id.texttitel);
        titel.setText("ประเภทอาการ");
        Button fab = (Button) findViewById(R.id.button_add_data);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainactivity_Healthy_Catcary.this, InsertData_MainActivity.class);
                startActivity(intent);

            }
        });

          listListView = (ListView) findViewById(R.id.listview);



    }


    public class CustomListViewAdapter extends BaseAdapter {

        public int getCount() {
            return File_Confix_Data.getOutput_data_Category.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_listview, null);
                holder = new ViewHolder();
                holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
                holder.descTextView = (TextView) convertView.findViewById(R.id.artistsss);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.relativeLayoutthumbnail = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutthumbnail);
                holder.authorImageView = (ImageView) convertView.findViewById(R.id.list_image);
                holder.relativeLayoutthumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();


                        File_Confix_Data.string_id_cat = catID;


                        for (int i = 0; i < File_Confix_Data.getOutput_data_Category.size(); i++) {

                            if (File_Confix_Data.getOutput_data_Category.get(i).getCategory_id().equals(File_Confix_Data.string_id_cat)) {


                                File_Confix_Data.titel = File_Confix_Data.getOutput_data_Category.get(i).getCategory_name();
                            }
                        }


                        Intent intent = new Intent(Mainactivity_Healthy_Catcary.this, MainactivityMain_Healthy_S.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.titleTextView.setText(File_Confix_Data.getOutput_data_Category.get(position).getCategory_name());
            holder.descTextView.setText(File_Confix_Data.getOutput_data_Category.get(position).getCategory_name());
            holder.textView1.setText("เลือก         >>>");


            Glide.with(getApplicationContext()).load(File_Confix_Data.getOutput_data_Category.get(position).getCategory_icon()).into(holder.authorImageView);


            holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, File_Confix_Data.getOutput_data_Category.get(position).getCategory_id());
            return convertView;
        }

        public class ViewHolder {
            TextView titleTextView;
            ImageView authorImageView;
            TextView textView1;
            TextView descTextView;
            RelativeLayout relativeLayoutthumbnail;

        }




    }
		@Override
		public void onBackPressed() {

    		 finish();
		}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
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

                // intent_activity();
            }else{
                showGPSDisabledAlertToUser();
            }

        } else {

            if (isGpsEnabled(this)){
                //    Loadding();

                intent_activity();
                // intent_activity();
            }else{
                showGPSDisabledAlertToUser();
            }


            // retryPopup();
        }
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
    private void intent_activity() {
        Gson gson = new Gson();
        SharedPreferences database_list = getSharedPreferences("DATABASELIST", 0);
        String List_Data = database_list.getString("databaselist", "");
        Debug.out(List_Data);

        List_Data  getOutput_List_Data=   gson.fromJson(List_Data, List_Data.class);
        File_Confix_Data.getOutput_List_Data= getOutput_List_Data.getOutput();







        SharedPreferences database_cat = getSharedPreferences("DATABASELISTCATEGORY", 0);
        String str_cat = database_cat.getString("databaselistcategory", "");
        Debug.out(str_cat);

        List_Data_Cat  category_data=   gson.fromJson(str_cat, List_Data_Cat.class);
        File_Confix_Data.getOutput_data_Category= category_data.getOutput();

        listListView.setAdapter(new CustomListViewAdapter( ));


    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}