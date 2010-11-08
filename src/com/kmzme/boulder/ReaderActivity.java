package com.kmzme.boulder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;



public class ReaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
		
		// Triggered FeedUpdaterTask automatically 
		// when the ReaderActivity starts and an update hasn’t occurred recently.
		
		
	}

	
}
