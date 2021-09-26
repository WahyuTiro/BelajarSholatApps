package com.paperplay.belajarsholat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {
	static Context context;
	private InterstitialAd interstitial;
	public static int screenWidth, screenHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make it completely full screen
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	  
		setContentView(R.layout.activity_main);
		context = this;
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenHeight = displaymetrics.heightPixels;
		screenWidth = displaymetrics.widthPixels;
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-9002983919929663/1151221230");

        // Create ad request.
        AdRequest adRequesat = new AdRequest.Builder().build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequesat);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (interstitial.isLoaded()) {
            interstitial.show();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			//LinearLayout lyTitle = (LinearLayout)rootView.findViewById(R.id.lyTitle);
			ImageHandler imageHandler = new ImageHandler();
//			ImageView imgTitle = (ImageView)rootView.findViewById(R.id.imgTitle);
			ImageView imgStart = (ImageView)rootView.findViewById(R.id.btnStart);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins((int)(screenWidth/3.5), (int)(screenWidth/20), (int)(screenWidth/4), (int)(screenWidth/96));
			LinearLayout.LayoutParams lpStart = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lpStart.setMargins((int)(screenWidth/2.3), (int)(screenWidth/4.5), (int)(screenWidth/2.5), 0);
			LinearLayout.LayoutParams lpButtonAbout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lpButtonAbout.setMargins((int)(screenWidth/24), (int)(screenHeight/1.25), (int)(screenWidth/96),  0);
			LinearLayout.LayoutParams lpButtonShare = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lpButtonShare.setMargins((int)(screenWidth/96), (int)(screenHeight/1.25), (int)(screenWidth/96),  0);
			LinearLayout.LayoutParams lpButtonRate = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lpButtonRate.setMargins((int)(screenWidth/1.35), (int)(screenHeight/1.25), (int)(screenWidth/96),  0);
			LinearLayout.LayoutParams lpButtonFacebook = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lpButtonFacebook.setMargins((int)(screenWidth/96), (int)(screenHeight/1.25), 0,  0);
			imgStart.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.context, MenuActivity.class);
					startActivity(intent);
				}
			});
			ImageView imgAbout = (ImageView)rootView.findViewById(R.id.btnAbout);
			imgAbout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.context, AboutActivity.class);
					startActivity(intent);
				}
			});
			ImageView imgShare = (ImageView)rootView.findViewById(R.id.btnShare);
			imgShare.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
					sharingIntent.setType("text/plain");
					String content = "Hai adik adik yang sholeh dan sholehah, sudah saatnya kita belajar sholat. \n"
							+ "Dengan Aplikasi \"Ayo Belajar Sholat\", kini kita bisa belajar gerakan serta bacaan sholat dengan mudah. "
							+ "\nFitur yang terdapat di aplikasi ini diantaranya:\n"
							+ "1. Dilengkapi gerakan sholat yang berurutan\n"
							+ "2. Dilengkapi bacaan sholat \n"
							+ "3. Dilengkapi pengalihan bacaan ke mode Arab, Latin, dan Indonesia (terjemahan)\n"
							+ "4. Terdapat 5 sholat fardhu (subuh, dzuhur, ashar, maghrib dan isya)\n"
							+ "5. Dapat berjalan di semua perangkat Android (ponsel maupun tablet)\n"
							+ "Dengan aplikasi ini, belajar sholat menjadi lebih seru dan menyenangkan!"
							+ "\nDownload aplikasinya di: \nhttp://play.google.com/store/apps/details?id=" + context.getPackageName();
					//sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, content);
					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
					context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
				}
			});
			ImageView imgRate = (ImageView)rootView.findViewById(R.id.btnRate);
			imgRate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
					    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
					} catch (android.content.ActivityNotFoundException anfe) {
					    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
					}
				}
			});
			ImageView imgFacebook = (ImageView)rootView.findViewById(R.id.btnFacebook);
			imgFacebook.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String url = "https://www.facebook.com/paperplay.studio";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			});
		    float density = context.getResources().getDisplayMetrics().density;
//		    imageHandler.scaleImage(imgTitle, (int)(screenWidth/2.2), density);
			imageHandler.scaleImage(imgStart, (int)(screenWidth/4), density);
			imageHandler.scaleImage(imgAbout, (int)(screenWidth/11), density);
			imageHandler.scaleImage(imgShare, (int)(screenWidth/11), density);
			imageHandler.scaleImage(imgRate, (int)(screenWidth/11), density);
			imageHandler.scaleImage(imgFacebook, (int)(screenWidth/11), density);
//			imgTitle.setLayoutParams(lp);
			imgStart.setLayoutParams(lpStart);
			imgRate.setLayoutParams(lpButtonRate);
			imgAbout.setLayoutParams(lpButtonAbout);
			imgFacebook.setLayoutParams(lpButtonFacebook);
			imgShare.setLayoutParams(lpButtonShare);
			return rootView;
		}
	}
}
