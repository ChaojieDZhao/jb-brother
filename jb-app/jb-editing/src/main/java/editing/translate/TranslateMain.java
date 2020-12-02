package editing.translate;

import com.google.common.collect.Maps;
import editing.translate.baidu.BaiduTranslate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;

public class TranslateMain
{

	public static void main(String[] args)
		throws Exception
	{
		File of = new File(CURRENT_FILE_DIR + "combine.srt");

		BufferedReader ofbr = new BufferedReader(new FileReader(of));

		Stream<String> lines = ofbr.lines();
		Iterator<String> iterator = lines.iterator();
		HashMap<Integer, String> maps = Maps.newHashMap();
		int mapKey = 1;
		while (iterator.hasNext())
		{
			String next = iterator.next();
			//System.out.println(mapKey + " : "+next);
			maps.put(mapKey++, next);
		}
		while (true)
		{
			String line = scanner("字幕号，空格分隔");
			String[] split = line.split("\\s+");
			StringBuilder sb = new StringBuilder("");
			Integer firstLine = Integer.valueOf(split[0]);
			sb.append(maps.get(firstLine * 5 - 1)).append(" ");
			if (split.length != 1)
			{
				Integer linesBlew = Integer.valueOf(split[1]);
				for (Integer i = 1; i <= linesBlew; i++)
				{
					sb.append(maps.get((firstLine + i) * 5 - 1)).append(" ");
				}
			}
			System.out.println(YDTranslateV3.translate(sb.toString()));
			System.out.println(sb.toString());
			System.out.println();
			System.out.println(BaiduTranslate.translate(sb.toString()));
			System.out.println(sb.toString());
			System.out.println();
		}
	}

	public static String scanner(String tip)
	{
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext())
		{
			String ipt = scanner.next();
			return ipt;
		}
		return null;
	}

}
