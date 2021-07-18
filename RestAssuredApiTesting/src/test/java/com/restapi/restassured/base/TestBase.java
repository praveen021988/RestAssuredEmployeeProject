/**
 * 
 */
package com.restapi.restassured.base;
import org.codehaus.groovy.ast.GenericsType.GenericsTypeName;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Shravani Maande
 *
 */
public class TestBase {

	public Response response;
	public RequestSpecification httpRequest;
	
	public Logger logger;
	
	public String statusLine;
	public long statusCode;
	public String responseBody;
	public String serverType;
	public long responseTime;
	public String contentType;
	public String contentEncoding;
	
	@BeforeClass
	public void setup() {
		
		logger=Logger.getLogger(getClass().getSimpleName());
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		
		
	}
	
	
}
