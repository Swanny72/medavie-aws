package ca.medavie.aws.lambda;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractResourceMethodStrategy<X, Y, Z> implements ResourceMethodStrategy<Y> {

	private Class<X> inputClass;
	private Class<Z> parameterClass;
	
	public AbstractResourceMethodStrategy(Class<X> inputClass, Class<Z> parameterClass) {
		this.inputClass = inputClass;
		this.parameterClass = parameterClass;
	}

	public final Y handleRequest(LambdaRequest request) throws MedavieAWSException {
		X inputData = null;
		System.out.println("handleRequest");
		Z parameterData = null;
		if (request != null) {
			if (request.getBody() != null ) {
				byte[] raw = DatatypeConverter.parseBase64Binary(request.getBody());
				String decoded = new String(raw, StandardCharsets.UTF_8);				
				inputData = inputData(decoded);
			}
			parameterData = parametersFromMaps(request);
	
			return this.handleRequest(inputData, parameterData);
		}
		else {
			return null;
		}
		
	}
	
	protected abstract Y handleRequest(X inputData, Z params) throws MedavieAWSException;
	

	public X inputFromJson(String functionBody) {
		if (functionBody == null || functionBody.length() < 1 || "{}".equals(functionBody)) {
			return null;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return (X)gson.fromJson(functionBody, inputClass.getClass());	
	}
	
	public X inputData(String body) throws MedavieAWSException {
		X mappedBody = null;
		
		Gson gson = new GsonBuilder().create();
		mappedBody = gson.fromJson(body, inputClass);
		
		return mappedBody;
	}
	public Z parametersFromMaps(LambdaRequest request) throws MedavieAWSException {
		BeanUtilsParameterMapper<Z> mapper = new BeanUtilsParameterMapper<Z>(parameterClass);
		return mapper.mapRequestParameters(request);
	}
}
