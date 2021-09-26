package com.paperplay.belajarsholat;

import java.util.ArrayList;

import com.paperplay.belajarsholat.CustomDrawable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SholatActivity extends Activity{
	public static int screenWidth, screenHeight;
	static Context context;
	static int posisi = 0, rakaat = 0;
	Intent intent;
	ArrayList<String> gerakan = new ArrayList<String>();
	ArrayList<String> bacaan = new ArrayList<String>();
	ArrayList<String> bacaanIndo = new ArrayList<String>();
	ArrayList<String> bacaanLatin = new ArrayList<String>();
	
	private String namaGerakanSholat[] = {"berdiri","berdiri","ruku","takbir","niat","sujud","duduk","sujud"};
    private String namaBacaanSholat[] = {"alfatihah","surat_alquran","ruku","itidal","itidal_berdiri","sujud","duduk_diantara_2_sujud","sujud"};
    
    ImageView btnNext, btnPrev;
    Button btnIndo, btnLatin, btnArab;
	ImageHandler imageHandler = new ImageHandler();
	String sholat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		// Make it completely full screen
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
	    setContentView(R.layout.activity_sholat);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenWidth = displaymetrics.widthPixels;
		screenHeight = displaymetrics.heightPixels;
		float density = getResources().getDisplayMetrics().density;

	    LinearLayout layoutButton = (LinearLayout)findViewById(R.id.layoutButton);
	    LinearLayout .LayoutParams layoutButtonParams= new LinearLayout.LayoutParams((int) (screenWidth/2.428), LinearLayout.LayoutParams.WRAP_CONTENT);
	    layoutButtonParams.setMargins(0, 0, (int) (screenWidth/5.72), screenHeight/108);
	    layoutButton.setLayoutParams(layoutButtonParams);
	    
	    LinearLayout layoutButtonNavigator = (LinearLayout)findViewById(R.id.layoutButtonNavigator);
	    LinearLayout .LayoutParams layoutButtonNavigatorParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    layoutButtonNavigatorParams.setMargins(0, 0, (int) (screenWidth/14.2), screenHeight/108);
	    layoutButtonNavigator.setLayoutParams(layoutButtonNavigatorParams);
	    
	    context = this;
	    intent = getIntent();
	    sholat = intent.getStringExtra("sholat");
	    final ImageView imgGerakan = (ImageView)findViewById(R.id.gerakan);
	    final ImageView imgBacaan = (ImageView)findViewById(R.id.bacaan);
        
	    btnNext = (ImageView)findViewById(R.id.btnNext);
        btnPrev = (ImageView)findViewById(R.id.btnPrev);
        btnIndo = (Button)findViewById(R.id.btnIndo);
        btnArab = (Button)findViewById(R.id.btnArab);
        btnLatin = (Button)findViewById(R.id.btnLatin);

	    LinearLayout .LayoutParams buttonPrevParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    buttonPrevParams.setMargins(0, 0, (int) (screenWidth/2.100), 0);
	    btnPrev.setLayoutParams(buttonPrevParams);
	    
	    LinearLayout .LayoutParams buttonParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
	    buttonParams.setMargins(0, 0, (int) (screenWidth/240), 0);
        btnIndo.setLayoutParams(buttonParams);
        btnArab.setLayoutParams(buttonParams);
        btnLatin.setLayoutParams(buttonParams);
        
        try{
        	btnPrev.setImageDrawable(CustomDrawable.getAssetImage(context, "button_prev"));
        	btnNext.setImageDrawable(CustomDrawable.getAssetImage(context, "button_next"));
			imgGerakan.setImageDrawable(CustomDrawable.getAssetImage(context, "niat"));
			imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_niat_"+sholat));
		}catch(Exception err){
			Log.e("Error", err.getMessage());
		}
        
		imageHandler.scaleImage(btnPrev, (int)(screenWidth/20), density);
		imageHandler.scaleImage(btnNext, (int)(screenWidth/20), density);
		
        if(sholat.equalsIgnoreCase("subuh")) rakaat = 2;
        else if(sholat.equalsIgnoreCase("maghrib")) rakaat = 3;
        else rakaat = 4;
        
        btnPrev.setVisibility(View.INVISIBLE);
        btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					posisi++;
					if(posisi >= gerakan.size()-1) btnNext.setVisibility(View.INVISIBLE); 
					imgGerakan.setImageDrawable(CustomDrawable.getAssetImage(context, gerakan.get(posisi)));
					imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_"+bacaan.get(posisi)));
					
					if(posisi >= 0) btnPrev.setVisibility(View.VISIBLE);
				}catch(Exception err){
					Toast.makeText(context, "Error: "+err.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
        btnPrev.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
				posisi--;
				try{
					if(posisi>=0) {
						imgGerakan.setImageDrawable(CustomDrawable.getAssetImage(context, gerakan.get(posisi)));
						imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_"+bacaan.get(posisi)));
					}else {
						btnPrev.setVisibility(View.INVISIBLE);
						imgGerakan.setImageDrawable(CustomDrawable.getAssetImage(context, "niat"));
						imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_niat_"+sholat));
					}
				}catch(Exception err){
					Toast.makeText(context, "Error: "+err.getMessage(), Toast.LENGTH_LONG).show();
				}
				if(posisi < gerakan.size()-1) btnNext.setVisibility(View.VISIBLE);
        	}
        });
        btnIndo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
        			if(posisi == -1)
        				imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_niat_"+sholat+"_indo"));
        			else 
            			imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_"+bacaan.get(posisi)+"_indo"));
				} catch (Exception err) {
					Log.e("Error", err.getMessage());
				}
			}
		});
        btnLatin.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		try {
        			if(posisi == -1)
        				imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_niat_"+sholat+"_latin"));
        			else 
            			imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_"+bacaan.get(posisi)+"_latin"));
        		} catch (Exception err) {
        			Log.e("Error", err.getMessage());
        		}
        	}
        });
        btnArab.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		try {
        			if(posisi == -1)
        				imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_niat_"+sholat));
        			else 
            			imgBacaan.setImageDrawable(CustomDrawable.getAssetImage(context, "bacaan_"+bacaan.get(posisi)));
        		} catch (Exception err) {
        			Log.e("Error", err.getMessage());
        		}
        	}
        });
	}
	@Override
	public void onResume(){
		super.onResume();
		setGerakanSholat(rakaat);
		posisi = -1;
	}
	private void setGerakanSholat(int rakaat){
		for (int i = 1; i <= rakaat; i++) {
			if(i == 1){ //rakaat pertama
				gerakan.add("takbir");
				bacaan.add("takbir");
				bacaanIndo.add("takbir_indo");
				bacaanLatin.add("takbir_latin");
				gerakan.add("berdiri");
				bacaan.add("iftitah");
				bacaanIndo.add("iftitah_indo");
				bacaanLatin.add("iftitah_latin");
			}
			for (int j = 0; j < namaGerakanSholat.length; j++) {
				gerakan.add(namaGerakanSholat[j]);
				bacaan.add(namaBacaanSholat[j]);
				bacaanIndo.add(namaBacaanSholat[j]+"_indo");
				bacaanLatin.add(namaBacaanSholat[j]+"_latin");
			}
			if(i%2==0||i == rakaat){ //rakaat genap
				gerakan.add("duduk");
				if(i == rakaat){
					bacaan.add("tasyahud_akhir");
					bacaanIndo.add("tasyahud_akhir_indo");
					bacaanLatin.add("tasyahud_akhir_latin");
				}
				else {
					bacaan.add("tasyahud_awal");
					bacaanIndo.add("tasyahud_awal_indo");
					bacaanLatin.add("tasyahud_awal_latin");
				}
			}
			if(i == rakaat){ //rakaat terakhir
				gerakan.add("salam_kanan");
				bacaan.add("salam");
				bacaanIndo.add("salam");
				bacaanLatin.add("salam");
				gerakan.add("salam_kiri");
				bacaan.add("salam");
				bacaanIndo.add("salam");
				bacaanLatin.add("salam");
			}else{
				gerakan.add("takbir");
				bacaan.add("takbir");
				bacaanIndo.add("takbir_indo");
				bacaanLatin.add("takbir_latin");
			}
		}
	}
}
