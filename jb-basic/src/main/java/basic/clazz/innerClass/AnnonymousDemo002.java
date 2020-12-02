package basic.clazz.innerClass;

/**
 * 静态内部类
 */
abstract class Bird
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public abstract int fly();
}

public class AnnonymousDemo002
{

	public static void main(String[] args)
	{
		System.out.println(args);
		AnnonymousDemo002 test = new AnnonymousDemo002();

		Alldios alldios = new Alldios();

		test.test(new Bird()
		{
			@Override
			public int fly()
			{
				return alldios.getValue();
			}

			@Override
			public String getName()
			{
				return "大雁";
			}
		});
	}

	public void test(Bird bird)
	{
		System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
	}
}

class Alldios
{
	private Integer value = 10000;

	public Integer getValue()
	{
		return value;
	}

}