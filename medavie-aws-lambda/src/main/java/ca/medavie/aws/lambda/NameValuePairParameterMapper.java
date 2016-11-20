package ca.medavie.aws.lambda;

import java.util.HashMap;
import java.util.Map;

public class NameValuePairParameterMapper implements RequestParameterMapper<Map<String,String>> {

	public NameValuePairParameterMapper() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> mapRequestParameters(LambdaRequest request) throws MedavieAWSException {
		Map<String, String> parameters = new HashMap<String, String>();
		
		if (request != null) {
			Map<String, String> queryMap = LambdaUtils.convertTooParameterMap(request.getQuery());
			Map<String, String> paramMap = LambdaUtils.convertTooParameterMap(request.getParams());
			parameters.putAll(queryMap);
		
			if (paramMap != null) {
				//make sure not duplicate parameters were supplied via url and query params
				for (String key : paramMap.keySet()) {
					if (parameters.containsKey(key)) {
						throw new DuplicateParameterException(String.format("The parameter %s is supplied multiple times", key));
					}
				}
				parameters.putAll(paramMap);
			}
		}
		return parameters;
	}

}
