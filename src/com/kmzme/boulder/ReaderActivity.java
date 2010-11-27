package com.kmzme.boulder;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;



public class ReaderActivity extends ListActivity {

	FeedUpdaterTask updateTask = new FeedUpdaterTask(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		updateTask.execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
		
		// Triggered FeedUpdaterTask automatically 
		// when the ReaderActivity starts and an update hasn’t occurred recently.
		
		
	}

	public void updateArticlesList(ArticlesDbAdapter aDbHelper) {
		Log.i(Boulder.TAG, "Updating Articles List");
		ArrayList<RssMessage> myArticles = aDbHelper.fetchAllArticles() ;
    	List<String> titles = new ArrayList<String>(myArticles.size());
    	for (RssMessage msg : myArticles){
    		Log.i(Boulder.TAG, "Adding article " + msg.getTitle());
    		titles.add(msg.getTitle());  // gets Title of feed 
    	}
    	ArrayAdapter<String> adapter = 
    		new ArrayAdapter<String>(this, R.layout.row,titles);
    	this.setListAdapter(adapter);  		
	}

	
}
