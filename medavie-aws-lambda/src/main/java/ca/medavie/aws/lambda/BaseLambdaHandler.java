package ca.medavie.aws.lambda;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class BaseLambdaHandler implements RequestHandler<LambdaRequest, Object>{

	private Map<ResourceMethodKey, ResourceMethodStrategy> resourceMethodMap = new HashMap<ResourceMethodKey, ResourceMethodStrategy>();
	
	public BaseLambdaHandler() {
		
	}
	
	public BaseLambdaHandler(Map<ResourceMethodKey, ResourceMethodStrategy> resourceMethodMap) {
		this.resourceMethodMap = resourceMethodMap;
	}

	public final Object handleRequest(LambdaRequest request, Context context) {
		System.out.println(String.format("httpMethod: %s, resource path: %s", request.getHttpMethod(), request.getResourcePath()));
		ResourceMethodKey key = new ResourceMethodKey(request.getHttpMethod(), request.getResourcePath());
		ResourceMethodStrategy strat = this.resourceMethodMap.get(key);
		if (strat != null) {
			try {
				System.out.println(String.format("strat: %s", (strat!=null ? strat.getClass().getName() : "none")));
				return strat.handleRequest(request);
			} catch (MedavieAWSException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public void addResourceMethodMapEntry(ResourceMethodKey key, ResourceMethodStrategy strat) {
		if (this.resourceMethodMap == null) {
			throw new IllegalStateException("No resource method strategies set, cannot complete request");
		}
 		this.resourceMethodMap.put(key, strat);
	}
	

}
