package ca.medavie.aws.lambda.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ca.medavie.aws.lambda.ResourceMethodKey;
import ca.medavie.aws.lambda.ResourceMethodStrategy;

@Configuration 
@ComponentScan(basePackages = "ca.medavie.inconfidence")

public class TestAppConfig {

	public TestAppConfig() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean(name="resourceMethodMap")
	public Map<String, ResourceMethodStrategy> resourceMethodMap() {
		Map<String, ResourceMethodStrategy> map = new HashMap<String, ResourceMethodStrategy>();
		map.put("PUT-/client", new StrategyOne(InputDataOne.class, ParamDataOne.class));
		return map;
	}

}
