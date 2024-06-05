package AmazonTestCase;

import java.util.Scanner;

import org.testng.annotations.Test;
import com.bcd.Data.Payload;
import com.bcd.Data.RandomData;
import com.bcd.EndPoints.Amazon.EndPoints;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

//import io.restassured.mapper.ObjectMapper;

public class TestCases {
	@Test	(enabled = false)
//	(dataProviderClass= RandomData.class, dataProvider = "dataProvide"  )
		public void postTestCase(String Name1, int Number , int Price) throws JsonProcessingException
	{	
	Payload p = new Payload();
	System.out.println("putTestCase");
	p.setName(Name1) ; 
	p.setNumber(Number);
	p.setPrice(Price);

	ObjectMapper mapper = new ObjectMapper();
	String jsonInput = mapper.writeValueAsString(p);
	
	Response output = EndPoints.PostMethod(jsonInput);
	
	System.out.println("Status code: "+output.getStatusCode());
	System.out.println("Body :"+output.body());
	}
	@Test
	public void putMethod() throws JsonProcessingException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the id you want to update");
		int id = sc.nextInt();
		
		
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
		
		EndPoints.PutMethod(jsonOutput,id);
		
	}
	
	@Test(enabled=false)
	public void GetAllDetails() {
		
		Response output = EndPoints.getData();
		System.out.println("Output: " +output.asString());
		}
	@Test(enabled = false)
	public void GetPerticularInfo() {
		int id = 0;
		Scanner sc=new Scanner (System.in);
		System.out.printf("Enter the id of which you want to check details of: ");
		id= sc.nextInt();
		Response output = EndPoints.GetPerticularDetail(id);
		System.out.println(output.asString());
		
	}
	@Test(enabled=false)
	public void DelInfo() {
		
		int id;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of the item which you want to delete the details of: ");
		id = sc.nextInt();
		Response output = EndPoints.GetPerticularDetail(id);
		EndPoints.DeleteData(id);
		System.out.println("Deleted the data " +output.asString());
	}
	
}
