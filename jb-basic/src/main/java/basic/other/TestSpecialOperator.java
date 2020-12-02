package basic.other;

/**
 * @describe 测试特殊运算符
 */
public class TestSpecialOperator
{
	/**
	 * Java采用”2的补码“(Two's Complement)编码负数，它是一种数值的编码方法，要分二步完成：第一步，每一个二进制位都取相反值，0变成1，1变成0。
	 * 比如，+8的二进制编码是00001000，取反后就是11110111。第二步，将上一步得到的值加1。
	 * 11110111就变成11111000。所以，00001000的2的补码就是11111000。也就是说，-8在计算机（8位机）中就是用11111000表示。
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(0 & 0);
		System.out.println(1 & 0);
		System.out.println(2 & 3); //10 & 11 = 10 = 2
		System.out.println(6 & 5); //110 & 101 = 100 = 4
		System.out.println(-1 & -2);
		System.out.println(-1 & -3);
		System.out.println(-2 & -3);
	}

}
