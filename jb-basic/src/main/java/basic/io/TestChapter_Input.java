package basic.io;

import org.junit.Test;

import java.io.*;

public class TestChapter_Input
{
	@Test
	public void readFileAsByte()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb.txt";
		InputStream is = null;
		try
		{
			is = new FileInputStream(filepath);
			int data = -1;
			while ((data = is.read()) != -1)
			{// -1 表示读取到达文件结尾
				// 操作数据
				System.out.print((byte)data + " ");
			}
		}
		finally
		{
			if (is != null)
			{
				is.close();// 关闭流
			}
		}
	}

	@Test
	public void readFileAsByteArray()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb.txt";
		InputStream is = null;
		try
		{
			is = new BufferedInputStream(new FileInputStream(filepath));// 组装BufferedInputStream流，加入缓冲能力
			byte[] data = new byte[256];
			int len = -1;
			while ((len = is.read(data)) != -1)
			{// -1 表示读取到达文件结尾
				// 操作数据
				for (int i = 0; i < len; i++)
				{
					System.out.print(data[i] + " ");
				}
			}
		}
		finally
		{
			if (is != null)
			{
				is.close();// 关闭流
			}
		}
	}

	@Test
	public void readFileAsChar()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb.txt";
		Reader r = null;
		try
		{
			r = new FileReader(filepath);
			int data = -1;
			while ((data = r.read()) != -1)
			{// -1 表示读取到达文件结尾
				// 操作数据
				System.out.print((char)data);
			}
		}
		finally
		{
			if (r != null)
			{
				r.close();// 关闭流
			}
		}
	}

	@Test
	public void readFileAsCharArray()
		throws IOException
	{
		String filepath = "D:\\zcjsooooooft\\mmb.txt";
		Reader r = null;
		try
		{
			r = new BufferedReader(new FileReader(filepath));// 组装BufferedReader流，加入缓冲能力
			char[] data = new char[256];
			int len = -1;
			while ((len = r.read(data)) != -1)
			{// -1 表示读取到达文件结尾
				//操作数据
				for (int i = 0; i < len; i++)
				{
					System.out.print(data[i]);
				}
			}
		}
		finally
		{
			if (r != null)
			{
				r.close();// 关闭流
			}
		}
	}

}
