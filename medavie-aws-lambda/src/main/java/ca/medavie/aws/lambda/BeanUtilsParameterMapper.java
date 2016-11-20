package ca.medavie.aws.lambda;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Maps incoming query and request parameters from a REST call to a templatized class
 * 
 * @author bcdswan
 *
 * @param <T> - the class which holds the query and request parameters from the REST call
 */
public class BeanUtilsParameterMapper<T> implements RequestParameterMapper<T> {

	private Class<T> clazz;
	
	public BeanUtilsParameterMapper(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T mapRequestParameters(LambdaRequest request) throws MedavieAWSException {
		T bean = null;
		
		//if this is a Void type, don't attempt to instantiate it
		if (this.clazz.equals(Void.class)) {
			return null;
		}
		
		if (this.clazz == null) {
			throw new MedavieAWSException("Could not instantiate return class", new IllegalStateException("clazz parameter was not set in class constructor"));
		}
		try {
			bean = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new MedavieAWSException("Could not instantiate return class", e);
		} catch (IllegalAccessException e) {
			throw new MedavieAWSException("Could not instantiate return class", e);
		}
		if (request != null) {
				Map<String, String> queryMap = LambdaUtils.convertTooParameterMap(request.getQuery());
			Map<String, String> paramMap = LambdaUtils.convertTooParameterMap(request.getParams());
			if (paramMap != null) {
				//make sure not duplicate parameters were supplied via url and query params
				for (String key : paramMap.keySet()) {
					if (queryMap.containsKey(key)) {
						throw new DuplicateParameterException(String.format("The parameter %s is supplied multiple times", key));
					}
				}
			}
			
			try {
				BeanUtils.populate(bean, paramMap);
				BeanUtils.populate(bean, queryMap);
			} catch (IllegalAccessException e) {
				throw new MedavieAWSException("Could not set a property on return class", e);
			} catch (InvocationTargetException e) {
				throw new MedavieAWSException("Could not set a property on return class", e);
			}
			
		}
		return bean;
	}
	
}
