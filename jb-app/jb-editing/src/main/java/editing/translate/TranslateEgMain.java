package editing.translate;

import com.google.common.collect.Lists;
import editing.PrUtils;
import editing.subtitle.utils.ClearUtils;
import editing.subtitle.utils.LineBlock;
import editing.translate.baidu.BaiduTranslate;

import java.io.*;
import java.util.ArrayList;

import static editing.FileName.CURRENT_FILE_DIR;
import static editing.subtitle.utils.Constant.BLANK_LINE;
import static editing.subtitle.utils.FormatLine.formatChineseSentences;
import static editing.subtitle.utils.FormatLine.formatEnglishSentences;
import static editing.subtitle.utils.SubtitleUtils.convert2List;

public class TranslateEgMain
{

	public static void main(String[] args)
		throws Exception
	{
		File english_file = new File(CURRENT_FILE_DIR + "english.srt");
		File combine_BD_file = new File(CURRENT_FILE_DIR + "combine_BD.srt");

		BufferedReader english_br = new BufferedReader(new FileReader(english_file));
		BufferedWriter combine_BD_br = new BufferedWriter(new FileWriter(combine_BD_file));
		ArrayList<LineBlock> lineBlocks = convert2List(english_br.lines(), true);
		Integer lineNum = 1;
		ArrayList<LineBlock> newLineBlocks = Lists.newArrayList();
		for (LineBlock lineBlock : lineBlocks)
		{
			if (!PrUtils.isTotalSceneByEnglish(lineBlock.getEgLine()))
			{
				LineBlock newlineBlock = new LineBlock();
				newlineBlock.setTimeCode(lineBlock.getTimeCode());
				newlineBlock.setNum(lineNum++);
				String clearEnglish =
					formatEnglishSentences(ClearUtils.clearEnglishBracket(ClearUtils.clearSquareBracket(lineBlock.getEgLine())));
				newlineBlock.setEgLine(clearEnglish);
				try
				{
					newlineBlock.setChLine(formatChineseSentences(BaiduTranslate.translate(clearEnglish)));
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("百度出问题了");
					System.out.println("百度出问题了");
					System.out.println("百度出问题了");
					System.out.println("睡个5秒");
					Thread.sleep(5000);
					newlineBlock.setChLine(formatChineseSentences(BaiduTranslate.translate(clearEnglish)));
				}
				System.out.println("\n" + newlineBlock.getNum());
				System.out.println("egLine：" + newlineBlock.getEgLine());
				System.out.println("chLine：" + newlineBlock.getChLine());
				newLineBlocks.add(newlineBlock);
			}
		}
		for (LineBlock lineBlock : newLineBlocks)
		{
			combine_BD_br.write(String.valueOf(lineBlock.getNum()));
			combine_BD_br.newLine();
			combine_BD_br.write(lineBlock.getTimeCode());
			combine_BD_br.newLine();
			combine_BD_br.write(lineBlock.getChLine());
			combine_BD_br.newLine();
			combine_BD_br.write(lineBlock.getEgLine());
			combine_BD_br.newLine();
			combine_BD_br.write(BLANK_LINE);
			combine_BD_br.newLine();
		}
		combine_BD_br.flush();
		combine_BD_br.close();
		english_br.close();
	}
}
