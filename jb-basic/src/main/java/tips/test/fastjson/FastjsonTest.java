package tips.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class FastjsonTest
{
	public static void main(String[] args)
	{
		JSONObject var = new JSONObject();
		JSONObject var2 = new JSONObject();
		JSONArray var3 = new JSONArray();
		var.put("var2", var2);
		var2.put("var3", var3);
		JSONObject var4 = new JSONObject();
		var3.add(var4);
		var4.put("var5", "var5");
		System.out.println(var.toJSONString());
		JSONObject JSONObject = var.getJSONObject("var2").getJSONArray("var3").getJSONObject(0);
		JSONObject.put("var6", "what the fuck");
		System.out.println(var.toJSONString());

	}

	@Test
	public void test1()
	{
		JSONObject var = new JSONObject();
		JSONObject var2 = new JSONObject();
		JSONObject var3 = new JSONObject();
		var.put("var2", var2);
		var2.put("var3", var3);
		var3.put("var4", "var4");
		System.out.println(var.toJSONString());
		JSONObject jsonObject = var.getJSONObject("var2").getJSONObject("var3");
		jsonObject.put("test", "test");
		System.out.println(var.toJSONString());
	}

	@Test
	public void test2()
	{
		String string =
			"{\"apiList\":[\"/nlp/ner\",\"/nlp/pos\",\"/nlp/cws\",\"/lxyisa/dispatch/afterhandle\",\"1111\",\"222\"]}";

		JSONObject jsonObject = JSON.parseObject(string);
		JSONArray apiList = jsonObject.getJSONArray("apiList");
		HashSet<String> strings = apiList.stream().map(Object::toString).collect(Collectors.toCollection(HashSet::new));
		System.out.println("");
	}

	@Test
	public void test3()
	{
		String string =
			"{\"openApiMap\":{\"/v1/open/nlp/cws\":3,\"/v1/open/nlp/pos\":2,\"/lxyisa/dispatch/afterhandle\":4,\"1212\":451,\"/v1/open/nlp/ner\":1}}";

		JSONObject jsonObject = JSON.parseObject(string);
		String openApiMap = jsonObject.getString("openApiMap");
		Map<String, Integer> params = JSONObject.parseObject(openApiMap,
			new TypeReference<Map<String, Integer>>()
			{
			});
		System.out.println(params);
	}

	@Test
	public void test4()
	{
		JSONObject jsonObject = (JSONObject)null;
		System.out.println(jsonObject);

		Map hashMap = new HashMap();
		hashMap.putAll(null);
	}

	@Test
	public void test5()
	{
		JSONObject object =
			JSONObject.parseObject("{\"date\": \"2020-07-07\",\"tempLow\": \"22℃\",\"weatherType\": 4}");
		String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat);
		System.out.println(pretty);
	}

	/**
	 * 获取联赛赛程的scheme
	 *
	 * @param fullName
	 * @param competitionId
	 * @return
	 */
	private static JSONObject getLeagueScheduleSchemaObj(String fullName, String competitionId)
	{
		JSONObject schemaObj = new JSONObject();
		schemaObj.put("type", "JUMP_INNER_NEW_PAGE");
		JSONObject paramsObj = new JSONObject();
		paramsObj.put("pageID", "MATCH_CATEGORY_DEFAULT");
		paramsObj.put("location", "H5");
		schemaObj.put("params", paramsObj);
		JSONObject extraObj = new JSONObject();
		extraObj.put("competitionName", fullName);
		extraObj.put("seasonId", "");
		extraObj.put("sportItemId", "");
		extraObj.put("competitionId", competitionId);
		paramsObj.put("extra", extraObj);
		return schemaObj;
	}

    @Test
	public void parseObject()
	{
		IssRequest
			issRequest = JSONObject.parseObject("{\"appId\":\"5cf8b001\",\"param\":{\"text\":\"今天天气怎么样\"}}", IssRequest.class);
		System.out.println(issRequest);

		issRequest = JSONObject.parseObject("{\"param\":{\"text\":\"今天天气怎么样\"}}", IssRequest.class);
		System.out.println(issRequest);

		issRequest = JSONObject.parseObject("{\"appId\":\"5cf8b001\"}", IssRequest.class);
		System.out.println(issRequest);

		issRequest = JSONObject.parseObject("{}", IssRequest.class);
		System.out.println(issRequest);

		issRequest = JSONObject.parseObject("", IssRequest.class);
		System.out.println(issRequest);

		String[] split = "/iis,/iis2".split(",");
		System.out.println(split);

	}
}
