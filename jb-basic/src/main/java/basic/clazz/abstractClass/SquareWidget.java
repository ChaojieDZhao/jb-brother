package basic.clazz.abstractClass;

public class SquareWidget extends Widget
{
	private final int size;

	public SquareWidget(int size)
	{
		this.size = size;
	}

	public static void main(String[] args)
	{
		Widget widget = new SquareWidget(5);
		System.out.println(widget.cachedWidth); //不是5，却是0，因为给父类赋值的时候子类还没有初始化。
		System.out.println(widget.cachedHeight);
		System.out.println(widget.height());
		System.out.println(widget.width());
	}

	@Override
	protected int width()
	{
		System.out.println("method:width()");
		return size;
	}

	@Override
	protected int height()
	{
		System.out.println("method:height()");
		return 3;
	}
}