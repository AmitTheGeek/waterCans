package com.example.getmewater;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LocationActivity extends FragmentActivity implements  LocationListener {

	GoogleMap googleMap;
	String address;
	
	boolean isLocationUpdated;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
 
        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        } else {  
                                    
            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        } 
 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 
    
    public void getMyLocationAddress(double lattitude, double longitude) {
        
        Geocoder geocoder= new Geocoder(this, Locale.ENGLISH);
         
        try {
               
              //Place your latitude and longitude
              List<Address> addresses = geocoder.getFromLocation(lattitude, longitude, 1);
              
              String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
              String subLocality = addresses.get(0).getSubLocality();
              String city = addresses.get(0).getLocality();
              String state = addresses.get(0).getAdminArea();
              String country = addresses.get(0).getCountryName();
              String postalCode = addresses.get(0).getPostalCode();
              String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
              
          
              
              ((EditText)findViewById(R.id.editInp2)).setText(address);
              ((EditText)findViewById(R.id.editInp3)).setText(subLocality);
              ((EditText)findViewById(R.id.editInp4)).setText(city);
              
              for(int i = 0; i < addresses.size(); i++) {
                  System.out.println("address " + addresses.get(i));
              }          
        } 
        catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
                 Toast.makeText(getApplicationContext(),"Could not get address..!", Toast.LENGTH_LONG).show();
        }
    }
    
    public void updateAddress(View v){
        EditText editName =  ((EditText)findViewById(R.id.editName));
        EditText editFlat =  ((EditText)findViewById(R.id.editInp1));
        EditText editstreet =  ((EditText)findViewById(R.id.editInp2));
        EditText editLandmark =  ((EditText)findViewById(R.id.editInp3));
        EditText editCity =  ((EditText)findViewById(R.id.editInp4));
        EditText editPhone = ((EditText)findViewById(R.id.Phone));
        
        
        if(isEmpty(editName)){
        	System.out.println(isEmpty(editName));
        	Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(isEmpty(editFlat)){
        	Toast.makeText(this, "Please enter flat", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(isEmpty(editstreet)){
        	Toast.makeText(this, "Please enter street", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(isEmpty(editLandmark)){
        	Toast.makeText(this, "Please enter landmark", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(isEmpty(editCity)){
        	Toast.makeText(this, "Please enter city", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        if(isEmpty(editPhone)){
        	Toast.makeText(this, "Please enter phone", Toast.LENGTH_SHORT).show();
        	return;
        }
        
        PreferenceUtil pf = new PreferenceUtil(this);
        pf.setName(editName.getText().toString());
        pf.setFlat(editFlat.getText().toString());
        pf.setLandmark(editLandmark.getText().toString());
        pf.setCity(editCity.getText().toString());
        pf.setStreetName(editstreet.getText().toString());
        pf.setPhone(editPhone.getText().toString());
        pf.setFirstTimeOver();
        
        Intent myIntent = new Intent(this, SavedAddressActivity.class);
    	startActivity(myIntent);
        

    }
    
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

	@Override
	public void onLocationChanged(Location location) {
	  if(!isLocationUpdated) {
			getMyLocationAddress(location.getLatitude(), location.getLongitude());
		    isLocationUpdated = true;
		}
	}
	

 	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	} 
}