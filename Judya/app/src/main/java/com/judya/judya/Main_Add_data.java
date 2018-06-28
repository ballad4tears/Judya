package com.judya.judya;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main_Add_data extends AppCompatActivity {
    private ListView listListView;
    private Databaseregister register;
    ArrayList<HashMap<String, String>> Select_Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__add_data);
          listListView = (ListView) findViewById(R.id.listview);


        Button fab = (Button) findViewById(R.id.button_add_data);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Add_data.this, Main_Add_data_Register.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        register=new Databaseregister(this);
       Select_Data=  register.SelectAllData();
         if(Select_Data.size()>0){
             listListView.setAdapter(new Main_Add_data.CustomListViewAdapter( ));
         }

    }


    public class CustomListViewAdapter extends BaseAdapter {

        public int getCount() {
            return Select_Data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            Main_Add_data.CustomListViewAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_listview, null);
                holder = new Main_Add_data.CustomListViewAdapter.ViewHolder();
                holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
                holder.descTextView = (TextView) convertView.findViewById(R.id.artistsss);
                holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
                holder.relativeLayoutthumbnail = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutthumbnail);
                holder.authorImageView = (ImageView) convertView.findViewById(R.id.list_image);
                holder.relativeLayoutthumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                      final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();
                       File_Confix_Data.Code=catID;

                        Intent intent = new Intent(Main_Add_data.this, TabSample_ALL_REGISTER.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(holder);

            } else {
                holder = (Main_Add_data.CustomListViewAdapter.ViewHolder) convertView.getTag();
            }
            holder.titleTextView.setText(Select_Data.get(position).get("col1"));
            holder.descTextView.setText(Select_Data.get(position).get("col2"));
            holder.textView1.setText( Select_Data.get(position).get("col3"));


            File file= new File(Environment.getExternalStorageDirectory(),Select_Data.get(position).get("col8"));
            Bitmap bitmapImage = BitmapFactory.decodeFile(file.getAbsolutePath());


            Bitmap bmp = BitmapFactory.decodeFile(Select_Data.get(position).get("col8"));
            Drawable drawableS = new BitmapDrawable(getResources(), bitmapImage);
            Log.d("imageiconsStoragePath",""+Select_Data.get(position).get("col8"));
            holder.authorImageView.setBackground(drawableS);




            holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, Select_Data.get(position).get("Code"));
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

}
