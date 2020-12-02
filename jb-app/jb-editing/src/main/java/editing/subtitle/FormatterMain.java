package editing.subtitle;

import com.google.common.collect.Maps;
import constant.AppConstant;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;
import static editing.subtitle.utils.FormatLine.formatChineseSentences;
import static editing.subtitle.utils.FormatLine.formatEnglishSentences;

public class FormatterMain
{

	public static void main(String[] args)
		throws Exception
	{

		File after_file = new File(AppConstant.RESOURCE_PATH + "formatter.srt");
		File before_file = new File(CURRENT_FILE_DIR + "JAVA");
		BufferedWriter after_br = new BufferedWriter(new FileWriter(after_file));
		BufferedReader before_br = new BufferedReader(new FileReader(before_file));

		Stream<String> lines = before_br.lines();
		Iterator<String> iterator = lines.iterator();
		HashMap<Integer, String> maps = Maps.newHashMap();
		int mapKey = 1;
		while (iterator.hasNext())
		{
			String next = iterator.next();
			maps.put(mapKey++, next);
		}

		Iterator<Map.Entry<Integer, String>> iterator2 = maps.entrySet().iterator();

		Integer numLine = 1;

		while (iterator2.hasNext())
		{
			Map.Entry<Integer, String> next = iterator2.next();
			//字幕序号
			if (next.getKey() % 5 == 1)
			{
				Integer integer = numLine++;
				System.out.println(next.getValue());
				after_br.write((integer).toString());
				after_br.newLine();
			}
			//时间码
			if (next.getKey() % 5 == 2)
			{
				System.out.println(next.getValue());
				after_br.write((next.getValue()));
				after_br.newLine();
			}
			//中文
			if (next.getKey() % 5 == 3)
			{
				writeLine(after_br, formatChineseSentences(next.getValue()));
			}
			//英文
			if (next.getKey() % 5 == 4)
			{
				writeLine(after_br, formatEnglishSentences(next.getValue()));
			}
			//空格
			if (next.getKey() % 5 == 0)
			{
				after_br.write("");
				after_br.newLine();
			}
		}
		after_br.flush();
		before_br.close();
		after_br.close();
		before_br.close();
	}

	private static void writeLine(BufferedWriter bw, String text)
		throws IOException
	{
		System.out.println(text);
		bw.write(text);
		bw.newLine();
	}

}
