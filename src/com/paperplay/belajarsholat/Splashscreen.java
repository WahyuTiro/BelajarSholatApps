package com.paperplay.belajarsholat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen extends Activity{
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make it completely full screen
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
		setContentView(R.layout.splash_screen);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
			}
			
		}, SPLASH_TIME_OUT);
	}

}
