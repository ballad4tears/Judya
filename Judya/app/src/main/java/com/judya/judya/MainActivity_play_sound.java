package com.judya.judya;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity_play_sound extends AppCompatActivity {

	private Database_Data_sound  sound;
	Button buttonStart, buttonStop, buttonPlayLastRecordAudio,
			buttonStopPlayingRecording ;

	MediaRecorder mediaRecorder ;
	Random random ;
	String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
	public static final int RequestPermissionCode = 1;
	MediaPlayer mediaPlayer ;
	String code_update="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sounds);

		sound=new Database_Data_sound(this);

				for(int i=0;i<sound.SelectAllData().size();i++){
					if(sound.SelectAllData().get(i).get("Code").equals(  File_Confix_Data.Code_data)){
						code_update=sound.SelectAllData().get(i).get("col2");
					}

				}
		buttonStart = (Button) findViewById(R.id.button);
		buttonStop = (Button) findViewById(R.id.button2);
		buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);
		buttonStopPlayingRecording = (Button)findViewById(R.id.button4);

//		buttonStop.setEnabled(false);
//		buttonPlayLastRecordAudio.setEnabled(false);
//		buttonStopPlayingRecording.setEnabled(false);

		random = new Random();
		buttonStart.setEnabled(false);
		buttonStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {


			}
		});
		buttonStop.setEnabled(false);
		buttonStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) throws IllegalArgumentException,
					SecurityException, IllegalStateException {

				buttonStop.setEnabled(false);
				buttonStart.setEnabled(false);
				buttonStopPlayingRecording.setEnabled(true);

				mediaPlayer = new MediaPlayer();
				try {
					mediaPlayer.setDataSource(code_update);
					mediaPlayer.prepare();


				} catch (IOException e) {
					e.printStackTrace();
				}

				mediaPlayer.start();
				Toast.makeText(MainActivity_play_sound.this, "Recording Playing",
						Toast.LENGTH_LONG).show();
			}
		});


		buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				buttonStop.setEnabled(false);
				buttonStart.setEnabled(false);
				buttonStopPlayingRecording.setEnabled(false);
				buttonPlayLastRecordAudio.setEnabled(true);

				if(mediaPlayer != null){
					mediaPlayer.stop();
					mediaPlayer.release();
					MediaRecorderReady();
				}
			}
		});

	}

	public void MediaRecorderReady(){
		mediaRecorder=new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		mediaRecorder.setOutputFile(code_update);
	}

	public String CreateRandomAudioFileName(int string){
		StringBuilder stringBuilder = new StringBuilder( string );
		int i = 0 ;
		while(i < string ) {
			stringBuilder.append(RandomAudioFileName.
					charAt(random.nextInt(RandomAudioFileName.length())));

			i++ ;
		}
		return stringBuilder.toString();
	}

	private void requestPermission() {
		ActivityCompat.requestPermissions(MainActivity_play_sound.this, new
				String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case RequestPermissionCode:
				if (grantResults.length> 0) {
					boolean StoragePermission = grantResults[0] ==
							PackageManager.PERMISSION_GRANTED;
					boolean RecordPermission = grantResults[1] ==
							PackageManager.PERMISSION_GRANTED;

					if (StoragePermission && RecordPermission) {
						Toast.makeText(MainActivity_play_sound.this, "Permission Granted",
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(MainActivity_play_sound.this,"Permission Denied",Toast.LENGTH_LONG).show();
					}
				}
				break;
		}
	}

	public boolean checkPermission() {
		int result = ContextCompat.checkSelfPermission(getApplicationContext(),
				WRITE_EXTERNAL_STORAGE);
		int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
				RECORD_AUDIO);
		return result == PackageManager.PERMISSION_GRANTED &&
				result1 == PackageManager.PERMISSION_GRANTED;
	}
}