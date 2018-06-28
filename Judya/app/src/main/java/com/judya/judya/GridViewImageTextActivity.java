package com.judya.judya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewImageTextActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "เพิ่มข้อมูลผู้ป่วย", "ตรวจต่อ", "ค้นหาข้อมูลผู้ป่วย", "ตั้งค่า" ,


    } ;
    int[] gridViewImageId = {
            R.drawable.add_user, R.drawable.edit_user, R.drawable.search_user, R.drawable.settingg

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_image_text_example);


        TextView   txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtTitle.setText( File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getName()+
                " "+File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getLastname());

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(GridViewImageTextActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
             if(i==0){
                 Intent ddd = new Intent(GridViewImageTextActivity.this, Main_Add_data_Register_Profile.class);
                 startActivity(ddd);
             }else if(i==1){
                 Intent ddd = new Intent(GridViewImageTextActivity.this, Main_Add_data_Dog_In.class);
                 startActivity(ddd);
             }else if(i==2){
                 Intent ddd = new Intent(GridViewImageTextActivity.this, Main_SearchData.class);
                 startActivity(ddd);
             }else if(i==3){
              finish();
             }else if(i==4){
//                 Intent ddd = new Intent(GridViewImageTextActivity.this, Mainactivity_Food.class);
//                 startActivity(ddd);
             }else  {
//
             }


//                addTab("ข้อมูลแพทย์", R.mipmap.ic_launcher_location, Mainactivity_Food.class);
//

//
//                addTab("ข้อมูลผู้ดูแล", R.mipmap.ic_launcher_person, Mainactivity_Knowledge.class);
              //  Toast.makeText(GridViewImageTextActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });

    }
}