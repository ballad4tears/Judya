package com.judya.judya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_Dog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_k);
        // Set up the input

        ImageView image_set_view = (ImageView)  findViewById(R.id.image_set_view);
        image_set_view.setVisibility(View.GONE);


        TextView texttitel = (TextView)  findViewById(R.id.texttitel);
        texttitel.setText("ชื่อ - นามสกุล  : "+ File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getName() );
        TextView data11 = (TextView)  findViewById(R.id.data11);
        data11.setText(  File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getLastname()
                +"\n\nTel :  "+File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getTel()

                +"\n\n  :  "+File_Confix_Data.data_list_user.get(File_Confix_Data.position_id_regist).getAddress()
                );




//        ImageView image_set_view = (ImageView)  findViewById(R.id.image_set_view);
//
//        Glide.with(getApplicationContext()).load(File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getImage_name()).into(image_set_view);
//
//        TextView texttitel = (TextView)  findViewById(R.id.texttitel);
//        texttitel.setText("ประเภท "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType1());
//        TextView data11 = (TextView)  findViewById(R.id.data11);
//        data11.setText("ชื่อ - นามสกุล  : "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getName()
//                +"\n\nเพศ :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType2()
//                //+"\n\nเพศ  :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType3()
//
//                +"\n\nโรคประจำตัว :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType4()
//                +"\n\nอายุ :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType5()
//                +"\n\nส่วนสูง*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType6()
//                +"\n\nสัมภาระติดตัว*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType7()
//                +"\n\nรายละเอียดเพิ่มเติม*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType8()
//                +"\n\nรายละเอียดผู้แจ้ง  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType9());
    }
}
