package initial.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MysqlDBHelper
{
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;

	// 线程池
	private static final BasicDataSource DATA_SOURCE;

	static
	{
		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread()
			.getContextClassLoader()
			.getResourceAsStream("initial/dataSourceConfig.properties");
		try
		{
			properties.load(inputStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String user = properties.getProperty("name");
		String password = properties.getProperty("password");
		String driver = properties.getProperty("driverClassName");
		String url = properties.getProperty("url");
		try
		{
			if (inputStream != null)
			{
				inputStream.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(user);
		DATA_SOURCE.setPassword(password);
		// /设置空闲和借用的连接的最大总数量，同时可以激活。
		DATA_SOURCE.setMaxTotal(40);
		// 设置初始大小
		DATA_SOURCE.setInitialSize(15);
		// 最小空闲连接
		DATA_SOURCE.setMinIdle(8);
		// 最大空闲连接
		DATA_SOURCE.setMaxIdle(10);
		// 超时等待时间毫秒
		DATA_SOURCE.setMaxWaitMillis(2 * 10000);
		// 只会发现当前连接失效，再创建一个连接供当前查询使用
		DATA_SOURCE.setTestOnBorrow(true);
		// removeAbandonedTimeout ：超过时间限制，回收没有用(废弃)的连接（默认为 300秒，调整为180）
		DATA_SOURCE.setRemoveAbandonedTimeout(180);
		// removeAbandoned ：超过removeAbandonedTimeout时间后，是否进
		// 行没用连接（废弃）的回收（默认为false，调整为true)
		DATA_SOURCE.setRemoveAbandonedOnBorrow(true);
		DATA_SOURCE.setTestOnReturn(true);
		DATA_SOURCE.setTestOnReturn(true);
		DATA_SOURCE.setRemoveAbandonedOnMaintenance(true);
		// 记录日志
		DATA_SOURCE.setLogAbandoned(true);
		// 设置自动提交
		DATA_SOURCE.setDefaultAutoCommit(true);
	}

	public static Connection getConnection()
		throws Exception
	{
		Connection con = CONNECTION_HOLDER.get();
		if (con == null)
		{
			try
			{
				con = DATA_SOURCE.getConnection();
			}
			catch (SQLException e)
			{
				System.out.println("get connection failure:" + e);
			}
			finally
			{
				CONNECTION_HOLDER.set(con);
			}
		}
		return con;
	}

	public static void closeAll(Connection con, Statement pstm, ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			if (pstm != null)
			{
				pstm.close();
				pstm = null;
			}
			if (con != null)
			{
				con.close();
				CONNECTION_HOLDER.remove();// 删除
				con = null;
			}
		}
		catch (Exception e)
		{
			System.out.print("关闭数据库连接时出现异常");
			e.printStackTrace();
		}
	}

	public void resetAll(Connection con, Statement pstm, ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.refreshRow();
			}
			if (pstm != null)
			{
				pstm.close();
			}
			if (con != null)
			{
				con.close();
				CONNECTION_HOLDER.remove();// 删除
			}
		}
		catch (Exception e)
		{
			System.out.print("关闭数据库连接时出现异常");
			e.printStackTrace();
		}
	}
}
