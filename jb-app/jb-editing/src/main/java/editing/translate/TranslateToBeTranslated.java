package editing.translate;


import editing.translate.baidu.BaiduTranslate;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

import static editing.FileName.TRANSLATED;
import static editing.FileName.TO_BE_TRANSLATED;

public class TranslateToBeTranslated
{

	public static void main(String[] args) throws IOException {
		File If = new File(TRANSLATED);
		BufferedWriter TRANSLATED_BW = null;
		try {
			File of = new File(TO_BE_TRANSLATED);
			TRANSLATED_BW = new BufferedWriter(new FileWriter(If));
			BufferedReader before_br = new BufferedReader(new FileReader(of));
			Stream<String> lines = before_br.lines();
			Iterator<String> iterator = lines.iterator();
			StringBuffer sb = new StringBuffer("");
			while (iterator.hasNext()) {
				String next = iterator.next();
				System.out.println(next.length());
				System.out.println(next);
				if(next.length() == 0){
					TRANSLATED_BW.newLine();
					continue;
				}
				//TRANSLATED_BW.write(next);
				//TRANSLATED_BW.newLine();
				TRANSLATED_BW.write(BaiduTranslate.translate(next));
				TRANSLATED_BW.newLine();
				//TRANSLATED_BW.write(YDTranslateV3.translate(next));
				//TRANSLATED_BW.newLine();
				Thread.sleep(1000);
			}
			String replaceAll = sb.toString().replaceAll("\\*|\\#|\\}","").replaceAll("\\{\\@code","").replaceAll("\\{\\@link","");
			replaceAll=replaceAll.replaceAll("\\<p\\>","");
			String s = replaceAll.replaceAll(" +", " ");
			System.out.println(s);
			//String translate = BaiduTranslate.translate(s);
			//String translate1 = YDTranslateV3.translate(s);
			//System.out.println(translate1);
			//System.out.println(translate);
			before_br.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			TRANSLATED_BW.flush();
			TRANSLATED_BW.close();
		}
	}


}
