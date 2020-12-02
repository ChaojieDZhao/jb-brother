package basic.clazz.enumClass;

public enum Telephone
{
	instance;

	public void dial(String phoneNumber)
	{
		System.out.println("开始打电话:" + phoneNumber);

	}

}
