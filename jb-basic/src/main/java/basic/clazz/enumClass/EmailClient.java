package basic.clazz.enumClass;

public enum EmailClient
{
	instance;

	public void sendTo(String phoneNumber)
	{
		System.out.println("开始打电话:" + phoneNumber);

	}

}
