package com.kmzme.boulder;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class RssMessageListActivity extends ListActivity {
	
	private List<RssMessage> messages;
	
    @Override
    public void onCreate(Bundle icicle) {
       super.onCreate(icicle);
        setContentView(R.layout.rssfeed);
        
		String feedUrlString = "http://feeds.dailycamera.com/mngi/rss/CustomRssServlet/21/218121.xml";
        loadFeed(feedUrlString);
   
    }

	private void loadFeed(String feedUrlString){
		
    	try{
	    	RssBaseFeedParser parser = new RssBaseFeedParser(feedUrlString);
	    	messages = parser.parse();
	    	List<String> titles = new ArrayList<String>(messages.size());
	    	for (RssMessage msg : messages){
	    		titles.add(msg.getTitle());  // gets Title of feed 
	    	}
	    	ArrayAdapter<String> adapter = 
	    		new ArrayAdapter<String>(this, R.layout.row,titles);
	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e("Boulder-Android-RSS",t.getMessage(),t);
    	}
    }
    
 }