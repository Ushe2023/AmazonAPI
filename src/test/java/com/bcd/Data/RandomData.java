package com.bcd.Data;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

public class RandomData {


	@DataProvider
	public Object[][] dataProvide() {
		
		Faker faker = new Faker();
		
		Object[][] data=
			{			
					{
					faker.book().title(),
					faker.number().numberBetween(50, 100),
					faker.number().randomDigit()
					},
					{
						faker.book().title(),
						faker.number().numberBetween(50, 100),
						faker.number().numberBetween(3, 3000)
						},
					{
						faker.book().title(),
						faker.number().numberBetween(50, 100),
						faker.number().randomDigitNotZero()
						},
					
			};
		return data;
	}
}
