package initial.start;

import initial.util.DateUtils;
import initial.util.MysqlDBHelper;
import initial.util.RandomValue;
import org.junit.Test;
import utils.SnowFlake;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StartTest
{
	private static ExecutorService executorService = new ThreadPoolExecutor(10, 13, 0L, TimeUnit.MILLISECONDS,
		new LinkedBlockingQueue<Runnable>(100000), new ThreadPoolExecutor.CallerRunsPolicy());

	private static Random random = new Random();

	private static List<String> address = new ArrayList();

	private static List<String> tags = new ArrayList();

	private static List<String> express = new ArrayList();

	static
	{
		initAddress();
		tags.add("父母家");
		tags.add("公司");
		tags.add("老婆公司");
		tags.add("儿子学校");
		express.add("顺丰速运");
		express.add("中通快递");
		express.add("圆通快递");
		express.add("韵达快递");
		express.add("菜鸟包裹");
	}

	SnowFlake snowFlake = new SnowFlake(2, 3);

	public static void initAddress()
	{
		Connection con = null;
		PreparedStatement pstm = null;
		try
		{
			con = MysqlDBHelper.getConnection();
			pstm = con.prepareStatement("select *  from ch_region where type = 1");
			ResultSet resultSet = pstm.executeQuery();
			while (resultSet.next())
			{
				String provinceId = resultSet.getString("code");
				String provinceName = resultSet.getString("name");
				pstm = con.prepareStatement("select *  from ch_region where parent_code = " + provinceId);
				ResultSet resultSet2 = pstm.executeQuery();
				while (resultSet2.next())
				{
					String cityId = resultSet2.getString("code");
					String cityName = resultSet2.getString("name");
					pstm = con.prepareStatement("select *  from ch_region where parent_code = " + cityId);
					ResultSet resultSet3 = pstm.executeQuery();
					while (resultSet3.next())
					{
						String areaId = resultSet3.getString("code");
						String areaName = resultSet3.getString("name");
						address.add(
							provinceId + "_" + cityId + "_" + areaId + "_" + provinceName + cityName + areaName);
					}
				}
			}
			MysqlDBHelper.closeAll(con, pstm, resultSet);
		}
		catch (Exception e)
		{

		}
	}

	public static String getIdCardNum()
	{
		Random r = new Random();
		Integer random = r.nextInt(899999) + 100000;
		String cardNum = "110105198710" + random.toString();
		String randomName = "";
		for (int i = 0; i < 10; i++)
		{
			randomName = randomName + (char)(Math.random() * 26 + 'A');
		}
		String mobilePhoneRandomNum = "1581";// "15818322868";
		for (int i = 0; i < 7; i++)
		{
			mobilePhoneRandomNum = mobilePhoneRandomNum + r.nextInt(9);
		}
		return cardNum;
	}

	@Test
	public void batchAddCustomerAddress()
		throws Exception
	{
		AtomicInteger count = new AtomicInteger(0);
		final String sql =
			"INSERT INTO  tb_customer_address (id, name, phone, province, city, area, email, address, address_str, status, customer_id, zip_code, tag, is_deleted, gmt_create, gmt_modified) " +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		for (int i = 0; i < 3; i++)
		{
			final int num = i;
			final List<String> list = address;
			executorService.submit(new Runnable()
			{
				public void run()
				{
					Connection con = null;
					PreparedStatement pstm = null;
					try
					{
						con = MysqlDBHelper.getConnection();
						pstm = con.prepareStatement(sql);
						for (int k = 0; k < 100; k++)
						{
							long start = System.currentTimeMillis();
							for (int j = 0; j < 120; j++)
							{
								int i = 1;
								pstm.setString(i++, String.valueOf(snowFlake.nextId()));
								// name
								pstm.setString(i++, RandomValue.getChineseName());
								// phone
								pstm.setString(i++, RandomValue.getTel());
								String address = list.get(random.nextInt(list.size()));
								String[] split = address.split("_");
								// province
								Integer x = Integer.valueOf((split[0]));
								pstm.setInt(i++, x);
								// city
								Integer x1 = Integer.valueOf(split[1]);
								pstm.setInt(i++, x1);
								// area
								Integer x2 = Integer.valueOf(split[2]);
								pstm.setInt(i++, x2);
								// email
								pstm.setString(i++, RandomValue.getEmail(5, 20));
								// address
								pstm.setString(i++, split[3] + RandomValue.getRoad());
								// `cuAddressCodeStr`,
								pstm.setString(i++, x + "/" + x1 + "/"
									+ x2);
								// `status`,
								pstm.setInt(i++, RandomValue.getRandomNumber(1, 2));
								// `customerId`,
								pstm.setInt(i++, RandomValue.getRandomNumber(1, 100000000));
								// zipCode
								pstm.setString(i++, String.valueOf(RandomValue.getRandomNumber(100000, 999999)));
								// tag
								pstm.setString(i++, tags.get(random.nextInt(tags.size())));
								// is_delete
								pstm.setInt(i++, RandomValue.getRandomNumber(1, 2));
								pstm.setString(i++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(i++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.addBatch();
							}
							pstm.executeBatch();
							int andIncrement = count.getAndIncrement();
							System.out.println(
								"get me some..." + andIncrement + "  " + Thread.currentThread().getName() + "   " +
									(System.currentTimeMillis() - start));
						}
						MysqlDBHelper.closeAll(con, null, null);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
		while (true)
		{
			if (executorService.isTerminated())
			{
				long end = System.currentTimeMillis();
				break;
			}
		}
	}

	@Test
	public void batchAddOrder()
		throws Exception
	{
		AtomicInteger count = new AtomicInteger(0);
		final String sql =
			"INSERT INTO tb_order (`id`, `user_id`, `addr_id`, `payment`, `payment_type`, `post_fee`, `status`, `shipping_name`, `shipping_code`, `no_annoyance`, `service_price`, `return_price`, `total_weight`, `buyer_rate`, `close_time`, `end_time`, `payment_time`, `consign_time`, `gmt_create`, `gmt_modified`) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		for (int i = 0; i < 3; i++)
		{
			final int num = i;
			final List<String> list = address;
			executorService.submit(new Runnable()
			{
				public void run()
				{
					Connection con = null;
					PreparedStatement pstm = null;
					try
					{
						con = MysqlDBHelper.getConnection();
						pstm = con.prepareStatement(sql);
						for (int k = 0; k < 10; k++)
						{
							long start = System.currentTimeMillis();
							for (int j = 0; j < 12; j++)
							{
								int count = 1;
								pstm.setString(count++, String.valueOf(snowFlake.nextId()));
								pstm.setString(count++, String.valueOf(snowFlake.nextId()));
								pstm.setString(count++, String.valueOf(snowFlake.nextId()));
								pstm.setString(count++, RandomValue.generateDouble(10, 100).toString());
								pstm.setInt(count++, RandomValue.getRandomNumber(1, 4));
								//post_fee
								pstm.setString(count++, RandomValue.generateDouble(4, 8).toString());
								pstm.setInt(count++, RandomValue.getRandomNumber(1, 4));
								pstm.setString(count++, express.get(random.nextInt(express.size())));
								pstm.setString(count++, String.valueOf(snowFlake.nextId()));
								pstm.setInt(count++, RandomValue.getRandomNumber(1, 2));
								//service_fee
								pstm.setString(count++, RandomValue.generateDouble(8, 10).toString());
								pstm.setString(count++, RandomValue.generateDouble(1, 3).toString());
								pstm.setString(count++, RandomValue.generateDouble(1, 4).toString());
								//buyer_rate
								pstm.setInt(count++, RandomValue.getRandomNumber(1, 2));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.setString(count++, DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
								pstm.addBatch();
							}
							pstm.executeBatch();
							int andIncrement = count.getAndIncrement();
							System.out.println(
								"get me some..." + andIncrement + "  " + Thread.currentThread().getName() + "   " +
									(System.currentTimeMillis() - start));
						}
						MysqlDBHelper.closeAll(con, null, null);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
		while (true)
		{
			if (executorService.isTerminated())
			{
				long end = System.currentTimeMillis();
				break;
			}
		}
	}

}
