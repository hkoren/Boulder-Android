package com.kmzme.boulder;

import java.net.URL;

import org.xml.sax.SAXException;

import android.util.Log;

public class InvalidFeedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFeedException(Exception e, URL feedUrl) {
		
		Log.e(Boulder.TAG,"Feed Error: URL: "+feedUrl+" Error: " + e.getMessage()) ; 
		// TODO Auto-generated constructor stub
	}
	
	

}
