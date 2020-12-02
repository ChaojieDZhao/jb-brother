package utils;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexDocument
{

	private String id;

	private Map<String, Object> data = new HashMap<String, Object>();

	private String source;

	public IndexDocument(String id)
	{
		this.id = id;
	}

	public IndexDocument()
	{
	}

	public Map<String, Object> getData()
	{
		return data;
	}

	public void setData(Map<String, Object> data)
	{
		this.data = data;
	}

	public void put(String key, Object value)
	{
		if (value != null)
		{
			data.put(key, value);
		}
	}

	public Object getByKey(String key)
	{
		return data.get(key);
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDocId()
	{
		return this.id;
	}

	public Map<String, Object> getSource()
		throws IOException
	{
		String json = JSON.toJSONString(this.data);
		XContentParser parser =
			XContentFactory.xContent(XContentType.JSON).createParser(NamedXContentRegistry.EMPTY, json);
		return parser.map();
	}

	public void setSource(String source)
	{
		this.source = source;
	}
}
