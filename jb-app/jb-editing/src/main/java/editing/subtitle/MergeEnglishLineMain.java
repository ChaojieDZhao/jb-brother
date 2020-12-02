package editing.subtitle;

import com.google.common.collect.Maps;
import constant.AppConstant;
import editing.subtitle.utils.LineBlock;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;
import static editing.subtitle.utils.Constant.BLANK_LINE;
import static editing.subtitle.utils.FormatLine.formatEnglishSentences;
import static editing.subtitle.utils.SubtitleUtils.convert2List;

public class MergeEnglishLineMain
{

	public static void main(String[] args)
		throws Exception
	{
		File after_file = new File(AppConstant.RESOURCE_PATH + "english.srt");
		File before_file = new File(CURRENT_FILE_DIR + "english_need_merge.srt");
		File merge_file = new File(AppConstant.RESOURCE_PATH + "/editing/" + "MERGE-LINE");
		BufferedWriter after_br = new BufferedWriter(new FileWriter(after_file));
		BufferedReader before_br = new BufferedReader(new FileReader(before_file));
		BufferedReader merge_br = new BufferedReader(new FileReader(merge_file));

		Stream<String> lines = merge_br.lines();
		Iterator<String> iterator = lines.iterator();
		Map<Integer, Integer> mergeMap = Maps.newHashMap();
		while (iterator.hasNext())
		{
			String next = iterator.next();
			String[] split = next.split("\\ ");
			if (split.length == 1)
			{
				mergeMap.put(Integer.valueOf(split[0]), 1);
			}
			else
			{
				mergeMap.put(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
			}
		}

		ArrayList<LineBlock> lineBlocks = convert2List(before_br.lines(), true);
		int lineNum = 1;
		for (int i = 0; i < lineBlocks.size(); i++, lineNum++)
		{
			LineBlock lineBlock = lineBlocks.get(i);
			after_br.write(String.valueOf(lineNum));
			after_br.newLine();
			if (mergeMap.containsKey(lineBlock.getNum()))
			{
				String timeCodeStart, timeCodeEnd = null;
				StringBuilder egLineSB = new StringBuilder("");
				egLineSB.append(lineBlock.getEgLine());
				timeCodeStart = lineBlock.getTimeCode().substring(0, 17);
				Integer count = mergeMap.get(lineBlock.getNum());
				Integer integer = 0;
				while (integer < count)
				{
					int i1 = ++i;
					egLineSB.append(" " + lineBlocks.get(i1).getEgLine());
					timeCodeEnd = lineBlocks.get(i1).getTimeCode().substring(17);
					integer++;
				}
				after_br.write(timeCodeStart + timeCodeEnd);
				after_br.newLine();
				after_br.write(formatEnglishSentences(egLineSB.toString()));
				after_br.newLine();
			}
			else
			{
				after_br.write(lineBlock.getTimeCode());
				after_br.newLine();
				after_br.write(formatEnglishSentences(lineBlock.getEgLine()));
				after_br.newLine();
			}
			after_br.write(BLANK_LINE);
			after_br.newLine();
		}
		after_br.flush();
		after_br.close();
	}
}
