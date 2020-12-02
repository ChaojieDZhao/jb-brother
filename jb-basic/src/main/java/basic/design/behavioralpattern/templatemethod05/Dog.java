package basic.design.behavioralpattern.templatemethod05;

public class Dog extends Animal
{

	public Dog()
	{
		System.out.println("dog登场：");
	}

	public void speak()
	{
		System.out.println("汪汪汪!");
	}

	public void sleep()
	{
		System.out.println("小狗睡在地上。");
	}

	public void move()
	{
		System.out.println("奔跑");
	}
}
	
 
