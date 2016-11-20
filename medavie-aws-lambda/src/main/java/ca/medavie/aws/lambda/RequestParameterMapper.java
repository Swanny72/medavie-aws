package ca.medavie.aws.lambda;

public interface RequestParameterMapper<T> {

	public T mapRequestParameters(LambdaRequest request) throws MedavieAWSException;
	
}
