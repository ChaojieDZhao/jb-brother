package basic.design.behavioralpattern.chainofresponsibility10;

interface IStep
{
	IStep setNextHandler(IStep step);

	void Handler(Integer number);
}

/**
 * 接下来我们将要谈谈责任链模式，有多个对象，每个对象持有对下一个对象的引用，这样就会形成一条链，请求在这条链上传递，直到某一对象决定处理该请求。
 * https://www.cnblogs.com/lizo/p/7503862.html
 */
public class TestChainOfResponsibilityPattern
{
	public static void main(String[] args)
		throws java.lang.Exception
	{
		IStep step01 = new Step01();
		IStep step02 = new Step02();
		IStep step03 = new Step03();
		step01.setNextHandler(step02);
		step02.setNextHandler(step03);
		step01.Handler(-10);
		step01.Handler(0);
		step01.Handler(10);
	}
}

class Step01 implements IStep
{
	IStep next;

	public IStep setNextHandler(IStep step)
	{
		this.next = step;
		return this;
	}

	public void Handler(Integer number)
	{
		if (number < 0)
		{
			System.out.println("step01  " + -1);
		}
		else
		{
			next.Handler(number);
		}
	}
}

class Step02 implements IStep
{
	IStep next;

	public IStep setNextHandler(IStep step)
	{
		this.next = step;
		return this;
	}

	public void Handler(Integer number)
	{
		if (number == 0)
		{
			System.out.println("step02  " + 0);
		}
		else
		{
			next.Handler(number);
		}
	}
}

class Step03 implements IStep
{
	IStep next;

	public IStep setNextHandler(IStep step)
	{
		this.next = step;
		return this;
	}

	public void Handler(Integer number)
	{
		System.out.println("step03  " + 1);
	}
}