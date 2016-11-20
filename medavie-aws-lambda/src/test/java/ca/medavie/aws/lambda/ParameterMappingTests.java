package ca.medavie.aws.lambda;

import static ca.medavie.aws.lambda.LambdaUtils.convertTooParameterMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParameterMappingTests {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testListFlatteningNoExceptionsExpected() throws Exception {
		thrown = ExpectedException.none();
		List<Map<String, String>> testList = new ArrayList<Map<String,String>>();
		assertNotEquals(null, LambdaUtils.convertTooParameterMap(null));
		assertEquals(0, LambdaUtils.convertTooParameterMap(null).size());
		assertNotEquals(null, LambdaUtils.convertTooParameterMap(testList));
		assertEquals(0, convertTooParameterMap(testList).size());
		
		testList = list(map("property1", "one"), map("property2", "two"));
		assertNotEquals(null, LambdaUtils.convertTooParameterMap(testList));
		assertEquals(2, LambdaUtils.convertTooParameterMap(testList).size());
		assertEquals("two", convertTooParameterMap(testList).get("property2"));
		assertEquals("one", convertTooParameterMap(testList).get("property1"));
		assertEquals(null, convertTooParameterMap(testList).get("property3"));		
	}
	
	@Test
	public void testListFlatteningForExceptions() throws Exception {
		thrown.expect(DuplicateParameterException.class);
		List<Map<String, String>> testList = list(map("property1", "one"), map("property1", "two"));
		convertTooParameterMap(testList);
	}
	
	@Test
	public void testBeanMapperPositiveCases() throws Exception {
		thrown = ExpectedException.none();
		BeanUtilsParameterMapper<ParamClass> mapper = new BeanUtilsParameterMapper<ParamClass>(ParamClass.class);
		
		//null and empty lists
		assertNotEquals(null, mapper.mapRequestParameters(null));
		assertNotEquals(null, mapper.mapRequestParameters(lr((List<Map<String, String>>)null, (List<Map<String, String>>)null)));
		
		//all properties populated on params
		LambdaRequest request = lr(list(map("name", "Doug"), map("longish", "123"), map("time", "10:27")), (List<Map<String, String>>)null);
		ParamClass c = mapper.mapRequestParameters(request);
		assertNotEquals(null, c);
		assertEquals("Doug", c.getName());
		assertEquals(123, c.getLongish());
		assertEquals("10:27", c.getTime());
		
		//all properties populated on query
		request = lr( (List<Map<String, String>>)null, list(map("name", "Doug"), map("longish", "123"), map("time", "10:27")));
		c = mapper.mapRequestParameters(request);
		assertNotEquals(null, c);
		assertEquals("Doug", c.getName());
		assertEquals(123, c.getLongish());
		assertEquals("10:27", c.getTime());
		
		//all properties populated on both
		request = lr(list(map("longish", "123")), list(map("name", "Doug"), map("time", "10:27")));
		c = mapper.mapRequestParameters(request);
		assertNotEquals(null, c);
		assertEquals("Doug", c.getName());
		assertEquals(123, c.getLongish());
		assertEquals("10:27", c.getTime());
		
		//extra params
		request = lr(list(map("name", "Doug"), map("longish", "123"), map("time", "10:27"), map("foo", "bar")), (List<Map<String, String>>)null);
		c = mapper.mapRequestParameters(request);
		assertNotEquals(null, c);
		assertEquals("Doug", c.getName());
		assertEquals(123, c.getLongish());
		assertEquals("10:27", c.getTime());
		
	}
	
	@Test
	public void testDupParams() throws Exception{
		thrown.expect(DuplicateParameterException.class);
		//dups in one params
		LambdaRequest request = lr(list(map("name", "Dale"), map("name", "Doug"), map("longish", "123"), map("time", "10:27")), (List<Map<String, String>>)null);
		BeanUtilsParameterMapper<ParamClass> mapper = new BeanUtilsParameterMapper<ParamClass>(ParamClass.class);
		ParamClass c = mapper.mapRequestParameters(request);

		
		
	}
	
	@Test
	public void testDupQuery() throws Exception {
		thrown.expect(DuplicateParameterException.class);
		BeanUtilsParameterMapper<ParamClass> mapper = new BeanUtilsParameterMapper<ParamClass>(ParamClass.class);
		//dups in query
		LambdaRequest request = lr((List<Map<String, String>>)null, list(map("name", "Dale"), map("name", "Doug"), map("longish", "123"), map("time", "10:27")));
		ParamClass c = mapper.mapRequestParameters(request);

	}
	
	@Test
	public void testDupAcross() throws Exception {
		thrown.expect(DuplicateParameterException.class);
		BeanUtilsParameterMapper<ParamClass> mapper = new BeanUtilsParameterMapper<ParamClass>(ParamClass.class);
		//dups over both
		LambdaRequest request = lr(list(map("name", "Dale")), list(map("name", "Doug"), map("longish", "123"), map("time", "10:27")));
		ParamClass c = mapper.mapRequestParameters(request);
	}

	@Test 
	public void testTypeCast() throws Exception {
		BeanUtilsParameterMapper<ParamClass> mapper = new BeanUtilsParameterMapper<ParamClass>(ParamClass.class);

		//bad type
		LambdaRequest request = lr((List<Map<String, String>>)null, list(map("name", "Doug"), map("longish", "x"), map("time", "10:27")));
		ParamClass c = mapper.mapRequestParameters(request);	

	}
	public Map<String, String> map(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key,  value);
		return map;
	}
	
	public List<Map<String, String>> list(Map<String, String> ... maps) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : maps) {
			list.add(map);
		}
		return list;
	}
	
	public LambdaRequest lr(List<Map<String, String>> params, List<Map<String, String>> query) {
		LambdaRequest request = new LambdaRequest();
		request.setParams(params);
		request.setQuery(query);
		return request;
	}
	
	

}
