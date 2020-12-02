package basic.design.structuralpattern.adapter13;

interface Target
{
	/**
	 * playFlac
	 *
	 * @param src
	 */
	void playFlac(Object src);
}

/**
 * @describe 原理：通过继承来实现适配器功能。
 * 当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A，在这种情况下，
 * 我们可以定义一个适配器p来进行中转，这个适配器p要实现我们访问的接口A，这样我们就能继续访问当前接口A中的方法（虽然它目前不是我们的菜），
 * 然后再继承接口B的实现类BB，这样我们可以在适配器P中访问接口B的方法了，这时我们在适配器P中的接口A方法中直接引用BB中的合适方法，这样就完成了一个简单的类适配器。
 */
public class TestAdapterPattern
{
	public static void main(String[] args)
	{
		Adaptee adaptee = new Adaptee();
		adaptee.playMp3("mp3");
		Target target = new ClassAdapter();
		target.playFlac("flac");
		target = new ObjectAdapter();
		target.playFlac("flac");
	}
}

/**
 * 被适配对象
 */
class Adaptee
{
	void playMp3(Object src)
	{
		System.out.println("播放MP3：" + src);
	}
}

/**
 * 类适配器
 */
class ClassAdapter extends Adaptee implements Target
{
	@Override
	public void playFlac(Object src)
	{
		//可能需要对src作处理
		playMp3(src);
	}
}

/**
 * 对象适配器
 */
class ObjectAdapter implements Target
{
	private Adaptee adaptee;

	public ObjectAdapter()
	{
		super();
		adaptee = new Adaptee();
	}

	@Override
	public void playFlac(Object src)
	{
		//可能需要对src作处理
		adaptee.playMp3(src);
	}
}

