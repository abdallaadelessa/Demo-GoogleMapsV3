
package test;

/*
 import java.util.ArrayList;
 import java.util.List;

 import android.app.Activity;
 import android.graphics.drawable.Drawable;
 import android.os.Bundle;
 import android.webkit.WebView;

 import com.google.android.maps.GeoPoint;
 import com.google.android.maps.ItemizedOverlay;
 import com.google.android.maps.MapActivity;
 import com.google.android.maps.Overlay;
 import com.google.android.maps.OverlayItem;

 public class Abdalla_test extends Activity
 {
 WebView myBrowser;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {

 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_abdalla_test);

 String path = "file:///android_asset/simplemap.html" ;
 String testPath = "https://maps.google.com/";

 myBrowser = (WebView)findViewById(R.id.mybrowser);
 //myBrowser.loadUrl(path);
 String data="<!DOCTYPE html>"+
 "<html> "+
 " <head> "+
 " <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />"+ 
 " <title>Google Maps Multiple Markers</title> "+
 " <script src=\"http://maps.google.com/maps/api/js?sensor=false\""+ 
 "        type=\"text/javascript\"></script>"+
 "</head> "+
 "<body>"+
 "  <div id=\"map\" style=\"width: 500px; height: 400px;\"></div>"+

 "  <script type=\"text/javascript\">"+
 "   var locations = ["+
 "    ['Bondi Beach', -33.890542, 151.274856, 4],"+
 "    ['Coogee Beach', -33.923036, 151.259052, 5],"+
 "    ['Cronulla Beach', -34.028249, 151.157507, 3],"+
 "    ['Manly Beach', -33.80010128657071, 151.28747820854187, 2],"+
 "    ['Maroubra Beach', -33.950198, 151.259302, 1]"+
 "  ];"+

 "   var map = new google.maps.Map(document.getElementById('map'), {"+
 "     zoom: 10,"+
 "     center: new google.maps.LatLng(-33.92, 151.25),"+
 "     mapTypeId: google.maps.MapTypeId.ROADMAP"+
 "   });"+

 "    var infowindow = new google.maps.InfoWindow();"+

 "    var marker, i;"+

 "    for (i = 0; i < locations.length; i++) {"+  
 "      marker = new google.maps.Marker({"+
 "        position: new google.maps.LatLng(locations[i][1], locations[i][2]),"+
 "        map: map"+
 "});"+

 "google.maps.event.addListener(marker, 'click', (function(marker, i) {"+
 "  return function() {"+
 "     infowindow.setContent(locations[i][0]);"+
 "      infowindow.open(map, marker);"+
 "     }"+
 "    })(marker, i));"+
 "   }"+
 "  </script>"+
 "</body>"+

 "</html>";
 myBrowser.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");
 myBrowser.getSettings().setJavaScriptEnabled(true);

 }




 }
 */

import org.json.JSONException;

import com.shoppinglist.R;
import com.shoppinglist.R.id;
import com.shoppinglist.R.layout;


import Utilities.GingerbreadJSFix;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Abdalla_test extends Activity
{

	WebView myBrowser;
	EditText Msg;
	Button btnSendMsg;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abdalla_test);
		myBrowser = (WebView) findViewById(R.id.mybrowser);
		
		String mapPath = "file:///android_asset/map.html";
		
		final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(
				this);
		
		//new GingerbreadJSFix().fixWebViewJSInterface(myBrowser, myJavaScriptInterface, "AndroidFunction", "testJS",this);		
		myBrowser.getSettings().setJavaScriptEnabled(true);
		myBrowser.loadUrl(mapPath);
		
	

		Msg = (EditText) findViewById(R.id.msg);
		btnSendMsg = (Button) findViewById(R.id.sendmsg);
		
		btnSendMsg.setOnClickListener(new Button.OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				String msgToSend = Msg.getText().toString();
				viewMap();
			}
		});

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
		public void showToast(String toast) throws JSONException 
		{

			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}

		@JavascriptInterface
		public void openAndroidDialog() throws JSONException 
		{

			AlertDialog.Builder myDialog = new AlertDialog.Builder(
					Abdalla_test.this);
			myDialog.setTitle("DANGER!");
			myDialog.setMessage("You can do what you want!");
			myDialog.setPositiveButton("ON", null);
			myDialog.show();
		}
		
		@JavascriptInterface
		public void viewInfo(String toast) throws JSONException 
		{
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}

	}
	
	//--------------------------------------------------
	
	/**
	 * Calling Methods of java script
	 */
	public void callFromActivity(String msg)
	{
		myBrowser.loadUrl("javascript:callFromActivity(\"" + msg
				+ "\")");
	}
	
	public void addLocation(String title,double lat , double longt)
	{
		myBrowser.loadUrl("javascript:addLocation(\"" + title
				+","+lat+","+longt
				+ "\")");
	}
	
	public void viewMap()
	{
		myBrowser.loadUrl("javascript:addLocation('ali',30.0150,31.5333)");
	}
	
	//--------------------------------------------------
}
