package basic.io.nio.demo01;

import basic.io.nio.demo01.client.Client;
import basic.io.nio.demo01.server.Server;

import java.util.Scanner;

public class TestNIO
{
	//测试主方法
	public static void main(String[] args)
		throws Exception
	{
		//运行服务器
		Server.start();
		//避免客户端先于服务器启动前执行代码
		Thread.sleep(100);
		//运行客户端
		Client.start();
		while (Client.sendMsg(new Scanner(System.in).nextLine()))
		{
		}
		;
	}
}
