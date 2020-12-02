package basic.clazz.enumClass;

public enum ContactMethod
{
	PHONE("telephone.png")
		{
			@Override
			public void initiate(User user)
			{
				Telephone.instance.dial(user.getPhoneNumber());
			}
		},
	EMAIL("envelope.png")
		{
			@Override
			public void initiate(User user)
			{
				EmailClient.instance.sendTo(user.getEmailAddress());
			}
		};

	private final String icon;

	ContactMethod(String icon)
	{
		this.icon = icon;
	}

	public abstract void initiate(User user);

	public String getIcon()
	{
		return icon;
	}
}