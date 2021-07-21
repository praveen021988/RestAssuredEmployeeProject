/**
 * 
 */
package com.restapi.restassured.tests;

import org.json.simple.JSONObject;
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
public class TC_002_POST_Employee extends TestBase {
	
	@BeforeClass
	public void putEmployeeDetails() throws InterruptedException {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		requestParams=new JSONObject();
		requestParams.put("name","KnickKnight");
		requestParams.put("salary","1200");
		requestParams.put("age","29");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		response=httpRequest.request(Method.POST,"/create");
		Thread.sleep(3000);
	}
	
	@Test(priority=1)
	public void verifyResponseBody() {
		logger.info("**********Verifying the POST response body**********");
		responseBody=response.getBody().asString();
		logger.info("responseBody is===>"+ responseBody);
		Assert.assertTrue(responseBody!=null,"response body is null");
		
	}
	
	@Test(priority = 2)
	public void verifyStatusCode() {
		logger.info("**********Verifying the status code of POST request of employee**********");
		statusCode=response.getStatusCode();
		logger.info("statusCode is===>"+statusCode);
		Assert.assertEquals(statusCode,200,"status code mismatch");
	}
	
	@Test(priority=3)
	public void verifyStatusLine() {
		logger.info("**********Verifying the status line of the POST request of employee**********");
		statusLine=response.getStatusLine();
		logger.info("statusLine===>"+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK", "status line mismatched");
	}
	
	@Test(priority = 4)
	public void verifySuccessStatus() {
		logger.info("**********Verifying the status**********");
		status=response.jsonPath().get("status");
		logger.info("request status response is===>"+status);
		Assert.assertEquals(status, "success","status mismatched");
	}

	@Test(priority = 5)
	public void verifySuccessMessage() {
		logger.info("**********Verifying the message of the success POST request**********");
		successMessage=response.jsonPath().get("message");
		logger.info("request success message is===>"+successMessage);
		Assert.assertEquals(successMessage,"Successfully! Record has been added." ,"message is mismatched");
	}
}
