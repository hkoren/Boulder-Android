package com.kmzme.boulder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.TextView;

public class FeedUpdaterTask extends AsyncTask {

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	private void getFeedList() {
		String restURL = "http://app.boulderandroid.com/feeds.php" ;
		RestClient client = new RestClient(restURL);
		// client.AddParam("figure1", "15");
		// client.AddParam("figure2", "27"); 

		try {
			client.Execute(RequestMethod.GET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String response = client.getResponse();

		//TextResults.setText(response);
		try {
			String jsonContent = response.substring(0, response.length());
		    JSONArray jArray = new JSONArray(jsonContent);
	
			JSONObject jObject ;
			String temp = "" ; 
	
		    for (int j = 0; j < jArray.length() ; j++) { 
	
		    jObject  = jArray.getJSONObject(j) ;
	
		    temp =  temp +
	//		              jObject.getString("ID").toString() + " | " +
		    	jObject.getString("name").toString() + " | " +
		    	jObject.getString("RSS_URL").toString() + " | " +
		    	jObject.getString("WWW_URL").toString() + "\n\n";
	    }
	
		            //TextResults.setText(temp) ; 

		} catch (JSONException e) {
//		            TextResults.setText(e.getMessage()) ;
		}
	}
}
