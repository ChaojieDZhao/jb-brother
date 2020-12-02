package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

public class GBK2UTF8AppUtils
{

	/**
	 * 将制定目录下的所有Java源文件的编码格式从GBK修改为UTF-8
	 */
	public static void main(String[] args)
		throws Exception
	{
		// GBK编码格式源码路径
		//String srcDirPath = "C:\\Users\\shibdz\\Downloads\\Wongsir-bio-nio-aio-master\\bio-nio-aio\\src\\aio\\";
		String srcDirPath = "";
		// 转为UTF-8编码格式源码路径
		String utf8DirPath = srcDirPath;
		// 获取所有java文件
		Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), new String[] {"java"}, true);
		for (File javaGbkFile : javaGbkFileCol)
		{
			// UTF8格式文件路径
			String utf8FilePath2 = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
			// 使用GBK读取数据，然后用UTF-8写入数据
			FileUtils.writeLines(new File(utf8FilePath2), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
		}
	}
}