package com.android.learnapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MapaActivity extends Activity{
	LoginActivity login = new LoginActivity();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void goToNivel1a(View v){
	    Intent intent = new Intent(this, Nivel1aActivity.class);
    	startActivity(intent);
    }	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
	
	@Override
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
