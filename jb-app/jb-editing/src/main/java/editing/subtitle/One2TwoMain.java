package editing.subtitle;

import com.google.common.collect.Maps;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;

public class One2TwoMain
{
	public static void main(String[] args)
		throws Exception
	{

		File sub_english_file = new File(CURRENT_FILE_DIR + "sub_english_file.srt");
		File sub_chinese_file = new File(CURRENT_FILE_DIR + "sub_chinese_file.srt");
		File combine_file = new File(CURRENT_FILE_DIR + "combine.srt");
		BufferedWriter sub_english_br = new BufferedWriter(new FileWriter(sub_english_file));
		BufferedWriter sub_chinese_br = new BufferedWriter(new FileWriter(sub_chinese_file));
		BufferedReader combine_br = new BufferedReader(new FileReader(combine_file));

		Stream<String> lines = combine_br.lines();
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
			if (next.getKey() % 5 == 1)
			{
				Integer integer = numLine++;
				System.out.println(next.getValue());
				sub_english_br.write((integer).toString());
				sub_english_br.newLine();
				sub_chinese_br.write((integer).toString());
				sub_chinese_br.newLine();
			}

			if (next.getKey() % 5 == 2)
			{
				System.out.println(next.getValue());
				sub_english_br.write((next.getValue()));
				sub_english_br.newLine();
				sub_chinese_br.write((next.getValue()));
				sub_chinese_br.newLine();
			}

			if (next.getKey() % 5 == 3)
			{
				writeLine(sub_chinese_br, next);
			}

			if (next.getKey() % 5 == 4)
			{
				writeLine(sub_english_br, next);
			}
		}
		sub_english_br.flush();
		sub_chinese_br.flush();
		combine_br.close();
		sub_english_br.close();
		sub_chinese_br.close();
	}

	private static void writeLine(BufferedWriter bw, Map.Entry<Integer, String> next)
		throws IOException
	{
		System.out.println(next.getValue());
		bw.write((next.getValue()));
		bw.newLine();
		bw.write("");
		bw.newLine();
	}
}
