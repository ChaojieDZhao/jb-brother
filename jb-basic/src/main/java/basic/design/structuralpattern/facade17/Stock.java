package basic.design.structuralpattern.facade17;

import java.util.Random;

/**
 * @describe 库存系统
 */
public class Stock
{
	boolean hasStock(String product)
	{
		return new Random().nextInt(Math.abs(product.hashCode())) > 0;//模拟是否还有库存
	}
}