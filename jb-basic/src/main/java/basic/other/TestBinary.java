package basic.other;

/**
 * @describe 测试Java进制，二进制，十进制，十六进制
 */
public class TestBinary
{
	public static void main(String[] args)
	{
		System.out.println(0b101); //二进制:5  （0b开头的）
		System.out.println(0e1011); //0.0
		System.out.println(011); //八进制:9   (0开头的)
		System.out.println(11); //十进制:11
		System.out.println(0x11C); //十六进制:284   （0x开头的）

		System.out.printf("%010x\n", 7); //0000000007   按10位十六进制输出，向右靠齐，左边用0补齐
		System.out.printf("%010o\n", 13); //0000000015    按10位八进制输出，向右靠齐，左边用0补齐

		System.out.printf("%x\n", 7); //7   按16进制输出
		System.out.printf("%o\n", 13); //15   按8进制输出

		System.out.println(Integer.toBinaryString(11)); //1011 二进制
	}

}
