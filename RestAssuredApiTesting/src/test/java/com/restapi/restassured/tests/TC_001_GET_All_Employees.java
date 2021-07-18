/**
 * 
 */
package com.restapi.restassured.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/**
 * @author Shravani Maande
 *
 */
public class TC_001_GET_All_Employees extends TestBase{
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void verifyResponseBody() {
		
		logger.info("**********Verifying the response body**********");
		responseBody=response.getBody().asString();
		logger.info("Response body is ===>"+responseBody);
		Assert.assertTrue(responseBody!=null,"reponse body is null");
	}
	
	@Test(priority = 2)
	public void verifyStatusCode() {
		logger.info("**********Verifying the Status Code");
		statusCode=response.statusCode();
		logger.info("Status code is :===>"+statusCode);
		Assert.assertEquals(statusCode, 200,"status code mismatch");
	}
	
	@Test(priority=3)
	public void verifyStatusLine() {
		logger.info("**********Verifying the status line**********");
		statusLine=response.statusLine();
		logger.info("Status Line is : ===>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","statusLine is mismatched");
	}
	
	@Test(priority = 4)
	public void verifyResponseTime() {
		logger.info("**********Verifying response time**********");
		responseTime=response.getTime();
		logger.info("Reponse time is : ===>"+ responseTime);
		Assert.assertTrue(responseTime<3000, "respone time is >2500");
	}
}
