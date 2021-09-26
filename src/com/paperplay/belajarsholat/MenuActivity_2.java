package com.paperplay.belajarsholat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MenuActivity_2 extends Activity implements View.OnClickListener{
	static Context context;
	static int posisi = 0;
	ImageButton btnSubuh, btnDzuhur, btnAshar, btnMaghrib, btnIsya, btnTarawih,  btnIdulFitri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		// Make it completely full screen
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
	    setContentView(R.layout.activity_menu);
	    context = this;
	    
	    btnSubuh = (ImageButton)findViewById(R.id.btnSubuh);
	    btnDzuhur = (ImageButton)findViewById(R.id.btnDzuhur);
	    btnAshar = (ImageButton)findViewById(R.id.btnAshar);
	    btnMaghrib = (ImageButton)findViewById(R.id.btnMaghrib);
	    btnIsya = (ImageButton)findViewById(R.id.btnIsya);
	    
	    btnSubuh.setOnClickListener(this);
	    btnDzuhur.setOnClickListener(this);
	    btnAshar.setOnClickListener(this);
	    btnMaghrib.setOnClickListener(this);
	    btnIsya.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(MenuActivity_2.this, SholatActivity.class);
		if(v.getId() == btnSubuh.getId()){
			intent.putExtra("sholat", "subuh");
		}else if(v.getId() == btnDzuhur.getId()){
			intent.putExtra("sholat", "dzuhur");
		}else if(v.getId() == btnAshar.getId()){
			intent.putExtra("sholat", "ashar");
		}else if(v.getId() == btnMaghrib.getId()){
			intent.putExtra("sholat", "maghrib");
		}else if(v.getId() == btnIsya.getId()){
			intent.putExtra("sholat", "isya");
		}
		startActivity(intent);
	}

}
