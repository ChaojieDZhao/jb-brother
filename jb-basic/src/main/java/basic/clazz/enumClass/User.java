package basic.clazz.enumClass;

public class User
{
	private String phoneNumber;

	private String emailAddress;

	public static void main(String[] args)
	{
		User user = new User();
		ContactMethod method = user.getPrimaryContactMethod();
		method.initiate(user);
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public ContactMethod getPrimaryContactMethod()
	{
		return ContactMethod.PHONE;
	}
}