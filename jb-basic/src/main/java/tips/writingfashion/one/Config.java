package tips.writingfashion.one;

/**
 * @describe 还能这么写配置文件！！！
 */
public class Config
{

	public static class Agent
	{
		public final static String APPLICATION_CODE = "";

		public final static int SAMPLE_N_PER_3_SECS = -1;

		public final static String IGNORE_SUFFIX = ".jpg,.jpeg,.js,.css,.png,.bmp,.gif,.ico,.mp3,.mp4,.html,.svg";
	}

	public static class Collector
	{
		public static long GRPC_CHANNEL_CHECK_INTERVAL = 30;

		public static long APP_AND_SERVICE_REGISTER_CHECK_INTERVAL = 3;

		public static long DISCOVERY_CHECK_INTERVAL = 60;

		public static String SERVERS = "";

		public static String DISCOVERY_SERVICE_NAME = "/agentstream/grpc";
	}

	public static class Jvm
	{
		public static int BUFFER_SIZE = 60 * 10;
	}

	public static class Buffer
	{
		public static int CHANNEL_SIZE = 5;

		public static int BUFFER_SIZE = 300;
	}

	public static class Dictionary
	{
		public static int APPLICATION_CODE_BUFFER_SIZE = 10 * 10000;

		public static int OPERATION_NAME_BUFFER_SIZE = 1000 * 10000;
	}

	public static class Logging
	{
		public static String FILE_NAME = "skywalking-api.log";

		public static String DIR = "";

		public static int MAX_FILE_SIZE = 300 * 1024 * 1024;

	}

	public static class Plugin
	{
		public static class MongoDB
		{
			public static boolean TRACE_PARAM = false;
		}
	}
}
