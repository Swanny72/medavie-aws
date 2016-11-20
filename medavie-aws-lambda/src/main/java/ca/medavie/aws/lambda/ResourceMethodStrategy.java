package ca.medavie.aws.lambda;

public interface ResourceMethodStrategy<Y> {

	public Y handleRequest(LambdaRequest request) throws MedavieAWSException;
	
}
