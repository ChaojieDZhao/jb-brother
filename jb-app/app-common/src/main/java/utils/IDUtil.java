package utils;

import java.util.Random;

public class IDUtil
{

	//固定位
	private static final String FIXEDNUM = "007";

	//目前给商家或者工人留的位数4
	private static final int BYTENUM = 4;

	//工人为订单号前缀
	private static final String WORKER = "W";

	//商检订单号前缀
	private static final String MERCHANRS = "M";

	//随机数范围最大值
	private static final int RANDOM_MAX = 9999;

	//随机数范围最小值
	private static final int RANDOM_MIN = 1000;

	private static Random random = new Random();

	/**
	 * 获取工人订单号
	 *
	 * @param id
	 * @return
	 */
	public static String getWorkerOrderNo(Integer id, int payMethod, int serviceType)
	{

		return WORKER + payMethod + serviceType + MyDateUtils.getDate("yyMMdd") + handleId(id) + getRandomNumber() +
			FIXEDNUM + gettTimeMillis();
	}

	/**
	 * 获取商家订单号
	 *
	 * @param id
	 * @return
	 */
	public static String getMerchantsOrderNo(Integer id, int payMethod, int serviceType)
	{

		return MERCHANRS + payMethod + serviceType + MyDateUtils.getDate("yyMMddHH") + handleId(id) +
			getRandomNumber() +
			FIXEDNUM + gettTimeMillis();
	}

	/**
	 * 如果id尾数达不到7位,保存前面的尾数后面不足尾数补零
	 *
	 * @param id
	 * @return
	 */
	private static String handleId(Integer id)
	{
		int number = id.toString().length();
		return number != BYTENUM ? getByteNumId(number, id.toString()) : id.toString();
	}

	/**
	 * 商家或者工人id不到规定为，后面补零方法
	 *
	 * @param number
	 * @param id
	 * @return
	 */
	private static String getByteNumId(int number, String id)
	{
		if (number < BYTENUM)
		{
			for (int i = 0; i < BYTENUM - number; i++)
			{
				id = 0 + id;
			}
		}
		else if (number > BYTENUM)
		{
			id = id.substring(number - 4, number);
		}
		return id;
	}

	/**
	 * 毫秒数
	 *
	 * @return
	 */
	private static String gettTimeMillis()
	{

		return (System.currentTimeMillis() + "").substring(5);
	}

	/**
	 * 产生两位随机数
	 *
	 * @return
	 */
	private static int getRandomNumber()
	{
		int s = random.nextInt(RANDOM_MAX) % (RANDOM_MAX - RANDOM_MIN + 1) + RANDOM_MIN;
		return s;
	}

}
