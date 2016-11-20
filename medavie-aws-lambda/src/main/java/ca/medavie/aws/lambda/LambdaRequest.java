package ca.medavie.aws.lambda;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LambdaRequest {

	private Map<String, Object> bodyJson;
	
	private String body;
	
	private List<Map<String, String>> params;
	
	private List<Map<String, String>> query;
	
	private List<Map<String, String>> headers;

	private String httpMethod;
	
	private String resourcePath;
	
	public LambdaRequest() {
		super();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, Object> getBodyJson() {
		return bodyJson;
	}

	public void setBodyJson(Map<String, Object> bodyJson) {
		this.bodyJson = bodyJson;
	}

	public List<Map<String, String>> getParams() {
		return params;
	}

	public void setParams(List<Map<String, String>> params) {
		this.params = params;
	}

	public List<Map<String, String>> getQuery() {
		return query;
	}

	public void setQuery(List<Map<String, String>> query) {
		this.query = query;
	}

	public List<Map<String, String>> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Map<String, String>> headers) {
		this.headers = headers;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}	
}
