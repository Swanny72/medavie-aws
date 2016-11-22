package ca.medavie.aws.lambda.strategy;

import org.junit.Rule;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.medavie.aws.lambda.BaseLambdaHandler;
import ca.medavie.aws.lambda.LambdaRequest;
import ca.medavie.aws.lambda.ResourceMethodKey;

public class TestStrategyPattern {
	@Rule
	public ResourceFile res = new ResourceFile("/lambda1.json");

	@Test
	public void test() throws Exception{
		TestingLambdaHandler handler = new TestingLambdaHandler();
		LambdaRequest request = lambda(res);
		OutputDataOne output = (OutputDataOne)handler.handleRequest(request, null);
		System.out.println(output);
		
	}
	
	public LambdaRequest lambda(ResourceFile res) throws Exception{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(res.getContent(), LambdaRequest.class);
	}

}
