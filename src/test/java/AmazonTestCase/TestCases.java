package AmazonTestCase;

import java.util.Scanner;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.bcd.Data.Payload;
import com.bcd.Data.RandomData;
import com.bcd.EndPoints.Amazon.EndPoints;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

//import io.restassured.mapper.ObjectMapper;

public class TestCases {
	
	ExtentReports ExtentReports;
	
	@BeforeTest
	public void generateReports() {
		ExtentReports = new ExtentReports();
		ExtentSparkReporter ExtentSparkReporter =new ExtentSparkReporter("./output/Result.html") ;
		ExtentReports.attachReporter(ExtentSparkReporter);
	}
	@AfterTest
	public void SaveAndReport() {
		ExtentReports.flush();
	}
	@Test(enabled = false)
//	(dataProviderClass= RandomData.class, dataProvider = "dataProvide")
		public void postTestCase(String Name1, int Number , int Price) throws JsonProcessingException
	{	
		ExtentTest ExtentTest = ExtentReports.createTest("This is API Testing");
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignCategory("Api Testing");
		
		ExtentTest.log(Status.INFO, "Adding the details");
	Payload p = new Payload();
	System.out.println("putTestCase");
	p.setName(Name1) ; 
	p.setNumber(Number);
	p.setPrice(Price);

	ObjectMapper mapper = new ObjectMapper();
	String jsonInput = mapper.writeValueAsString(p);
	
	Response output = EndPoints.PostMethod(jsonInput);
	
	System.out.println("Status code: "+output.getStatusCode());
	System.out.println("Body :"+output.getBody().toString());

	ExtentTest.log(Status.PASS, "Added the details successfully");

	ExtentTest.log(Status.INFO, "Details added are"+ output.getBody().asString());
	}
	@Test(enabled = false)
	public void putMethod() throws JsonProcessingException
	{
		ExtentTest ExtentTest = ExtentReports.createTest("Updating single detail");
		ExtentTest.assignAuthor("ushe");
		ExtentTest.assignCategory("This is API Testing");
		ExtentTest.log(Status.INFO,"We are updating an item");
		Scanner sc=new Scanner(System.in);
		Response output = EndPoints.getData();
		int size = output.getBody().asString().length();
		
		System.out.println("Enter the id within "+size+" that you want to update");
		int id = sc.nextInt();
		ExtentTest.log(Status.INFO,"The Id of the item is :" +id);
		
		System.out.println("Enter the number");
		int Number3 = sc.nextInt();

		System.out.println("Enter the Name");
		String name3 = sc.next();
		System.out.println("Enter the price");
		int price3 = sc.nextInt();
		
		Payload p = new Payload();
		p.setName(name3);
		p.setNumber(Number3);
		p.setPrice(price3);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonOutput = mapper.writeValueAsString(p);
		
		Response output = EndPoints.PutMethod(jsonOutput,id);
		System.out.println(output.body().asString());
		ExtentTest.log(Status.PASS,"The details are upated.");
		ExtentTest.log(Status.INFO,"The details entered are" +output.body().asString());
	}
	
	@Test(enabled=false)
	public void GetAllDetails() {
		ExtentTest ExtentTest = ExtentReports.createTest("This is APITest to get the details");
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignDevice("Windows");
		Response output = EndPoints.getData();
		System.out.println("Output: " +output.asString());
		ExtentTest.log(Status.INFO, "The details are:" + output.body().asString());
		ExtentTest.log(Status.PASS ,"The details are displayed correctly");
		}
	
	
	@Test(enabled = true ,priority = 1)
	public void GetPerticularInfo() {
		ExtentTest ExtentTest = ExtentReports.createTest("This is APITesting to get details of perticular details");
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignDevice("Windows");
		
		Scanner sc=new Scanner (System.in);
		System.out.printf("Enter the id of which you want to check details of: ");
		int id= sc.nextInt();
		Response output = EndPoints.GetPerticularDetail(id);
		
		ExtentTest.log(Status.INFO,"The details of id: "+id+ "are: "+output.body().asPrettyString());
		ExtentTest.log(Status.PASS,"The details are successfully fetched and displayed");
		System.out.println(output.asString());
	}
	
	@Test(enabled=true, priority = 2)
	public void DelInfo() {
		
		ExtentTest ExtentTest = ExtentReports.createTest("This is APITesting to delete an item");
		
		ExtentTest.assignAuthor("Ushe");
		ExtentTest.assignDevice("Windows");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of the item which you want to delete the details of: ");
		int id = sc.nextInt();
		
		Response output = EndPoints.GetPerticularDetail(id);
		EndPoints.DeleteData(id);
		
		ExtentTest.log(Status.PASS,"Details are deleted successfully");
		System.out.println("Deleted the data " +output.asString());
		ExtentTest.log(Status.INFO, "The item deleted is: "+output.getBody().asPrettyString());
	}
	
}
