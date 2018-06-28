package com.judya.judya;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


/**
 * @author Adil Soomro
 *
 */
public class TabSample_Addata_detail extends TabActivity {
	/** Called when the activity is first created. */
	String code;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Bundle extras = getIntent().getExtras();
		 code = "";
		setTabs() ;
	}
	private void setTabs()
	{
		 addTab("ข้อมูลการบันทึก", R.drawable.icon1, Main_Add_data_ShowAnimal.class);
		addTab("เล่นเสียง", R.drawable.icon1, MainActivity_play_sound.class);

		 addTab("รายละเอียด", R.drawable.icon1, SuccessActivity_Healthy.class);


	}

	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		intent.putExtra("code",code);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);

		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}