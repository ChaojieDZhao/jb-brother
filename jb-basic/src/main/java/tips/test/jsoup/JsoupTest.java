package tips.test.jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class JsoupTest
{
	public static void main(String[] args)
	{
	}

	@Test
	public void test2()
		throws IOException
	{
		Document doc = Jsoup.connect("http://music.migu.cn/v3/search?type=tag&keyword=跑步")
			.header("Referer", "http://music.migu.cn")
			.get();
		Element song = doc.selectFirst(".songlist-item.single-item.on");
		System.out.println(song);
		Element element = song.selectFirst("a[href^=/v3/music/artist]");
		String html = element.html();
		System.out.println(html);
		Element element2 = song.selectFirst("a[href^=/v3/music/song]");
		html = element2.html();
		System.out.println(html);
	}

	@Test
	public void test1()
		throws IOException
	{
		Document doc = Jsoup.connect("http://music.migu.cn/v3/search?type=tag&keyword=跑步")
			.header("Referer", "http://music.migu.cn")
			.get();
		String s1 = doc.outerHtml();
		//System.out.println(s1);
		//System.out.println(doc.title());
		Elements songList = doc.select(".songlist-item.single-item.on");
		//System.out.println("songList:"+songList);
		for (Element song : songList)
		{
			//System.out.println(song);
			System.out.println(song.attr("data-id"));
		}
	}

	@Test
	public void test3()
		throws IOException
	{

		Document doc = Jsoup.connect("http://www.anc.org/penn.html")
			.get();
		String s1 = doc.outerHtml();
		Elements tbody = doc.getElementsByTag("tbody");

		JSONObject sqlObject = new JSONObject();
		//System.out.println("songList:"+songList);
		for (Element song : tbody)
		{
			Elements children = song.children();
			JSONArray jsonArray = new JSONArray();
			for (int i = 1; i < children.size(); i++)
			{
				JSONObject jsonObject = new JSONObject();
				Element element = children.get(i);
				Element child = element.child(2);
				Element child_1 = element.child(1);
				System.out.println(child_1.html());
				jsonObject.put("tag", child_1.html());
				System.out.println(child.html());
				jsonObject.put("description", child.html());
				jsonArray.add(jsonObject);
			}
			System.out.println(JSON.toJSONString(jsonArray));
			sqlObject.put("RECORD", jsonArray);
			System.out.println(JSON.toJSONString(sqlObject));
		}

	}
}
