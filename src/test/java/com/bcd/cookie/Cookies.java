package com.bcd.cookie;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.util.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
//import junit.framework.Assert;

public class Cookies {

	ExtentReports ExtentReports;
	@BeforeTest
	public void createReport() {
		ExtentReports = new ExtentReports();
			ExtentSparkReporter ExtentSparkReporter = new ExtentSparkReporter("./output/CookieResults.html");
			ExtentReports.attachReporter(ExtentSparkReporter);
		}
	
	@AfterTest
	public void push(){
		ExtentReports.flush();
		
	}
	@Test
	public void Google() {
		
		ExtentTest ExtentTest =  ExtentReports.createTest("This is API Cookie Testing");
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignDevice("Windows");
		
		Response output = RestAssured.given()
							.baseUri("https://www.google.com/")
							
							.when().get()
							.then().extract().response();
		
		String PrevCookie1 = output.cookie("AEC");
		System.out.println("AEC Cookie: "+PrevCookie1);
		ExtentTest.log(Status.INFO,"AEC Cookie1=" +PrevCookie1);
		
		String PrevCookie2 = output.cookie("NID");
		System.out.println("NID Cookie:"+PrevCookie2);
		ExtentTest.log(Status.INFO,"AEC Cookie1=" +PrevCookie2);
		
		Response output2 = RestAssured.given()
				.baseUri("https://www.google.com/")
				
				.when().get()
				.then().extract().response();
		ExtentTest.log(Status.INFO,"StatusCode:" +output.getStatusCode());
		int ActualStatusCode = +output.getStatusCode();
		int ExpectedStatusCode = 200;
		
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCode);
		
		System.out.println("Status Code:"+output.getStatusCode());
		String CurrentCookie1 = output2.cookie("AEC");
		System.out.println("AEC Cookie: "+CurrentCookie1);
		ExtentTest.log(Status.INFO,"AEC Cookie1=" +CurrentCookie1);
		
		String CurrentCookie2 = output2.cookie("NID");
		System.out.println("NID Cookie: "+CurrentCookie2);
		ExtentTest.log(Status.INFO,"AEC Cookie1=" +CurrentCookie2);
		
		if ((PrevCookie1==CurrentCookie1) || (PrevCookie2== CurrentCookie2))
		{
			System.out.println("Website is not secure");	
			ExtentTest.log(Status.FAIL,"Website is not secure" );
		}
		 else
		{
			System.out.println("Website is secure");
			ExtentTest.log(Status.PASS,"Website is secure");
		}

	}
}
