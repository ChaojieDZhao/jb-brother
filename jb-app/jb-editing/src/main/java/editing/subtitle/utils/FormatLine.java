package editing.subtitle.utils;

public class FormatLine
{
	public static void main(String[] args)
	{
		System.out.println(formatChineseSentences("你好? 我不好! 你是谁"));
		System.out.println(formatChineseSentences("你好?     我不好?      你是谁"));
		System.out.println(formatChineseSentences("你好?   我不好?   你是谁"));
		System.out.println(formatChineseSentences("你好???   我不好？？？?   你是谁"));
		System.out.println(formatChineseSentences("你好? ！ ? ?   我不好？？？?   你是谁"));
		System.out.println(formatChineseSentences("你好，我不好，你是谁"));

		System.out.println(formatEnglishSentences("ehhehe,      123123.    sterwtewrtewt"));
		System.out.println(formatEnglishSentences("ehhehe，     123123。    sterwtewrtewt"));
		System.out.println(formatEnglishSentences("ehhehe?     123123?    sterwtewrtewt？"));
		System.out.println(formatEnglishSentences("ehhehe?   !  ? ! 123123?    sterwtewrtewt？"));
	}

	public static String formatEnglishSentences(String text)
	{
		//多个空格替代为一个
		text = text.replaceAll(" +", " ");
		text = text.replaceAll("\\,", ", ")
			.replaceAll("\\.", ". ")
			.replaceAll("。", ". ")
			.replaceAll("，", ", ")
			.replaceAll("！", "!")
			.replaceAll("？", "?");
		//多个空格替代为一个
		text = text.replaceAll(" +", " ");
		text = text.replaceAll(" \\?", "?");
		text = text.replaceAll(" \\!", "!");
		text = text.trim();
		if (text.startsWith("-"))
		{
			text = text.substring(1, text.length());
		}
		return text.trim();
	}

	public static String formatChineseSentences(String text)
	{
		text = text.replaceAll("，", " ")
			.replaceAll("。", " ")
			.replaceAll("\\,", " ")
			.replaceAll("\\.", " ")
			.replaceAll("？", "？ ")
			.replaceAll("！", "！ ")
			.replaceAll("\\?", "？ ")
			.replaceAll("\\!", "！ ");
		//多个空格替代为一个
		text = text.replaceAll(" +", " ");
		text = text.replaceAll(" ？", "？");
		text = text.replaceAll(" ！", "！");
		text = text.trim();
		if (text.startsWith("-"))
		{
			text = text.substring(1, text.length());
		}
		return text.trim();
	}
}
