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
	private void URLClickButton(int id, String url) {
        Button button = (Button)findViewById(id);
        button.setOnClickListener(new URLClickListener(url));		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RestClient client = new RestClient(restURL);
        
        initBackground();
        
        initButtons();
    }
    
    public void initButtons() {
        URLClickButton(R.id.meetup_group,"http://www.meetup.com/Boulder-Android/");
        URLClickButton(R.id.follow_twitter,"http://twitter.com/BoulderAndroid");
        URLClickButton(R.id.dot_com,"http://BoulderAndroid.com");

    }
    
    public void initBackground() {
//    	ImageView image = (ImageView)findViewById(R.id.background_image);
//    	image.setBackgroundDrawable()
    	
    
    }

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}