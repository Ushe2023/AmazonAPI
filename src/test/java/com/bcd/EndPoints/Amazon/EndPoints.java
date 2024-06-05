package com.bcd.EndPoints.Amazon;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndPoints {
	
	public static Response PostMethod(String info){
		
		 Response output =RestAssured.given()
		.baseUri(Routes.PostURL)
		.contentType("application/json")
		.body(info.toString())
		.when().post()
		
		.then().extract().response();
		 
		 return output;
	}

	public static Response PutMethod(String info,int id)
	{
		Response output = RestAssured.given()
		.baseUri(Routes.BaseURL)
		.contentType("application/json")
		.body(info.toString())
		.pathParam("id",id)
		
		.when().put(Routes.PutURL)
		.then().extract().response();
		
		return output;
	}
	public static Response getData() {
		Response output = RestAssured.given()
		.baseUri(Routes.GetURL)
		
		.when().get()
		
		.then().extract().response();
		
		return output;
	}
	
	public static Response GetPerticularDetail(int id) {
		Response output = RestAssured.given()
		.baseUri(Routes.BaseURL)
		.pathParam("id",id)
		
		.when().get(Routes.GetPerticular)
		
		.then().extract().response();
		
		return output;
	}
	
	public static Response DeleteData(int id) {
		
		Response output = RestAssured.given()
		.baseUri(Routes.BaseURL)
		.pathParam("id",id)
		
		.when().delete(Routes.DeleteURL)
		.then().extract().response();
		
		return output;
	}
}
