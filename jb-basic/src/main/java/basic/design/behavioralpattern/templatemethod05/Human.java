package basic.design.behavioralpattern.templatemethod05;

public class Human extends Animal
{

	public Human()
	{
		System.out.println("人类登场：");
	}

	public void speak()
	{
		System.out.println("你好啊！");
	}

	public void sleep()
	{
		System.out.println("我睡在床上。");
	}

	public void move()
	{
		System.out.println("坐公交");
	}

}