package ca.medavie.aws.lambda.strategy;

import ca.medavie.aws.lambda.BaseLambdaHandler;

public class TestingLambdaHandler extends BaseLambdaHandler {

	public TestingLambdaHandler() {
		super(TestAppConfig.class);
		
	}

}
