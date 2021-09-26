package com.paperplay.belajarsholat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class CustomDrawable {
	public static Drawable getAssetImage(Context context, String filename) throws IOException {
	    AssetManager assets = context.getResources().getAssets();
	    InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filename + ".png")));
	    Bitmap bitmap = BitmapFactory.decodeStream(buffer);
	    return new BitmapDrawable(bitmap);
	}
}
