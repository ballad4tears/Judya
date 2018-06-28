package com.judya.judya;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail_Dog_Profile extends AppCompatActivity {
    ProgressDialog pDialog;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_profiledog);
        // Set up the input

        ImageView image_set_view = (ImageView)  findViewById(R.id.image_set_view);

        Glide.with(getApplicationContext()).load(File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getImage_name()).into(image_set_view);

        TextView texttitel = (TextView)  findViewById(R.id.texttitel);
        texttitel.setText("ชื่อ  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getName() );
        TextView data11 = (TextView)  findViewById(R.id.data11);
        data11.setText("เพศ   : "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType2()
        +"\n\nโรคประจำตัว  :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType3()

                +"\n\nเคยแพ้ยา :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType4()
                +"\n\nอายุ :  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType5()
                +"\n\nหมายเลขบัตรประชาชน*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType6()
                +"\n\nเบอร์โทร*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType7()
                +"\n\nที่อยู่*  "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType8()
                +"\n\nรายละเอียด "+File_Confix_Data.getOutput_HomeDog.get(File_Confix_Data.position_id).getType9());


          btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setVisibility(View.GONE);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmit.setVisibility(View.GONE);







            }


        });
    }


}
