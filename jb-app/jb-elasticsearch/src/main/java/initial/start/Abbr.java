package initial.start;

public class Abbr
{

	private String code;

	private String city;

	private String province;

	private String Pcode;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getPcode()
	{
		return Pcode;
	}

	public void setPcode(String pcode)
	{
		Pcode = pcode;
	}

	@Override
	public String toString()
	{
		return "Abbr{" +
			"code='" + code + '\'' +
			", city='" + city + '\'' +
			", province='" + province + '\'' +
			", Pcode='" + Pcode + '\'' +
			'}';
	}
}
