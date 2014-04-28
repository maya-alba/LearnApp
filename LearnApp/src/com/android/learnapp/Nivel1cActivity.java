package com.android.learnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipData;
import android.graphics.Typeface;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;


public class Nivel1cActivity extends Activity{
	boolean wrong=false;
	String score="";
	private TextView option1, option2, option3, choice1, choice2, choice3;
	private int contador=0;
	private int preguntas=3;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel1c_layout);
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
        
      //views to drag
      		option1 = (TextView)findViewById(R.id.option_1);
      		option2 = (TextView)findViewById(R.id.option_2);
      		option3 = (TextView)findViewById(R.id.option_3);

      		//views to drop onto
      		choice1 = (TextView)findViewById(R.id.choice_1);
      		choice2 = (TextView)findViewById(R.id.choice_2);
      		choice3 = (TextView)findViewById(R.id.choice_3);
      		
      		//set touch listeners
      		option1.setOnTouchListener(new ChoiceTouchListener());
      		option2.setOnTouchListener(new ChoiceTouchListener());
      		option3.setOnTouchListener(new ChoiceTouchListener());
      		
      		//set drag listeners
      		choice1.setOnDragListener(new ChoiceDragListener());
      		choice2.setOnDragListener(new ChoiceDragListener());
      		choice3.setOnDragListener(new ChoiceDragListener());

      	}



      	private final class ChoiceTouchListener implements OnTouchListener {


      		public boolean onTouch(View view, MotionEvent motionEvent) {

      			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
      				//setup drag
      				ClipData data = ClipData.newPlainText("", "");
      				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
      				//start dragging the item touched
      				view.startDrag(data, shadowBuilder, view, 0);
      				return true;
      			} 
      			else {
      				return false;
      			}

      		}
      	}
      	
      	
      	private class ChoiceDragListener implements OnDragListener {
      		
      		@Override
      		public boolean onDrag(View v, DragEvent event) {
      		    //handle drag events
      			switch (event.getAction()) {
      		    case DragEvent.ACTION_DRAG_STARTED:
      		        //no action necessary
      		        break;
      		    case DragEvent.ACTION_DRAG_ENTERED:
      		        //no action necessary
      		        break;
      		    case DragEvent.ACTION_DRAG_EXITED:        
      		        //no action necessary
      		        break;
      		    case DragEvent.ACTION_DROP:
      		        //handle the dragged view being dropped over a drop view
      		    	//handle the dragged view being dropped over a target view
      		    	View view = (View) event.getLocalState();
      		    	//stop displaying the view where it was before it was dragged
      		    	view.setVisibility(View.INVISIBLE);
      		    	//view dragged item is being dropped on
      		    	TextView dropTarget = (TextView) v;
      		    	//view being dragged and dropped
      		    	TextView dropped = (TextView) view;
      		    	String drop;
      		    	String target;
      		    	drop=(String) dropped.getText();
      		    	target=(String)dropTarget.getText();

      		    	if(((drop.equalsIgnoreCase("Campeche"))&&(target.equalsIgnoreCase("Campeche")))
      		    			||((drop.equalsIgnoreCase("Nayarit"))&&(target.equalsIgnoreCase("Tepic")))
      		    			||((drop.equalsIgnoreCase("Jalisco"))&&(target.equalsIgnoreCase("Guadalajara")))){
      		    		//update the text in the target view to reflect the data being dropped
          		    	dropTarget.setText(target + " - " + drop);
          		    	//make it bold to highlight the fact that an item has been dropped
          		    	dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
          		    	contador++;
          		    	if(contador==preguntas){
          		    		sigNivel();
          		    	}
      		    	}
      		    	else{
      		    		wrong=true;
      		    		findViewById(dropped.getId()).setVisibility(View.VISIBLE);
      		    	}
      		    	
      		        break;
      		    case DragEvent.ACTION_DRAG_ENDED:
      		        //no action necessary
      		        break;
      		    default:
      		        break;
      		}
      		    return true;
      		}
        
   }
      	
    public void sigNivel(){
	    Intent intent = new Intent(this, Nivel1aActivity.class);
	    int oldScore=Integer.parseInt((String)((TextView)findViewById(R.id.textViewScore)).getText());
		int levelScore=Integer.parseInt(score);
		String newScore=String.valueOf(levelScore+oldScore);
		intent.putExtra("score",newScore);
    	startActivity(intent);
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crear_menu, menu);
        return true;
    }

}
