package com.bcd.EndPoints.Amazon;

public class Routes {

	public static String BaseURL = "http://localhost:3000";
	
	public static String GetURL = BaseURL + "/amazon";
	public static String GetPerticular = BaseURL + "/amazon/{id}";
	public static String PostURL ="http://localhost:3000/amazon";
//	= BaseURL + "/amazon";
	
	public static String PutURL = BaseURL + "/amazon/{id}";
	public static String DeleteURL = BaseURL + "/amazon/{id}";
	
//	public static String BASE_URL = "http://localhost:3000";//it is made public so that these variables can be accessed by all 
//	classes. static variables can be accessed directly.
	
//	public static String POST_URL = BASE_URL + "/trains";
	}
