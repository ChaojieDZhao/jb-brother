package basic.io;

import org.junit.Test;

import java.io.*;
import java.util.Random;

public class TestChapter_Output
{
	@Test
	public void writeFileAsByte()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb2.txt";
		OutputStream os = null;
		try
		{
			os = new FileOutputStream(filepath);
			os.write('1');
			os.write('2');
			os.write('3');
			os.write('4');
			os.flush();// 把缓冲区内的数据刷新到磁盘

		}
		finally
		{
			if (os != null)
			{
				os.close();// 关闭流
			}
		}
	}

	@Test
	public void writeFileAsByteArray()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb2.txt";
		OutputStream os = null;
		try
		{
			os = new BufferedOutputStream(new FileOutputStream(filepath));
			// 模拟
			byte[] data = new byte[256];
			new Random().nextBytes(data);

			os.write(data);
			os.flush();// 把缓冲区内的数据刷新到磁盘
		}
		finally
		{
			if (os != null)
			{
				os.close();// 关闭流
			}
		}
	}

	@Test
	public void writeFileAsChar()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb2.txt";
		Writer w = null;
		try
		{
			w = new FileWriter(filepath);
			w.write('1');
			w.write('2');
			w.write('3');
			w.write('4');
			w.flush();// 把缓冲区内的数据刷新到磁盘

		}
		finally
		{
			if (w != null)
			{
				w.close();// 关闭流
			}
		}
	}

	@Test
	public void writeFileAsCharArray()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb2.txt";
		Writer w = null;
		try
		{
			w = new BufferedWriter(new FileWriter(filepath));// 组装BufferedWriter流，加入缓冲能力
			// 模拟
			char[] data = new char[256];
			String f = "0123456789abcdefghijklmnopqrstuvwxyz";
			Random rd = new Random();
			for (int i = 0; i < data.length; i++)
			{
				data[i] = f.charAt(rd.nextInt(f.length()));
			}
			w.write(data);
			w.flush();// 把缓冲区内的数据刷新到磁盘
		}
		finally
		{
			if (w != null)
			{
				w.close();// 关闭流
			}
		}
	}

}
