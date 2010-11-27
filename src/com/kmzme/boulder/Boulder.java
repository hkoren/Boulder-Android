package com.kmzme.boulder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Boulder extends Activity {
	
	private class URLClickListener implements OnClickListener {
		private String url;
		public URLClickListener(String url) {
			this.url= url;
		}
		public void onClick(View v) {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}		
	}

	public static final String TAG = "Boulder";
	private void URLClickButton(int id, String url) {
        Button button = (Button)findViewById(id);
        button.setOnClickListener(new URLClickListener(url));		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initBackground();
        
        initButtons();
        
    }
    
    public void initButtons() {
        URLClickButton(R.id.meetup_group,"http://www.meetup.com/Boulder-Android/");
        URLClickButton(R.id.follow_twitter,"http://twitter.com/BoulderAndroid");
        URLClickButton(R.id.dot_com,"http://BoulderAndroid.com");
        
        // Reader Button
        initReaderButton();

    }
    
    public void initReaderButton() {
    	Button button = (Button)findViewById(R.id.rss_reader);
        button.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
//				Intent goRead = new Intent(view.getContext(), RssMessageListActivity.class);
				Intent goRead = new Intent(view.getContext(), ReaderActivity.class);
				startActivity(goRead);
			}
        	
        });
    }
    public void initBackground() {
//    	ImageView image = (ImageView)findViewById(R.id.background_image);
//    	image.setBackgroundDrawable()
    	
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}