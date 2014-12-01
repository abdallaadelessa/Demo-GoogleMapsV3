
package com.shoppinglist;

import model.Place;
import model.PlacesList;

import org.json.JSONException;

import singletons.staticUserInfos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import functions.GingerbreadJSFix;
import functions.Utilities;

public class PlacesMapActivity extends Activity 
{

	static final String DEBUG = "PLACE_DEBUG";
	// Nearest places
	PlacesList nearPlaces;

	static final LatLng Egypt = new LatLng(30.0500, 31.2333);

	WebView myBrowser;
	
    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_places);
		Intent i = getIntent();
		
		nearPlaces = (PlacesList) i.getSerializableExtra("near_places");
		
		myBrowser = (WebView) findViewById(R.id.browser);
		
		String mapPath = "file:///android_asset/map.html";
		
		final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(
				this);
		
		new GingerbreadJSFix().fixWebViewJSInterface(myBrowser, myJavaScriptInterface,
				"AndroidFunction", "testJS",this,nearPlaces);		
		myBrowser.getSettings().setJavaScriptEnabled(true);
		myBrowser.loadUrl(mapPath);
	}


	//--------------------------------------------------
	
	/**
	 * Methods to be called from java script
	 */
	public class MyJavaScriptInterface
	{

		Context mContext;

		MyJavaScriptInterface(Context c)
		{

			mContext = c;
		}

		@JavascriptInterface
		public void showToast(String ref ,String name) 
		{
			//Toast.makeText(mContext, name+":"+ref, Toast.LENGTH_SHORT).show();
			
			staticUserInfos tmp = staticUserInfos.getInstance();
        	tmp.setisGooglePlace(true);
            // Starting new intent
            Intent in = new Intent(getApplicationContext(),
                    SinglePlaceActivity.class);
             
            
            // Sending place refrence id to single place activity
            // place refrence id used to get "Place full details"
            in.putExtra(KEY_REFERENCE, ref);
            Utilities.StartActivity(PlacesMapActivity.this, in, true);
		}

	}
	
	//--------------------------------------------------

}