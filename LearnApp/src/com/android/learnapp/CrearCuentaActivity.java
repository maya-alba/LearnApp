package com.android.learnapp;

import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CrearCuentaActivity extends Activity{

	Spinner spinner;
	String[] opciones = {"Grado escolar", "Kinder", "1ro - 3ro de Primaria", "4to - 6to de Primaria", "Secundaria", "Otro"};
	String grado="";
	String gen="N";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crear_cuenta_layout);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		spinner = (Spinner)findViewById(R.id.spinnerGrado);
		ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,opciones);
		spinner.setAdapter(adaptadorSpinner);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				switch (i) {
				case 0:	
					grado="";
					break;
				case 1:
					//Toast.makeText(getApplicationContext(), opciones[i], Toast.LENGTH_LONG).show();	
					grado=opciones[i];
					break;
				case 2:
					//Toast.makeText(getApplicationContext(), opciones[i], Toast.LENGTH_LONG).show();
					grado=opciones[i];
					break;
				case 3:
					//Toast.makeText(getApplicationContext(), opciones[i], Toast.LENGTH_LONG).show();
					grado=opciones[i];
					break;
				case 4:
					//Toast.makeText(getApplicationContext(), opciones[i], Toast.LENGTH_LONG).show();
					grado=opciones[i];
					break;
				case 5:
					//Toast.makeText(getApplicationContext(), opciones[i], Toast.LENGTH_LONG).show();
					grado=opciones[i];
					break;
				}
				
			}

	
			
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {	
			}			
		});
		
		
		ImageView nino = (ImageView) findViewById(R.id.imageViewNino);
		nino.setClickable(true);
		ImageView nina = (ImageView) findViewById(R.id.imageViewNina);
		nina.setClickable(true);
        nino.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gen="M";
                v.setBackgroundColor(Color.GREEN);
                ImageView nina = (ImageView) findViewById(R.id.imageViewNina);
                nina.setBackgroundColor(0);
            }
        });
     
        nina.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gen="F";
                v.setBackgroundColor(Color.GREEN);
                ImageView nino = (ImageView) findViewById(R.id.imageViewNino);
                nino.setBackgroundColor(0);
            }
        });
       
        
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Chalkduster.ttf");
        TextView tv = (TextView) findViewById(R.id.textViewCrearCuenta);
        tv.setTypeface(tf);   
        
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();		
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_menu, menu);
		return true;
	}
	
	public void goToMapa(View v ) throws IOException, JSONException{				
		
		JSONArray datos= new JSONArray();
		JSONObject usuario = new JSONObject();
		
		String user=UIHelper.getText(this, R.id.editTextUsuario);
		String pass=UIHelper.getText(this, R.id.editTextPassword);
		
		if((!user.equals("")) && (!pass.equals("")) && (!gen.equals("N")) && (!grado.equals(""))){
			usuario.put("Usuario",user);
			usuario.put("Password",pass);
			usuario.put("Genero",gen);
			usuario.put("Grado",grado);
			datos.put(usuario);
		
			String text=datos.toString();
			FileOutputStream fos=openFileOutput("usuarios", MODE_PRIVATE);
			fos.write(text.getBytes());
			fos.close();
			
			Intent intent = new Intent(this, MapaActivity.class);
			startActivity(intent);
			finish();
		
		}
		else{
			Toast.makeText(getApplicationContext(), "¡Llena todos los campos!", Toast.LENGTH_LONG).show();		
		}	
		//UIHelper.displayText(this,R.id.textViewPassword,"File written to disk:\n" + datos.toString());	
	}
	
	
}
