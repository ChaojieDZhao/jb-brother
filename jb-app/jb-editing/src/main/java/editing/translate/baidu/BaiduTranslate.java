package editing.translate.baidu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class BaiduTranslate
{

	// 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = "20191028000346027";

	private static final String SECURITY_KEY = "7c_qcyVAjOZwGYjqaHIj";

	public static String translate(String text)
		throws IOException
	{
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String transResult = api.getTransResult(text, "auto", "zh");
		//System.out.println(transResult);
		JSONObject transResultJSON = JSONObject.parseObject(transResult);
		JSONArray transArray = transResultJSON.getJSONArray("trans_result");
		String dst = transArray.getJSONObject(0).getString("dst");
		return UnicodeConverteUtil.unicode2String(dst);
			//.replaceAll("，", " ")
			//.replaceAll("。", " ")
			//.replaceAll("\\,", " ")
			//.replaceAll("\\.", " ");
	}
}
