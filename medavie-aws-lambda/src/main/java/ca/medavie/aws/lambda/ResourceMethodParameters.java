package ca.medavie.aws.lambda;

/**
 * Object that has parameter data from a LambdaRequest mapped to concrete Java Objects
 * The bodyClass will be populated from the Json body payload. For operations with no 
 * body payloads, provide the Void type as the X generic and bodyClass.
 * The parameterClass is a custom class for the query and request parameters provided
 * 
 * @author bcdswan
 *
 * @param <X>
 * @param <Y>
 */
public class ResourceMethodParameters<X, Y>{

	private Class<X> bodyClass;
	
	private Class<Y> parameterClass;
	
	private Y parameters;
	
	private X body;
	
	public ResourceMethodParameters( Class<X> bodyClass, Class<Y> parameterClass ) {
		this.bodyClass = bodyClass;
		this.parameterClass = parameterClass;
	}

	public Class<X> getBodyClass() {
		return bodyClass;
	}

	public void setBodyClass(Class<X> bodyClass) {
		this.bodyClass = bodyClass;
	}

	public Class<Y> getParameterClass() {
		return parameterClass;
	}

	public void setParameterClass(Class<Y> parameterClass) {
		this.parameterClass = parameterClass;
	}

	public Y getParameters() {
		return parameters;
	}

	public void setParameters(Y parameters) {
		this.parameters = parameters;
	}

	public X getBody() {
		return body;
	}

	public void setBody(X body) {
		this.body = body;
	}
}
