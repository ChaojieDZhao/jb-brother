package basic.reference;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User
{
	private Integer id;

	private String name;

	protected User(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public static void main(String[] args)
	{
		User user = new User(10, "10");
		User user2 = user;
		user2.setId(11);
		System.out.println(user);
	}

}
