package com.android.learnapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	
	MediaPlayer mp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        getActionBar().hide();
        mp1 = MediaPlayer.create(LoginActivity.this, R.raw.jmin);
		mp1.start();
   
    }
    
  //Te lleva a Mapa
    public void goToMapa(View v)throws IOException, JSONException{
    	
    	File file = getBaseContext().getFileStreamPath("usuarios");
    	if(file.exists()){
    		Log.d("LoginActivity", "Existe el archivo");
    		FileInputStream fis=openFileInput("usuarios");
    		BufferedInputStream bis=new BufferedInputStream(fis);
    		StringBuffer b = new StringBuffer();
    		while(bis.available()!=0){
    			char c=(char)bis.read();
    			b.append(c);
    		}
    		bis.close();
    		fis.close();
    		
    		
    		String usuarioL=UIHelper.getText(this, R.id.editTextUserLogin);
    		String passL=UIHelper.getText(this, R.id.editTextPasswordLogin);
    		
    		
    		JSONArray datos= new JSONArray(b.toString());
    		StringBuffer usuariosBuffer=new StringBuffer();
    		
    		for(int i=0;i<datos.length();i++){
    			String user=datos.getJSONObject(i).getString("Usuario");
    			usuariosBuffer.append(user+"\n");
    			String pass=datos.getJSONObject(i).getString("Password");
    			usuariosBuffer.append(pass+"\n");
    			
    			if(user.equals(usuarioL) && pass.equals(passL)){
    				mp1.stop();
    				Intent intent = new Intent(this, MapaActivity.class);
    		    	startActivity(intent);
    			}
    			else{
    				Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();	
    			}
    		}
    		
    		//UIHelper.displayText(this, R.id.textViewUsuario, usuariosBuffer.toString());
    	}
    	else{
    		Log.d("LoginActivity", "No existe el archivo");
			Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();	

    	}
	
    }
    //Te lleva a CrearCuenta
    public void goToCrearCuenta(View v){
    	mp1.stop();
    	Intent intent = new Intent(this, CrearCuentaActivity.class);
    	startActivity(intent);
    	
    }
    

}
