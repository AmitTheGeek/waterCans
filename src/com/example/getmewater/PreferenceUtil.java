package com.example.getmewater;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtil {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public PreferenceUtil(Context ctx) {
		sp = PreferenceManager.getDefaultSharedPreferences(ctx);
		editor = sp.edit();
	}

	public String getName(){
		String phonenumber = sp.getString("name", null);
		return phonenumber;
	}
	
	public void setName(String name){
		editor.putString("name", name);
		editor.commit();
	}
	
	private String getFlat(){
		String phonenumber = sp.getString("flat", null);
		return phonenumber;
	}
	
	public void setFlat(String flat){
		editor.putString("flat", flat);
		editor.commit();
	}
	

	private String getStreetName() {
		String phonenumber = sp.getString("streetname", null);
		return phonenumber;
	}

	
	public void setStreetName(String streetname) {
		editor.putString("streetname", streetname);
		editor.commit();
	}
	
	public void setLandmark(String landmark){
		editor.putString("landmark", landmark);
		editor.commit();
	}
	
	private String getLandmark(){
		String landmark = sp.getString("landmark", null);
		return landmark;
	}
	
	public void setCity(String city){
		editor.putString("city", city);
		editor.commit();
	}
	
	private String getCity(){
		String city = sp.getString("city", null);
		return city;
	}
	
	public String getPhone(){
		String city = sp.getString("phone", null);
		return city;
	}
	
	public void setPhone(String phone){
		editor.putString("phone", phone);
		editor.commit();
	}
	
	
	public String getAddress(){
		String address = getFlat() + ", " + getStreetName() + ", " + getLandmark() + ", " + getCity();
		return address;
	}
	
	public boolean isFirstTime(){
		return sp.getBoolean("firstime", true);
	}
	
	public void setFirstTimeOver(){
		editor.putBoolean("firstime", false);
		editor.commit();
	}
	
	
}
