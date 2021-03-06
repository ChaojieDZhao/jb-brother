package basic.design.behavioralpattern.visitor19;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 简单来说，访问者模式就是一种分离对象数据结构与行为的方法，通过这种分离，可达到为一个被访问者动态添加新的操作而无需做其它的修改的效果。
 * 该模式适用场景：如果我们想为一个现有的类增加新功能，不得不考虑几个事情：
 * 1、新功能会不会与现有功能出现兼容性问题？
 * 2、以后会不会再需要添加？
 * 3、如果类不允许修改代码怎么办？面对这些问题，最好的解决方法就是使用访问者模式，访问者模式适用于数据结构相对稳定的系统，把数据结构和算法解耦，
 */
public class TestUse
{
	public static void main(String args[])
	{
		Visitor appOwner = new APPOwner();
		ArrayList<User> users = new ArrayList<User>();
		users.add(new UserOrdinary("普通用户短反馈"));
		users.add(new UserOrdinary("这是一个普通用户的比较长的反馈"));
		users.add(new UserVIP("VIP用户的短反馈"));
		users.add(new UserVIP("VIP用户的比较长的反馈反馈"));
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext())
		{
			iterator.next().accept(appOwner);
		}
	}
}