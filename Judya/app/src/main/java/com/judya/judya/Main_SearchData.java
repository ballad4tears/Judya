package com.judya.judya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
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

public class Main_SearchData extends AppCompatActivity {
    private ListView listListView;
    private AutoCompleteTextView  searchPal;
    private String countries[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__search);
          listListView = (ListView) findViewById(R.id.listview);

      //  File_Confix_Data.getOutput_HomeDog.get(potition[position]).getName()
        searchPal = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);

        searchPal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                String dataS=(String)parent.getItemAtPosition(position);


                for(int i=0;i<File_Confix_Data.getOutput_HomeDog.size();i++) {

                    if( File_Confix_Data.getOutput_HomeDog.get(i).getName().equals(dataS)){

                        File_Confix_Data.position_id=i;

                    }
                }


                Intent intent = new Intent(Main_SearchData.this, Detail_Dog_Profile.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        File_Confix_Data.image_set=false;
      if(isOnline()){
          GetAllUser();
      }else{
          intent_activity();
      }

    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public class CustomListViewAdapter extends BaseAdapter {

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
          CustomListViewAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_listview, null);
                holder = new Main_SearchData.CustomListViewAdapter.ViewHolder();
                holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
                holder.descTextView = (TextView) convertView.findViewById(R.id.artistsss);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.relativeLayoutthumbnail = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutthumbnail);
                holder.authorImageView = (ImageView) convertView.findViewById(R.id.list_image);
                holder.relativeLayoutthumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();
                        File_Confix_Data.string_id_cat=catID;


                        for(int i=0;i<File_Confix_Data.getOutput_HomeDog.size();i++) {

                            if( File_Confix_Data.getOutput_HomeDog.get(i).getId().equals(File_Confix_Data.string_id_cat)){

                                File_Confix_Data.position_id=i;

                            }
                        }


                        Intent intent = new Intent(Main_SearchData.this, Detail_Dog_Profile.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(holder);

            } else {
                holder = (Main_SearchData.CustomListViewAdapter.ViewHolder) convertView.getTag();
            }
            holder.titleTextView.setText("ชื่อ "+File_Confix_Data.getOutput_HomeDog.get(potition[position]).getName());
            holder.descTextView.setText(File_Confix_Data.getOutput_HomeDog.get(potition[position]).getType5()+"  "+File_Confix_Data.getOutput_HomeDog.get(potition[position]).getType4());
            holder.textView1.setText( File_Confix_Data.getOutput_HomeDog.get(potition[position]).getType3()+"  "+File_Confix_Data.getOutput_HomeDog.get(potition[position]).getType2());

            Glide.with(getApplicationContext()).load(File_Confix_Data.getOutput_HomeDog.get(potition[position]).getImage_name()).into(holder.authorImageView);
            holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, File_Confix_Data.getOutput_HomeDog.get(potition[position]).getId());
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
    private void GetAllUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://projectandroid.top/Emergency/index.php/APIHealthy/getAllUser", new AsyncHttpResponseHandler() {

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

                SharedPreferences settings = getSharedPreferences("DATABASEHOMEDOG", 0);
                SharedPreferences.Editor prefsEd = settings.edit();
                prefsEd.putString("databasehomedong", str);
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
        SharedPreferences database_list = getSharedPreferences("DATABASEHOMEDOG", 0);
        String List_Data = database_list.getString("databasehomedong", "");


        GetHomeDog  getOutput_List_Data=   gson.fromJson(List_Data, GetHomeDog.class);
        File_Confix_Data.getOutput_HomeDog= getOutput_List_Data.getOutput();

        count=0;
  for(int i=0;i<File_Confix_Data.getOutput_HomeDog.size();i++){
      //if(File_Confix_Data.getOutput_HomeDog.get(i).getType1().equals("ประวัติผู้ใช้")&&
        //      File_Confix_Data.getOutput_HomeDog.get(i).getRegisteruser_id().equals(File_Confix_Data.data_list_user.get( File_Confix_Data.position_id_regist).getId())){
          count++;
      //}

  }
        potition=new int[count];
        countries=new String[count];

        int po=0;

        for(int i=0;i<File_Confix_Data.getOutput_HomeDog.size();i++){
           // if(File_Confix_Data.getOutput_HomeDog.get(i).getType1().equals("ประวัติผู้ใช้")&&  File_Confix_Data.getOutput_HomeDog.get(i).getRegisteruser_id().equals(File_Confix_Data.data_list_user.get( File_Confix_Data.position_id_regist).getId())){
                potition[po]=i;
                countries[po]=File_Confix_Data.getOutput_HomeDog.get(i).getName() ;

                po++;

           // }
        }

            ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
            searchPal.setAdapter(adapterS);




         listListView.setAdapter(new  CustomListViewAdapter( ));
    }

int potition[];
int count=0;

}
