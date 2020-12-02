package editing.subtitle;

import com.google.common.collect.Maps;
import constant.AppConstant;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static editing.FileName.CURRENT_FILE_DIR;
import static editing.FileName.CURRENT_JAVA_FILE_DIR;
import static editing.subtitle.utils.FormatLine.formatChineseSentences;
import static editing.subtitle.utils.FormatLine.formatEnglishSentences;

public class FormatterJavaMain {

    public static void main(String[] args)
            throws Exception {

        File before_file = new File(CURRENT_JAVA_FILE_DIR);
        BufferedReader before_br = new BufferedReader(new FileReader(before_file));

        Stream<String> lines = before_br.lines();
        Iterator<String> iterator = lines.iterator();
        StringBuffer sb = new StringBuffer("");
        while (iterator.hasNext()) {
            sb.append(iterator.next()+" ");
        }
		String replaceAll = sb.toString().replaceAll("\\*|\\#|\\}","").replaceAll("\\{\\@code","").replaceAll("\\{\\@link","");
		replaceAll=replaceAll.replaceAll("\\<p\\>","");
		System.out.println(replaceAll.replaceAll(" +", " "));
		before_br.close();
    }

}
