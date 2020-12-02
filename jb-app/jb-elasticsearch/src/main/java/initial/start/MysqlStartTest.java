package initial.start;

import initial.util.MysqlDBHelper;
import org.junit.Test;

import java.sql.Connection;

public class MysqlStartTest
{
	@Test
	public void test5()
		throws Exception
	{
		for (int i = 0; i < 25; i++)
		{
			Connection connection = MysqlDBHelper.getConnection();
			System.out.println(connection);
			Thread.sleep(5000);
		}
	}
}
