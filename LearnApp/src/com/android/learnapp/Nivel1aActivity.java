package com.android.learnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Nivel1aActivity extends Activity{

	boolean wrong=false;
	String score="";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nivel1a_layout);


		final TextView reloj = (TextView) findViewById(R.id.textViewReloj);

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					while (!isInterrupted()) {
						Thread.sleep(1000);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								int time=Integer.parseInt((String)reloj.getText());
								String newT;
								if(wrong){
									newT= String.valueOf(time+11);
									wrong=false;
								}
								else{
									newT= String.valueOf(time+1);
								}
								score=newT;
								reloj.setText(newT);
							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};

		t.start();





	}

	public void correcto(View v){	
		Toast.makeText(getApplicationContext(), "¡Correcto!", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, Nivel1bActivity.class);
		int oldScore=Integer.parseInt((String)((TextView)findViewById(R.id.textViewScore)).getText());
		int levelScore=Integer.parseInt(score);
		String newScore=String.valueOf(levelScore+oldScore);
		intent.putExtra("score",newScore);
		startActivity(intent);
	}	
	public void incorrecto(View v){
		wrong=true;
		Toast.makeText(getApplicationContext(), "¡Incorrecto!", Toast.LENGTH_LONG).show();
		

	}	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_menu, menu);
		return true;
	}

}
