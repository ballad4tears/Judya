package com.judya.judya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class Detail_data_healthy extends Fragment {
    TextView texttitel,contect,addres   ;
    TextView data11  ,data1,data2,data3,data4 ;
    ImageView image_set_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v = inflater.inflate(R.layout.detail_healthy, container, false);
        texttitel = (TextView) v.findViewById(R.id.texttitel);
        contect = (TextView) v.findViewById(R.id.contect);
        addres = (TextView) v.findViewById(R.id.addres);

              data11 = (TextView) v.findViewById(R.id.data11);
              image_set_view = (ImageView) v.findViewById(R.id.image_set_view);



        Settext();
        return  v;
    }

    private void Settext() {

        if(File_Confix_Data.getOutput_List_Data.get( File_Confix_Data.position_id ).getImage_product().size()>0){

            Glide.with(getContext()).load(File_Confix_Data.getOutput_List_Data.get(  File_Confix_Data.position_id ).getImage_product().get(0)).into(image_set_view);
        }

       texttitel.setText("ชื่อ  "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_name());
       contect.setText("  "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getCategory_name()) ;


        addres.setText("อาการ   "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_detail() );
        data11.setText("สาเหตุ   "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_Cause()
                +"\n" +"การรักษา   "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_treatment()
                +"\n" +"การดูแลตัวเอง   "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_taking_care()
                +"\n" +"อื่น ๆ "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getHealthy_another()
                +"\n" +"ลงเมือ "+File_Confix_Data.getOutput_List_Data.get(File_Confix_Data.position_id).getDate()
        );
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


}
