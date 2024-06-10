package com.bcd.validations;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResponseValidations {
	ExtentReports ExtentReports;
	
	@BeforeTest
	public void CreateReport(){
		
		ExtentReports = new ExtentReports();
		ExtentSparkReporter ExtentSparkReporter = new ExtentSparkReporter("./output/ReponseChecking.html");
		ExtentReports.attachReporter(ExtentSparkReporter);
	}

	@AfterTest
	public void Report()
	{
	ExtentReports.flush();	
	}
	@Test
	public void HeaderValidation() {
		
		ExtentTest ExtentTest = ExtentReports.createTest("This is Response Validation Testing");
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignDevice("Windows");
		
		Response response = RestAssured.given()
		.baseUri("http://localhost:3000/goodies")
		.when().get()
		.then().extract().response();
		
    	System.out.println("Response:" +response.body().asPrettyString());
		ExtentTest.log(Status.INFO,"Response : " + response.body().asPrettyString());
		
		int GetStatus = response.getStatusCode();
		int ExpectedStatusCode = 200;
	//	Assert.assertTrue(GetStatus,ExpectedStatusCode);
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("Status code: "+GetStatus);
		ExtentTest.log(Status.PASS,"Data is successfully fetched and the status code is: "+response.getStatusCode());
		ExtentTest.log(Status.INFO,"Status line is: "+response.getStatusLine());
		
		int size=response.getBody().asString().length();
		System.out.println("Size:" +size);
		ExtentTest.log(Status.INFO, "The size of the response: " +size);
		
		int size_array = response.jsonPath().getList("amazon").size();
		System.out.println("Number of items : "+size_array);
		ExtentTest.log(Status.INFO,"Number of items: "+size_array);
		
		String name;
	
		for (int i =0 ;i<size_array ; i++) {
			 name =response.jsonPath().getString("amazon["+i+"].name");
			 ExtentTest.log(Status.INFO,"The element num " +i+ " is: " +name);
		System.out.println(name);
		}
		}
	
	
}
