package basic.io.nio;

import java.nio.*;
import java.util.Arrays;

/**
 * 容量（capacity）：所包含的元素个数。缓存区的容量不能为负，且一旦定义无法变更。
 * <p>
 * 限制（limit）：第一个不应该被读取或写入的索引。缓存区的限制不能为负，且不能大于容量（capacity）。
 * <p>
 * 位置（position）：下一个要读取或写入的索引。不能为负数，也不能超过限制（limit）。
 * <p>
 * 标记（mark）：标记当前位置的索引，暂存。如果定义了标记，则在将位置或限制调整为小于该标记的值时，该标记将被丢弃。
 * <p>
 * 剩余（remaining）：当前位置与限制之间的元素数 （limit - position）。
 * <p>
 * 1、创建并分配指定大小的buffer空间
 * <p>
 * 2、写入数据到Buffer
 * <p>
 * 3、调用flip()方法
 * <p>
 * 4、从Buffer中读取数据 （如有必要，需要检查是否足够）
 * <p>
 * 5、调用clear()方法或者compact()方法
 * <p>
 * remaining = limit - position; remaining>1 （除以2） asCharBuffer asShortBuffer
 * remaining>2 （除以4） asIntBuffer asFloatBuffer remaining>3 （除以8） asLongBuffer
 * asDoubleBuffer
 * <p>
 * asReadOnlyBuffer（只读） 一个char=2byte 一个int=4byte 一个long=8byte
 */
public class TestChapter_NIOBuffer
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
