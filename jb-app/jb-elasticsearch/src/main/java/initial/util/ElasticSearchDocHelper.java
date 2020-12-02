package initial.util;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ElasticSearchDocHelper
{
	private static Settings settings =
		Settings.builder().put("client.transport.sniff", true).put("cluster.name", "my-application").build();

	private static String host = "localhost";

	private static int port = 9300;

	private static volatile Client client;

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