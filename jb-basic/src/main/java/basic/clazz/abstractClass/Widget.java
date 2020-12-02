package basic.clazz.abstractClass;

public abstract class Widget
{
	public final int cachedWidth;

	public final int cachedHeight;

	public Widget()
	{
		System.out.println("heheda: constructor Widget()");
		this.cachedWidth = width();
		this.cachedHeight = height();
	}

	protected abstract int width();

	protected void sayHello()
	{
		System.out.println("hello my sun");
	}

	protected abstract int height();
}