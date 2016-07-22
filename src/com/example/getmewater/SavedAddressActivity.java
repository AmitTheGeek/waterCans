package com.example.getmewater;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SavedAddressActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_address);
		savedAddress();
	}


	
	public void savedAddress() {
		TextView name,phone,address; 
		PreferenceUtil pf = new PreferenceUtil(this);
		
		 name = (TextView) findViewById(R.id.saved_name);
		 phone = (TextView) findViewById(R.id.saved_phone);
		 address = (TextView) findViewById(R.id.saved_address);
		 name.setText(pf.getName().toString());
		 phone.setText(pf.getPhone().toString());
		 address.setText(pf.getAddress().toString());
		 address.setTextColor(Color.parseColor("#444444"));
		
	}
	
   public void updateActivity (View view) {
	   Intent myIntent = new Intent(SavedAddressActivity.this, LocationActivity.class);
   	   startActivity(myIntent);
   }
}
