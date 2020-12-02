package initial.start;

import initial.util.OracleDBHelper;
import org.junit.Test;

import java.sql.Connection;

public class OracleStartTest
{
	@Test
	public void test5()
		throws Exception
	{
		while (true)
		{
			Connection connection = OracleDBHelper.getConnection();
			System.out.println(connection);
		}
	}
}
