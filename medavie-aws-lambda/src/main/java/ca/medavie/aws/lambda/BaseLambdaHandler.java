package ca.medavie.aws.lambda;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class BaseLambdaHandler implements RequestHandler<LambdaRequest, Object>{
	
	@Resource
	@Qualifier("resoureMethodMap")
	private Map<String, ResourceMethodStrategy> resourceMethodMap = new HashMap<String, ResourceMethodStrategy>();
	
	private BaseLambdaHandler() {
		
	}
	
	protected  BaseLambdaHandler(Class<?> ... springConfigClasses) {
		ApplicationContext springContext = new AnnotationConfigApplicationContext(springConfigClasses );
		AutowireCapableBeanFactory acbFactory = springContext.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}

	public final Object handleRequest(LambdaRequest request, Context context) {
		System.out.println(String.format("httpMethod: %s, resource path: %s", request.getHttpMethod(), request.getResourcePath()));
		String key = request.getHttpMethod() + "-" + request.getResourcePath();
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
	
	public void addResourceMethodMapEntry(String key, ResourceMethodStrategy strat) {
		if (this.resourceMethodMap == null) {
			throw new IllegalStateException("No resource method strategies set, cannot complete request");
		}
 		this.resourceMethodMap.put(key, strat);
	}

	public Map<String, ResourceMethodStrategy> getResourceMethodMap() {
		return resourceMethodMap;
	}

	public void setResourceMethodMap(Map<String, ResourceMethodStrategy> resourceMethodMap) {
		this.resourceMethodMap = resourceMethodMap;
	}

	
	
	

}
