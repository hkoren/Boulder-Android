package com.kmzme.boulder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

public class FeedUpdaterTask extends AsyncTask {

	private ArticlesDbAdapter aDbHelper;
	private List<RssMessage> messages;
	private ReaderActivity readerActivity;	
	
	public FeedUpdaterTask(ReaderActivity readerActivity) {
		this.readerActivity = readerActivity;
	}

	@Override
	protected Object doInBackground(Object... params) {
		execute(); 
		return true;
	}
	
	public void execute() {

		try {

			int int_feed_id = 1 ;  // placeholder until figure out what feed_id is supposed to be (Pub Date ?) 

	        // get database ready to store RSS feeds 
	        aDbHelper = new ArticlesDbAdapter(readerActivity);  // need to get context instead of passing null !!!! 
	        aDbHelper.open();  	
        
			// do REST query to get list of recommended RSS Feed suppliers (JSON format) 
			String restURL = "http://app.boulderandroid.com/feeds.php" ;
			RestClient client = new RestClient(restURL);
					// client.AddParam("figure1", "15");  // example of passing a parameter 
			try {
				client.Execute(RequestMethod.GET);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String jsonContent = client.getResponse();
			Log.i(Boulder.TAG,"JSON content: " + jsonContent);
			if (jsonContent != null) {
			    JSONArray jArray = new JSONArray(jsonContent);
			    
			    // loop thru each suggested RSS Feed  
			    for (int j = 0; j < jArray.length() ; j++) {
			    	
			    	// parse JSON contents to obtain RSS Article URLs  
			    	JSONObject jObject  = jArray.getJSONObject(j) ; 
			    	String rssURL = jObject.getString("RSS_URL").toString()  ;
			    	// query each RSS URL to get RSS Article 
			    	
			    	Log.i(Boulder.TAG,"Rss URL: "+rssURL);
			    	
			    	
			    	RssBaseFeedParser parser = new RssBaseFeedParser(rssURL);
			    	try {
						messages = parser.parse();
					} catch (InvalidFeedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						continue;
					}
			    	for (RssMessage msg : messages){
			    		//String guid = "1" ;  // set guid to 1 for testing purposes 
			    		// check if article already exists
			    		Log.i(Boulder.TAG, "Getting message " + msg.getLink());
			    		Cursor myArticle = aDbHelper.fetchArticle(msg.getGuid());
			    		if (myArticle.getCount()==1  ) {
			    			// if so update record   	
			    			Log.i(Boulder.TAG, "Already Have Message, title: " + myArticle.getString(myArticle.getColumnIndex("title")));
//			    			aDbHelper.updateNote(msg.getGuid(), msg.getTitle(), 
//			    					msg.getTitle(), msg.getDescription(), int_feed_id, msg.getLink()) ;
			    			myArticle.close();
			    		} else {
			    			myArticle.close();
			    			// write results  	
			    			Log.i(Boulder.TAG, "Adding New Message");
			    			aDbHelper.createNote(msg.getGuid(), msg.getTitle(), 
			    					msg.getTitle(), msg.getDescription(), int_feed_id, msg.getLink()) ;
			    		}
			    	}
			    }
			    this.readerActivity.updateArticlesList(aDbHelper);
			}	    
		} catch (JSONException e) {
				Log.e("FeedUpdaterTask", "Error parsing JSON data "+e.toString());
				e.printStackTrace();
		}
//		catch (Exception e) {
//				e.printStackTrace();
//		}
		
	}

}
