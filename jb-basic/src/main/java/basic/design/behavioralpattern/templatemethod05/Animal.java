package basic.design.behavioralpattern.templatemethod05;

public abstract class Animal
{

	public static final int SPEAK = 1;

	public static final int SLEEP = 3;

	public static final int MOVE = 7;

	public Animal()
	{
	}

	//抽象方法
	public abstract void speak();

	//抽象方法
	public abstract void sleep();

	//抽象方法
	public abstract void move();

	/**
	 * 这个方法就是模板方法
	 */
	public void action(int num)
	{
		switch (num)
		{
			case SPEAK:
				this.speak();
				break;
			case SLEEP:
				this.sleep();
				break;
			case MOVE:
				this.move();
				break;
			default:
				break;
		}
	}
}
