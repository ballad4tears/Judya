package com.judya.judya;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Main_Animal extends AppCompatActivity {
    private ListView listListView;
    private Database_Data  dataall;
    ArrayList<HashMap<String, String>> Select_Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__add_data_data);
          listListView = (ListView) findViewById(R.id.listview);


        Button fab = (Button) findViewById(R.id.button_add_data);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main_Animal.this, Mainactivity_Healthy_Catcary.class);
                startActivity(intent);

            }
        });


    }
    private int[] myIntArray ;
    private int count=0;
    @Override
    protected void onStart() {
        super.onStart();
        count=0;
         dataall=new Database_Data(this);
         Select_Data=  dataall.SelectAllData();

         if(Select_Data.size()>0){
             for(int i=Select_Data.size()-1;i>=0 ;i--){
                 if(Select_Data.get(i).get("col7").equals(File_Confix_Data.Code)){
                     count++;
                 }
             }
             myIntArray = new int[count];
             int y=0;
             for(int i=Select_Data.size()-1;i>=0 ;i--){
                 if(Select_Data.get(i).get("col7").equals(File_Confix_Data.Code)){
                     myIntArray[y]=i;
                     y++;
                 }
             }
          if(count>0){
              listListView.setAdapter(new Main_Animal.CustomListViewAdapter( ));
          }

         }

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
            Main_Animal.CustomListViewAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_listview, null);
                holder = new Main_Animal.CustomListViewAdapter.ViewHolder();
                holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
                holder.descTextView = (TextView) convertView.findViewById(R.id.artistsss);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.relativeLayoutthumbnail = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutthumbnail);
                holder.authorImageView = (ImageView) convertView.findViewById(R.id.list_image);
                holder.relativeLayoutthumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();

                        File_Confix_Data.Code_data=catID;
                        for(int i=Select_Data.size()-1;i>=0 ;i--){
                            if(Select_Data.get(i).get("Code").equals(File_Confix_Data.Code_data)){
                                File_Confix_Data.position_id=   Integer.parseInt( Select_Data.get(i).get("col6"));
                            }
                        }

                        Intent intent = new Intent(Main_Animal.this, TabSample_Addata_detail.class);
                        startActivity(intent);

                    }
                });

                convertView.setTag(holder);

            } else {
                holder = (Main_Animal.CustomListViewAdapter.ViewHolder) convertView.getTag();
            }

            holder.titleTextView.setText(Select_Data.get(myIntArray[position]).get("col1"));
            holder.descTextView.setText(Select_Data.get(myIntArray[position]).get("col2"));
            holder.textView1.setText( Select_Data.get(myIntArray[position]).get("col3"));



            Bitmap bmpS = StringToBitMap (Select_Data.get(myIntArray[position]).get("col8"));//BitmapFactory.decodeFile(Select_Data.get(myIntArray[position]).get("col8"));
                   Log.d("ddadadsa", Select_Data.get(myIntArray[position]).get("col8"));

            holder.authorImageView.setImageBitmap( bmpS);


            holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, Select_Data.get(myIntArray[position]).get("Code"));
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
