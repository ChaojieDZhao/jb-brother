package basic.design.structuralpattern.facade17;

/**
 * @describe 邮费系统
 */
public class Postage
{
	int getPostage(String addr)
	{
		return Math.abs(addr.hashCode()) % 20 + 6;
	}
}
