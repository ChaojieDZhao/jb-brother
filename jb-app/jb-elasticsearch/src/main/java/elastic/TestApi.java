package elastic;

import initial.util.MysqlDBHelper;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import utils.IndexDocument;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestApi
{
	private static ExecutorService executorService = new ThreadPoolExecutor(20, 25, 0L, TimeUnit.MILLISECONDS,
		new LinkedBlockingQueue<Runnable>(100000), new ThreadPoolExecutor.CallerRunsPolicy());

	private static volatile AtomicInteger count = new AtomicInteger(0);

	private static Settings settings = Settings.builder().put("client.transport.sniff", true).build();

	private static String host = "localhost";

	private static int port = 9300;

	private static volatile Client client;

	@Test
	public void test5()
		throws Exception
	{
		final String sql = "select * from customer_address order by id desc limit ?,?";
		for (int i = 0; i <= 10000000; i++)
		{
			final int num = i;
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
						pstm.setInt(1, num * 100);
						pstm.setInt(2, 100);
						ResultSet resultSet = pstm.executeQuery();
						Client elasticCon = getConnection();
						BulkRequestBuilder bulkRequestBuilder = elasticCon.prepareBulk();
						int row = 0;
						long start = System.currentTimeMillis();
						while (resultSet.next())
						{
							row++;
							count.incrementAndGet();
							String id = resultSet.getString("id");
							String name = resultSet.getString("name");
							String phone = resultSet.getString("phone");
							String province = resultSet.getString("province");
							String city = resultSet.getString("city");
							String area = resultSet.getString("area");
							String area_code = resultSet.getString("area_code");
							IndexDocument doc = new IndexDocument(id);
							doc.put("id", id);
							doc.put("name", name);
							doc.put("phone", phone);
							doc.put("province", province);
							doc.put("city", city);
							doc.put("area", area);
							doc.put("area_code", area_code);

							bulkRequestBuilder.add(client.prepareIndex("customer_address", "CustomerAddress",
								doc.getDocId()).setSource(doc.getSource()));

						}
						BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
						if (bulkResponse.hasFailures())
						{
							BulkItemResponse[] items = bulkResponse.getItems();
							for (int j = 0; j < items.length; j++)
							{
								System.out.println(items[j].getFailureMessage());
							}
						}
						System.out.println("插入" + row + "的时间：" + (System.currentTimeMillis() - start));
						System.out.println("插入" + count.get() + "的总时间：" + (System.currentTimeMillis() - start));
						MysqlDBHelper.closeAll(con, pstm, null);

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

	public Client getConnection()
	{
		if (client == null)
		{
			synchronized (this)
			{
				if (client == null)
				{
					try
					{
						TransportClient transportClient = new PreBuiltTransportClient(settings)
							.addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));

						if (transportClient.connectedNodes().size() == 0)
						{
							throw new Exception("can not connect to elasticsearch");
						}
						client = transportClient;
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		return client;
	}

}