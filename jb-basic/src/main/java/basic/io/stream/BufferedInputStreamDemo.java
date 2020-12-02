package basic.io.stream;

import java.io.*;

public class BufferedInputStreamDemo
{

	public static void main(String[] args)
	{
		rename2();
	}

	/**
	 * 使用BufferedInputStream、BufferedOutputStream进行数据的读取与写入。
	 */
	private static void rename2()
	{
		System.out.println(System.getProperty("user.dir"));
		System.out.println(BufferedInputStreamDemo.class.getClassLoader().getResource("").getPath());
		System.out.println(BufferedInputStreamDemo.class.getClassLoader().getResource("io.jpg").getPath());
		String resource = BufferedInputStreamDemo.class.getClassLoader().getResource("").getPath();
		File src = new File(BufferedInputStreamDemo.class.getClassLoader().getResource("io.jpg").getPath());
		File dst = new File(resource + "/dst.jpg");
		try
		{
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dst));
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1)
			{
				out.write(b, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
