package editing.subtitle.utils;

import com.google.common.collect.Lists;
import editing.PrUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import static editing.subtitle.utils.Constant.BLANK_LINE;

public class SubtitleUtils
{
	public static ArrayList<LineBlock> convert2List(Stream<String> lineStream, boolean isEgLine)
	{
		Iterator<String> lineIterator = lineStream.iterator();
		ArrayList<LineBlock> lineBlocks = Lists.newArrayList();
		StringBuilder lineSB = null;
		LineBlock lineBlock = null;
		while (lineIterator.hasNext())
		{
			String next = lineIterator.next();
			if (PrUtils.isNumLine(next))
			{
				lineBlock = new LineBlock();
				lineBlock.setNum(Integer.valueOf(next));
				continue;
			}
			if (PrUtils.isTimeCode(next))
			{
				lineSB = new StringBuilder("");
				lineBlock.setTimeCode(next);
				continue;
			}
			//如果隔行栏
			if (next.trim().equals(BLANK_LINE))
			{
				lineBlock.setEgLine(lineSB.toString());
				lineBlocks.add(lineBlock);
				continue;
			}
			lineSB.append(next);
		}
		return lineBlocks;
	}
}
