package com.android.learnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Nivel1dActivity extends Activity{
	boolean wrong=false;
	String score="";
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel1d_layout);
        
        
Intent intent = getIntent();
        
        score = intent.getStringExtra("score");
        
        ((TextView)findViewById(R.id.textViewScore)).setText(score);
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
	
	public void b1(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("1");
	}
	public void b2(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("2");
	}
	public void b3(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("3");
	}
	public void b4(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("4");
	}
	public void b5(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("5");
	}
	public void b6(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("6");
	}
	public void b7(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("7");
	}
	public void b8(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("8");
	}
	public void b9(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("9");
	}
	public void b0(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.append("0");
	}
	public void enviar(View v){	
		String resultado=UIHelper.getText(this, R.id.editTextResultado);
		if(resultado.equals("12")){
			Toast.makeText(getApplicationContext(), "¡Correcto!", Toast.LENGTH_LONG).show();
		    Intent intent = new Intent(this, Nivel1cActivity.class);
		    int oldScore=Integer.parseInt((String)((TextView)findViewById(R.id.textViewScore)).getText());
			int levelScore=Integer.parseInt(score);
			String newScore=String.valueOf(levelScore+oldScore);
			intent.putExtra("score",newScore);
	    	startActivity(intent);
		}
		else{
			wrong=true;
			Toast.makeText(getApplicationContext(), "¡Incorrecto!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void borrar(View v){
		String resultado=UIHelper.getText(this, R.id.editTextResultado);
		String resNuevo="";
		if(!resultado.equals("")){
			resNuevo = resultado.substring(0, resultado.length()-1); 
		}
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.setText(resNuevo);
	}
	
	public void limpiar(View v){	
		EditText respuesta = (EditText) findViewById(R.id.editTextResultado);
		respuesta.setText("");
	}	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crear_menu, menu);
        return true;
    }

}
