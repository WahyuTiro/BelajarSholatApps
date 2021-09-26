package com.paperplay.belajarsholat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("NewApi") public class MenuActivity extends Activity implements OnTouchListener{
	public static int screenWidth;
	ImageHandler imageHandler = new ImageHandler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make it completely full screen
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
		setContentView(R.layout.activity_menu);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenWidth = displaymetrics.widthPixels;
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins((int)(screenWidth/80.4), (int)(screenWidth/96), (int)(screenWidth/80.4), (int)(screenWidth/96));
		float density = getResources().getDisplayMetrics().density;
		ImageView btnSubuh = (ImageView)findViewById(R.id.btnSubuh);
		ImageView btnDzuhur = (ImageView)findViewById(R.id.btnDzuhur);
		ImageView btnAshar = (ImageView)findViewById(R.id.btnAshar);
		ImageView btnMaghrib= (ImageView)findViewById(R.id.btnMaghrib);
		ImageView btnIsya = (ImageView)findViewById(R.id.btnIsya);
		try {
			btnSubuh.setImageDrawable(CustomDrawable.getAssetImage(this, "ic_subuh"));
			btnDzuhur.setImageDrawable(CustomDrawable.getAssetImage(this, "ic_dzuhur"));
			btnAshar.setImageDrawable(CustomDrawable.getAssetImage(this, "ic_ashar"));
			btnMaghrib.setImageDrawable(CustomDrawable.getAssetImage(this, "ic_maghrib"));
			btnIsya.setImageDrawable(CustomDrawable.getAssetImage(this, "ic_isya"));
		}catch(Exception ex){
			Log.e("Error MainActivity", ex.getMessage());
		}
		imageHandler.scaleImage(btnSubuh, (int)(screenWidth/6), density);
		imageHandler.scaleImage(btnDzuhur, (int)(screenWidth/6), density);
		imageHandler.scaleImage(btnAshar, (int)(screenWidth/6), density);
		imageHandler.scaleImage(btnMaghrib, (int)(screenWidth/6), density);
		imageHandler.scaleImage(btnIsya, (int)(screenWidth/6), density);
		
		btnSubuh.setLayoutParams(lp);
		btnDzuhur.setLayoutParams(lp);
		btnAshar.setLayoutParams(lp);
		btnMaghrib.setLayoutParams(lp);
		btnIsya.setLayoutParams(lp);
		
		btnSubuh.setOnTouchListener(this);
		btnDzuhur.setOnTouchListener(this);
		btnMaghrib.setOnTouchListener(this);
		btnAshar.setOnTouchListener(this);
		btnIsya.setOnTouchListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN: {
	        	((ImageView)v).setImageAlpha(200);
	            break;
	        }
	        case MotionEvent.ACTION_UP:{
	        	((ImageView)v).setImageAlpha(255);
	    		Intent intent = new Intent(MenuActivity.this, SholatActivity.class);
	        	if(v.getId() == R.id.btnSubuh) {
	        		intent.putExtra("sholat", "subuh");
	        	}else if(v.getId() == R.id.btnDzuhur) {
	        		intent.putExtra("sholat", "dzuhur");
	        	}else if(v.getId() == R.id.btnAshar) {
	        		intent.putExtra("sholat", "ashar");
	        	}else if(v.getId() == R.id.btnMaghrib) {
	        		intent.putExtra("sholat", "maghrib");
	        	}else if(v.getId() == R.id.btnIsya) {
	        		intent.putExtra("sholat", "isya");
	        	}
				startActivity(intent);
	        	break;
	        }
	        case MotionEvent.ACTION_CANCEL: {
	            ImageView view = (ImageView) v;
	            //clear the overlay
	            view.getDrawable().clearColorFilter();
	            view.invalidate();
	            break;
	        }
	    }
		return true;
	}
	
}
