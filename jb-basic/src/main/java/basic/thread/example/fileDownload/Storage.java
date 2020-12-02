package basic.thread.example.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * on 2017/8/5.
 */
public class Storage implements Closeable, AutoCloseable
{
	private static final Logger log = LoggerFactory.getLogger(Storage.class);

	protected final AtomicLong totalWrites = new AtomicLong(0);

	private final RandomAccessFile storeFile;

	private final FileChannel storeChannel;

	public Storage(long fileSize, String fileShortName)
		throws FileNotFoundException
	{
		String fullFileName = "D:" + "/" + fileShortName;
		String localFileName = createStoreFile(fileSize, fullFileName);
		storeFile = new RandomAccessFile(localFileName, "rw");
		storeChannel = storeFile.getChannel();
	}

	public int store(long offset, ByteBuffer bytebuffer)
		throws IOException
	{
		int length;
		storeChannel.write(bytebuffer, offset);
		length = bytebuffer.limit();
		totalWrites.addAndGet(length);
		return length;
	}

	public long getTotalWrites()
	{
		return totalWrites.get();
	}

	private String createStoreFile(long fileSize, String fullFileName)
		throws FileNotFoundException
	{
		File file = new File(fullFileName);
		log.info("Create local file: %s", fullFileName);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		try
		{
			raf.setLength(fileSize);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				raf.close();
			}
			catch (IOException e)
			{
			}
		}
		return fullFileName;
	}

	@Override
	public void close()
	{
		if (storeChannel.isOpen())
		{
			try
			{
				storeChannel.close();
				storeFile.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}
