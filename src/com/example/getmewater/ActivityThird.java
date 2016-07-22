package com.example.getmewater;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.widget.EditText;
public class ActivityThird extends Activity {

	private TextView loc;
	private Button button1;
	private Button button2;
	private String URL = "http://192.168.0.104/android_connect/MyApp.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		String locality = pref.getString("locality", "false");
		loc=(TextView)findViewById(R.id.locality_text_fill);
		loc.setText(locality);
		String str = pref.getString("address", "false");
		Log.d("VALUE OF ADDRESS IS",str);
		if(!(pref.getString("address", "false").equals("false"))) {
			EditText text = (EditText)findViewById(R.id.address_text_fill);
    		String str_val = pref.getString("address", "");
			text.setText(str_val);
		}
		if(!(pref.getString("landmark", "false").equals("false"))) {
			EditText text = (EditText)findViewById(R.id.landmark_text_fill);
    		String str_val = pref.getString("landmark", "");
			text.setText(str_val);
		}
		if(!(pref.getString("phone", "false").equals("false"))) {
			EditText text = (EditText)findViewById(R.id.phone_text_fill);
    		String str_val = pref.getString("phone", "");
			text.setText(str_val);
		}
		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		EditText text=(EditText)findViewById(R.id.address_text_fill);
        		String str_val = text.getText().toString();
        		SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        		SharedPreferences.Editor edit = pref.edit();
        		edit.putString("address", str_val);
        		edit.commit();
        		text=(EditText)findViewById(R.id.landmark_text_fill);
        		str_val = text.getText().toString();
        		edit = pref.edit();
        		edit.putString("landmark", str_val);
        		edit.commit();
        		text=(EditText)findViewById(R.id.phone_text_fill);
        		str_val = text.getText().toString();
        		edit = pref.edit();
        		edit.putString("phone", str_val);
        		edit.commit();
        		new AddNewPrediction().execute(pref.getString("phone", "false"), pref.getString("locality", "false"), 
        				                       pref.getString("address", "false"), pref.getString("landmark", "false"),
        				                       Integer.toString(pref.getInt("amount", 0)),
        				                       Integer.toString(pref.getInt("quantity", 0)));
        		//Intent i = new Intent(ActivityThird.this, ActivityForth.class);
                 //startActivity(i);
        		//text = (TextView)findViewById(R.id.print_text);
        		//text.setText(str_val);
        	}
        });
	
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		EditText text=(EditText)findViewById(R.id.address_text_fill);
        		String str_val = text.getText().toString();
        		SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        		SharedPreferences.Editor edit = pref.edit();
        		edit.putString("address", str_val);
        		edit.commit();
        		text=(EditText)findViewById(R.id.landmark_text_fill);
        		str_val = text.getText().toString();
        		edit = pref.edit();
        		edit.putString("landmark", str_val);
        		edit.commit();
        		text=(EditText)findViewById(R.id.phone_text_fill);
        		str_val = text.getText().toString();
        		edit = pref.edit();
        		edit.putString("phone", str_val);
        		edit.commit();
             	Intent i = new Intent(ActivityThird.this, ActivityForth.class);
                startActivity(i);
        		//text = (TextView)findViewById(R.id.print_text);
        		//text.setText(str_val);
        	}
        });
         
	}
		private class AddNewPrediction extends AsyncTask<String, Void, Void> {

	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();

	        }

	        @Override
	        protected Void doInBackground(String... arg) {
	            // TODO Auto-generated method stub
	            String phone_no = arg[0];
	            String locality = arg[1];
	            String address = arg[2];
	            String landmark = arg[3];
	            String amount = arg[4];
	            String quantity = arg[5];
	            // Preparing post params
	         // Preparing post params
	            Log.d("CHECK VALUE",phone_no);
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("phone_no", phone_no));
	            params.add(new BasicNameValuePair("locality", locality));
	            params.add(new BasicNameValuePair("address", address));
	            params.add(new BasicNameValuePair("landmark", landmark));
	            params.add(new BasicNameValuePair("amount", amount));
	            params.add(new BasicNameValuePair("quantity", quantity));
                Log.d("HELLLLLLLLLLLO","HELLL");
	            SeviceHandler serviceClient = new SeviceHandler();
                Log.d("HIIIIIIII","HIIII");
	            String json = serviceClient.makeServiceCall(URL,
	                    SeviceHandler.GET, params);
                Log.d("SUCCESSFULLY CALLING THE FUNCTION","YEYEYYYYY");
	            Log.d("Create Prediction Request: ", "> " + json);

	            if (json != null) {
	                try {
	                    JSONObject jsonObj = new JSONObject(json);
	                    boolean error = jsonObj.getBoolean("error");
	                    // checking for error node in json
	                    if (!error) {
	                    	// new category created successfully
	                        Log.e("Prediction added successfully ",
	                                "> " + jsonObj.getString("message"));
	                    } else {
	                        Log.e("Add Prediction Error: ",
	                                "> " + jsonObj.getString("message"));
	                    }

	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }

	            } else {
	                Log.e("JSON Data", "JSON data error!");
	            }
	            return null;
	        }

	        @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	        }
	   }
}
