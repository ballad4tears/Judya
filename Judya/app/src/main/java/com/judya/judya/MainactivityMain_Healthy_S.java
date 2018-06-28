package com.judya.judya;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @author Adil Soomro
 *
 */
public class MainactivityMain_Healthy_S extends AppCompatActivity {


	 protected static final int RESULT_SPEECH = 1;
   private int[] myIntArray ;
    private int count=0;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_food);


        TextView titel = (TextView) findViewById(R.id.texttitel);
        titel.setText(File_Confix_Data.titel);




        for(int i=0;i<File_Confix_Data.getOutput_List_Data.size();i++) {

           if(File_Confix_Data.getOutput_List_Data.get(i).getCategory_id().equals(File_Confix_Data.string_id_cat)){

                count++;
             }
        }
        myIntArray = new int[count];
        int y=0;
        for(int i=0;i<File_Confix_Data.getOutput_List_Data.size();i++) {
            if(File_Confix_Data.getOutput_List_Data.get(i).getCategory_id().equals(File_Confix_Data.string_id_cat)){
                myIntArray[y]=i;
                y++;
             }
        }

        ListView listListView = (ListView) findViewById(R.id.listview);
        listListView.setAdapter(new CustomListViewAdapter( ));

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


                        //   Toast.makeText(MainactivityMain_Healthy_S.this,  File_Confix_Data.getOutput_data_List.get(myIntArray[position]).getRestaurant_name()+"  "
                        //+ File_Confix_Data.getOutput_data_List.get(myIntArray[position]).getRestaurant_id(), Toast.LENGTH_SHORT).show();


                        final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();
                        File_Confix_Data.string_id_data=catID;
                        // Toast.makeText(MainactivityMain_Healthy_S.this, File_Confix_Data.string_id_data, Toast.LENGTH_SHORT).show();


                        for(int i=0;i<File_Confix_Data.getOutput_List_Data.size();i++) {

                            if(File_Confix_Data.getOutput_List_Data.get(i).getHealthy_id().equals(File_Confix_Data.string_id_data)){

                                File_Confix_Data.position_id=i;
                            }
                        }
                        //Toast.makeText(MainactivityMain_Healthy_S.this, File_Confix_Data.string_id_data+"  "+File_Confix_Data.position_id, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainactivityMain_Healthy_S.this, TabSample_Addata.class);
                        startActivity(intent);
                    }
                });

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.titleTextView.setText(File_Confix_Data.getOutput_List_Data.get( myIntArray [position ]).getHealthy_name());
            holder.descTextView.setText(File_Confix_Data.getOutput_List_Data.get( myIntArray [position ] ).getHealthy_detail());
            holder.textView1.setText( File_Confix_Data.getOutput_List_Data.get( myIntArray [position ] ).getCategory_name());


            if(File_Confix_Data.getOutput_List_Data.get( myIntArray [position ] ).getImage_product().size()>0){

                Glide.with(getApplicationContext()).load(File_Confix_Data.getOutput_List_Data.get(  myIntArray [position ] ).getImage_product().get(0)).into(holder.authorImageView);
            }


            holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, File_Confix_Data.getOutput_List_Data.get(myIntArray [position ]).getHealthy_id());
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
}