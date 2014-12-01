package functions;

import model.Place;
import model.PlacesList;
import android.location.Location;
import android.webkit.WebView;

import com.google.android.gms.maps.model.LatLng;


public class JavaScriptMethodCaller
{

	public static void addPlacesToMap(WebView view , PlacesList p)
	{
		if (p.results != null)
		{
			// loop through all the places
			for (Place place : p.results)
			{
				// Geopoint to place on map
				LatLng pos = new LatLng(place.geometry.location.lat,
						place.geometry.location.lng);

				addLocation(view,place.name,pos.latitude, pos.longitude,place.reference);

			}
		}
	}
	
	/**
	 * Helper Method
	 */
	private static void addLocation(WebView view , String title,double lat , double longt ,String ref )
	{
		view.loadUrl("javascript:addLocation('"+title+"',"+lat+","+longt+",'"+ref+"')");
	}
	
	//-----------------------------------------------------
	
	public static void addCurrentPlaceToMap(WebView view , Location loc)
	{
		view.loadUrl("javascript:addDraggableMarker('"+loc.getProvider()+"',"+loc.getLatitude()+","+loc.getLongitude()+")");
	}
}
