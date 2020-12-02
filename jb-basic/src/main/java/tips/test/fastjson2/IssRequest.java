package tips.test.fastjson2;

public class IssRequest
{
	public String appId;

	public Param param;

	@Override
	public String toString()
	{
		final StringBuffer sb = new StringBuffer("IssRequest{");
		sb.append("appId='").append(appId).append('\'');
		sb.append(", param=").append(param);
		sb.append('}');
		return sb.toString();
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public Param getParam()
	{
		return param;
	}

	public void setParam(Param param)
	{
		this.param = param;
	}
}

class Param
{
	public String text;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		final StringBuffer sb = new StringBuffer("Param{");
		sb.append("text='").append(text).append('\'');
		sb.append('}');
		return sb.toString();
	}
}