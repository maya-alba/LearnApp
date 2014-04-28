package com.android.learnapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class SendSMSActivity extends Activity {
 
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Chalkduster.ttf");
        TextView tv = (TextView) findViewById(R.id.textViewSMS);
        TextView tv1 = (TextView) findViewById(R.id.textViewRecomendacion);
        TextView tv2 = (TextView) findViewById(R.id.textViewPhoneNo);
        tv1.setTypeface(tf);   
        tv2.setTypeface(tf);  
        tv.setTypeface(tf);  
 
		buttonSend = (Button) findViewById(R.id.buttonSend);
		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		textSMS = (EditText) findViewById(R.id.editTextSMS);
 
		buttonSend.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			  String phoneNo = textPhoneNo.getText().toString();
			  String sms = textSMS.getText().toString();
 
			  try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, sms, null, null);
				Toast.makeText(getApplicationContext(), "¡SMS enviado!",
							Toast.LENGTH_LONG).show();
				finish();
			  } catch (Exception e) {
				Toast.makeText(getApplicationContext(),
					"Error al enviar SMS",
					Toast.LENGTH_LONG).show();
				e.printStackTrace();
			  }
 
			}
		});
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();		
		}
		return super.onOptionsItemSelected(item);
	}
}