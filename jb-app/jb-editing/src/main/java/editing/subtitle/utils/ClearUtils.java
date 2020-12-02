package editing.subtitle.utils;

public class ClearUtils
{

	public static void main(String[] args)
	{
		System.out.println("clearChineseBracket：" + clearChineseBracket(" （咕run）笑，我是一个粉刷匠（粉刷本领强）".trim()));
		System.out.println("clearEnglishBracket：" + clearEnglishBracket(" (i love your)but l hate (myself)".trim()));
		System.out.println("clearSquareBracket：" + clearSquareBracket(" [咕run]笑，我是一个粉刷匠[粉刷本领强]".trim()));
	}

	/**
	 * 清理中文小括号
	 */
	public static String clearChineseBracket(String context)
	{
		// 修改原来的逻辑，防止右括号出现在左括号前面的位置
		int head = context.indexOf('（'); // 标记第一个使用左括号的位置
		if (head == -1)
		{
			return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
		}
		else
		{
			int next = head + 1; // 从head+1起检查每个字符
			int count = 1; // 记录括号情况
			do
			{
				if (context.charAt(next) == '（')
				{
					count++;
				}
				else if (context.charAt(next) == '）')
				{
					count--;
				}
				next++; // 更新即将读取的下一个字符的位置
				if (count == 0)
				{ // 已经找到匹配的括号
					String temp = context.substring(head, next);
					context = context.replace(temp, ""); // 用空内容替换，复制给context
					head = context.indexOf('（'); // 找寻下一个左括号
					next = head + 1; // 标记下一个左括号后的字符位置
					count = 1; // count的值还原成1
				}
			} while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
		}
		return context; // 返回更新后的context
	}

	/**
	 * 清理英文小括号
	 */
	public static String clearEnglishBracket(String context)
	{
		// 修改原来的逻辑，防止右括号出现在左括号前面的位置
		int head = context.indexOf('('); // 标记第一个使用左括号的位置
		if (head == -1)
		{
			return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
		}
		else
		{
			int next = head + 1; // 从head+1起检查每个字符
			int count = 1; // 记录括号情况
			do
			{
				if (context.charAt(next) == '(')
				{
					count++;
				}
				else if (context.charAt(next) == ')')
				{
					count--;
				}
				next++; // 更新即将读取的下一个字符的位置
				if (count == 0)
				{ // 已经找到匹配的括号
					String temp = context.substring(head, next);
					context = context.replace(temp, ""); // 用空内容替换，复制给context
					head = context.indexOf('('); // 找寻下一个左括号
					next = head + 1; // 标记下一个左括号后的字符位置
					count = 1; // count的值还原成1
				}
			} while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
		}
		return context; // 返回更新后的context
	}

	/**
	 * 清理中括号
	 *
	 * @param context
	 * @return
	 */
	public static String clearSquareBracket(String context)
	{
		// 修改原来的逻辑，防止右括号出现在左括号前面的位置
		int head = context.indexOf('['); // 标记第一个使用左括号的位置
		if (head == -1)
		{
			return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
		}
		else
		{
			int next = head + 1; // 从head+1起检查每个字符
			int count = 1; // 记录括号情况
			do
			{
				if (context.charAt(next) == '[')
				{
					count++;
				}
				else if (context.charAt(next) == ']')
				{
					count--;
				}
				next++; // 更新即将读取的下一个字符的位置
				if (count == 0)
				{ // 已经找到匹配的括号
					String temp = context.substring(head, next);
					context = context.replace(temp, ""); // 用空内容替换，复制给context
					head = context.indexOf('['); // 找寻下一个左括号
					next = head + 1; // 标记下一个左括号后的字符位置
					count = 1; // count的值还原成1
				}
			} while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
		}
		return context; // 返回更新后的context
	}
}
