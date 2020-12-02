package editing.subtitle;

import com.google.common.collect.Maps;
import editing.PrUtils;
import editing.subtitle.utils.ClearUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;

public class Two2OneMain
{

	public static File english_file = new File(CURRENT_FILE_DIR + "english.srt");

	public static File chinese_file = new File(CURRENT_FILE_DIR + "chinese.srt");

	public static File combine_file = new File(CURRENT_FILE_DIR + "combine.srt");

	public static HashMap<Integer, String> englishMaps = Maps.newHashMap();

	public static HashMap<Integer, String> chineseMaps = Maps.newHashMap();

	static
	{
		try
		{
			preprocessing();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
		throws Exception
	{
		BufferedWriter combine_br = new BufferedWriter(new FileWriter(combine_file));
		Iterator<Map.Entry<Integer, String>> iterator = chineseMaps.entrySet().iterator();

		Integer numLine = 1;
		String timeCode = null;
		while (iterator.hasNext())
		{
			Map.Entry<Integer, String> next = iterator.next();
			//时间码
			if (next.getKey() % 3 == 2)
			{
				timeCode = next.getValue();
			}
			//台词
			if (next.getKey() % 3 == 0)
			{

				if (!PrUtils.isTotalScene(next.getValue()))
				{
					Integer integer = numLine++;
					combine_br.write((integer).toString());
					combine_br.newLine();

					combine_br.write(timeCode);
					combine_br.newLine();

					String clearChinese =
						ClearUtils.clearSquareBracket(ClearUtils.clearChineseBracket(next.getValue()));
					combine_br.write(clearChinese);
					combine_br.newLine();

					String englishLine = englishMaps.get(next.getKey());
					String clearEnglish = ClearUtils.clearEnglishBracket(ClearUtils.clearSquareBracket(englishLine));
					combine_br.write(clearEnglish);
					combine_br.newLine();

					combine_br.write("");
					combine_br.newLine();
				}
			}
		}
		combine_br.close();
	}

	public static void preprocessing()
		throws Exception
	{

		BufferedReader english_br = new BufferedReader(new FileReader(english_file));
		BufferedReader chinese_br = new BufferedReader(new FileReader(chinese_file));

		Stream<String> englishLines = english_br.lines();
		Iterator<String> englishIterator = englishLines.iterator();
		convert2Map(englishIterator, englishMaps);

		Stream<String> chineseLines = chinese_br.lines();
		Iterator<String> chineseIterator = chineseLines.iterator();
		convert2Map(chineseIterator, chineseMaps);
		english_br.close();
		chinese_br.close();
		System.out.println("英文长度：" + englishMaps.size());
		System.out.println("中文长度：" + chineseMaps.size());
	}

	private static void convert2Map(Iterator<String> lineIterator, HashMap<Integer, String> lineMaps)
	{
		int mapKey = 1;
		StringBuilder lineSB = null;
		while (lineIterator.hasNext())
		{
			String next = lineIterator.next();
			if (PrUtils.isNumLine(next))
			{
				lineMaps.put(mapKey++, next);
				continue;
			}
			if (PrUtils.isTimeCode(next))
			{
				lineSB = new StringBuilder("");
				lineMaps.put(mapKey++, next);
				continue;
			}
			//如果隔行栏
			if (next.trim().equals(""))
			{
				lineMaps.put(mapKey++, lineSB.toString());
				//lineMaps.put(mapKey++, next);
				continue;
			}
			lineSB.append(next);
		}
	}
}
