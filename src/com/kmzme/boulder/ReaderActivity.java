package com.kmzme.boulder;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;



public class ReaderActivity extends Activity {

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

	public void updateArticlesList() {

        ArticlesDbAdapter aDbHelper = new ArticlesDbAdapter(this);  // need to get context instead of passing null !!!! 
        aDbHelper.open(); 
        Cursor myArticles = aDbHelper.fetchAllArticles() ;
        
		
	}

	
}
