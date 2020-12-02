package basic.thread.example.fileDownload;

/**
 * on 2017/8/6.
 */
public class BigFileDownloaderMain
{
	public static void main(String[] args)
		throws Exception
	{
		String url = "http://down15.huacolor.com:8080/down/860272010/201210/Adobe%20Dreamweaver%20CS6_GzKoo.Cn.rar";
		BigFileDownloader downloader = new BigFileDownloader(url);
		int workerThreadCount = 3;
		long reportInterval = 2;
		downloader.download(workerThreadCount, reportInterval);
	}
}
