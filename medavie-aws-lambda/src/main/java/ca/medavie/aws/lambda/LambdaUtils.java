package ca.medavie.aws.lambda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaUtils {

	public static Map<String, String> convertTooParameterMap(List<Map<String, String>> listOfMaps) throws DuplicateParameterException {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		if (listOfMaps != null) {
			for (Map<String, String> map : listOfMaps ) {
				for (String key :  map.keySet()) {
					if (paramMap.containsKey(key)) {
						throw new DuplicateParameterException(String.format("The parameter %s is supplied multiple times", key));
					}
					paramMap.put(key, map.get(key));
				}
			}
		}
		return paramMap;
	}

}
