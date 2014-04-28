package com.android.learnapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AcercaActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acerca_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		 
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Chalkduster.ttf");
        TextView tv = (TextView) findViewById(R.id.textViewAcerca);
        tv.setTypeface(tf);   
        
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();		
		}
		else{
			Intent intent;
			switch (item.getItemId()) {
			case R.id.action_acerca:
				intent = new Intent(this, AcercaActivity.class);
				startActivity(intent);
				break;
			case R.id.action_puntos:
				intent = new Intent(this, CuentaActivity.class);
				startActivity(intent);
				break;
			case R.id.action_sms:
				intent = new Intent(this, SendSMSActivity.class);
				startActivity(intent);
				break;
			
			}		
		}
		return super.onOptionsItemSelected(item);
	}

}
