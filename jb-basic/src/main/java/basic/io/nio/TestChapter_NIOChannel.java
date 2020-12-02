package basic.io.nio;

import java.nio.*;
import java.util.Arrays;

/**
 * 在操作系统知识中，通道指的是独立于CPU的专管I/O的控制器，控制外围I/O设备与内存进行信息交换。在采用通道方式的指令系统中，除了供CPU编程使用的机器指令系统外，还设置另外供通道专用的一组通道指令，用通道指令编制通道程序，读取或存入I/O设备。当需要进行I/O操作时，CPU只需启动通道，然后可以继续执行自身程序，通道则执行通道程序，管理与实现I/O操作，当完成时通道再汇报CPU即可。
 * <p>
 * 如此，通道使得CPU、内存与I/O操作之间达到更高的并行程度。
 * <p>
 * Stream（流），是单向的，不能在一个流中读写混用，要么一路读直到关闭，要么一路写直到关闭。同时流是直接依赖CPU指令，与内存进行I/O操作，CPU指令会一直等I/O操作完成。
 * <p>
 * 而Channel（通道）
 * ，是双向的，可以借助Buffer（缓冲区）在一个通道中读写混用，可以交叉读数据、写数据到通道，而不用在读写操作后立刻关闭。另外还可以在两个通道中直接对接传输。通道不依赖CPU指令，有专用的通道指令，在接收CPU指令，就可以独立与内存完成I/O操作，只有在I/O操作完成后通知CPU，在此期间CPU是不用一直等待。
 */
public class TestChapter_NIOChannel
{
	public static void main(String[] args)
	{
		ByteBuffer byteBuffer1 = ByteBuffer.allocate(31);
		printBuffer(byteBuffer1, "byteBuffer1");

		/**
		 * byteBuffer1的remaining>1 （除以2）
		 */
		CharBuffer charBuffer = byteBuffer1.asCharBuffer();
		printBuffer(charBuffer, "charBuffer");
		charBuffer.put('a');
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));

		/**
		 * byteBuffer1的remaining>1 （除以2）
		 */
		ShortBuffer shortBuffer = byteBuffer1.asShortBuffer();
		printBuffer(shortBuffer, "shortBuffer");
		shortBuffer.put((short)3);
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));

		/**
		 * byteBuffer1的remaining>2 （除以4）
		 */
		IntBuffer intBuffer = byteBuffer1.asIntBuffer();
		printBuffer(intBuffer, "intBuffer");
		intBuffer.put(4);
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));

		/**
		 * byteBuffer1的remaining>3 （除以8）
		 */
		LongBuffer longBuffer = byteBuffer1.asLongBuffer();
		printBuffer(longBuffer, "longBuffer");
		longBuffer.put(120);
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));

		/**
		 * byteBuffer1的remaining>2 （除以4）
		 */
		FloatBuffer floatBuffer = byteBuffer1.asFloatBuffer();
		printBuffer(floatBuffer, "floatBuffer");
		floatBuffer.put(9f);
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));

		/**
		 * byteBuffer1的remaining>3 （除以8）
		 */
		DoubleBuffer doubleBuffer = byteBuffer1.asDoubleBuffer();
		printBuffer(doubleBuffer, "doubleBuffer");
		doubleBuffer.put(1);
		if (byteBuffer1.hasArray())
			System.out.println("byteBuffer1 data=" + Arrays.toString(byteBuffer1.array()));
	}

	private static void printBuffer(Buffer buffer, String name)
	{
		System.out.println((name != null && !name.isEmpty() ? name + " " : "") + "position=" + buffer.position()
			+ ",limit=" + buffer.limit() + ",remaining=" + buffer.remaining() + ",capacity=" + buffer.capacity());
	}
}
