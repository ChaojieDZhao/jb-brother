package initial.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDBHelper
{
	private static BasicDataSource ds = new BasicDataSource();

	static
	{
		InputStream is = Thread.currentThread()
			.getContextClassLoader()
			.getResourceAsStream("initial/oracle.properties");
		Properties prop = new Properties();
		try
		{
			prop.load(is);
			ds.setDriverClassName(prop.getProperty("driver"));
			ds.setUrl(prop.getProperty("url"));
			ds.setUsername(prop.getProperty("username"));
			ds.setPassword(prop.getProperty("password"));
			ds.setInitialSize(Integer.valueOf(prop.getProperty("initialSize")));
			ds.setMaxIdle(Integer.valueOf(prop.getProperty("maxIdle")));
			ds.setMaxTotal(Integer.valueOf(prop.getProperty("maxTotal")));
			ds.setMinIdle(Integer.valueOf(prop.getProperty("minIdle")));
			System.out.println(ds.getDriverClassName() + ds.getUrl());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection()
		throws SQLException
	{
		return ds.getConnection();
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
			if (ps != null)
			{
				ps.close();
			}
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}