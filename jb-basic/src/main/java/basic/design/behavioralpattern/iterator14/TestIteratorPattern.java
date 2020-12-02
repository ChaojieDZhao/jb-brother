package basic.design.behavioralpattern.iterator14;

import java.util.ArrayList;
import java.util.List;

interface MyIterator
{
	/**
	 * 将游标指向第一个元素
	 */
	void first();

	/**
	 * 将游标指向下一个元素
	 */
	void next();

	/**
	 * 判断是否有下一个元素
	 *
	 * @return
	 */
	boolean hasNext();

	/**
	 * 是不是第一个元素
	 *
	 * @return
	 */
	boolean isFirst();

	/**
	 * 是不是最后一个元素
	 *
	 * @return
	 */
	boolean isLast();

	/**
	 * 获取当前对象
	 *
	 * @return
	 */
	Object getCurrentObj();
}

/**
 * @describe
 */
public class TestIteratorPattern
{
	public static void main(String[] args)
	{
		ConcreteMyAggregate cma = new ConcreteMyAggregate();
		cma.addObject("111");
		cma.addObject("222");
		cma.addObject("333");
		cma.addObject("444");

		MyIterator iterator = cma.createIterator();
		//如果删除一个元素的话，迭代的时候也同样会被删除
		cma.removeObject("111");
		while (iterator.hasNext())
		{
			//获取当前对象
			System.out.println(iterator.getCurrentObj());
			//将游标向下移
			iterator.next();
		}
	}
}

class ConcreteMyAggregate
{
	private List<Object> list = new ArrayList<>();

	public void addObject(Object obj)
	{
		this.list.add(obj);
	}

	public void removeObject(Object obj)
	{
		this.list.remove(obj);
	}

	public List<Object> getList()
	{
		return list;
	}

	public void setList(List<Object> list)
	{
		this.list = list;
	}

	public MyIterator createIterator()
	{
		return new ConcreteIterator();
	}

	private class ConcreteIterator implements MyIterator
	{
		private int cursor;

		@Override
		public void first()
		{
			cursor = 0;
		}

		@Override
		public void next()
		{
			if (cursor < list.size())
			{
				cursor++;
			}
		}

		@Override
		public boolean hasNext()
		{
			//如果游标<list的大小，则说明还有下一个
			if (cursor < list.size())
			{
				return true;
			}
			return false;
		}

		@Override
		public boolean isFirst()
		{
			return cursor == 0 ? true : false;
		}

		@Override
		public boolean isLast()
		{
			return cursor == (list.size() - 1) ? true : false;
		}

		@Override
		public Object getCurrentObj()
		{
			return list.get(cursor);
		}
	}

}