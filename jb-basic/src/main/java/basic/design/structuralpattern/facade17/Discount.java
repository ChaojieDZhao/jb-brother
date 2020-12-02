package basic.design.structuralpattern.facade17;

public class Discount
{
	int getDiscount(String discountCode)
	{
		return Math.abs(discountCode.hashCode()) % 3;
	}
}
