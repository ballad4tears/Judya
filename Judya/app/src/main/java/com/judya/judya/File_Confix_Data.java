package com.judya.judya;

import android.graphics.Bitmap;

import java.util.List;

public final class File_Confix_Data {


	public static List<GetHomeDog.OutputBean> getOutput_HomeDog;

	public static List<List_Data.OutputBean> getOutput_List_Data;
	public static List<List_Data_Cat.OutputBean> getOutput_data_Category;
	//public static List<List_Data_food.OutputBean> getOutput_data_List_Data_food;

	//public static List<List_Data_hospital.OutputBean> getOutput_data_List_Data_hospital;

	//public static List<Knowledge.OutputBean> getOutput_List_DataKnowledge;

	public static List<List_User.OutputBean> data_list_user;




	public static String string_id_cat="";
	public static String string_id_data="";
	public static String titel="";
	public static  int position_id=0;
	public static  String position_id_s="";
	public static double latitude=13.00;
	public static double longitude=100.00;

	public static String Code_data="";
	public static String Code="";

	public static String titelKnowledge="";

	public static boolean image_set=false;
	public static Bitmap mPhotoBitMap ;
	//public static String login="0";
	public static  int position_id_regist=0;


//	for(int i=0;i<File_Confix_Data.getOutput.size();i++){
//		Debug.out(File_Confix_Data.getOutput.get(i).getRestaurant_name());
//		for(int j=0;j<File_Confix_Data.getOutput.get(i).getImage_product().size();j++){
//			Debug.out( File_Confix_Data.getOutput.get(i).getImage_product().get(j));
//		}
//	}
	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	public static class Extra {
		public static final String IMAGES = "IMAGES";
		public static final String IMAGE_POSITION = "IMAGE_POSITION";
	} 
	
}
 