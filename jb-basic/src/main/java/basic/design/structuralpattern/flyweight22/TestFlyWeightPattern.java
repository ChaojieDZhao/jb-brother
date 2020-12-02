package basic.design.structuralpattern.flyweight22;

/**
 * @describe 享元模式的主要目的是实现对象的共享，即共享池，当系统中对象多的时候可以减少内存的开销，通常与工厂模式一起使用。
 */
public class TestFlyWeightPattern
{

	public static void main(String args[])
	{
		WeatherFactory factory = new WeatherFactory();
		IWeather weather1, weather2, weather3, weather4, weather5, weather6, weather7, weather8;
		weather1 = factory.getFlyWeight("多云", 15);
		weather2 = factory.getFlyWeight("晴", 23);
		weather3 = factory.getFlyWeight("多云", 16);
		weather4 = factory.getFlyWeight("阴", 10);
		weather5 = factory.getFlyWeight("多云", 15);
		weather6 = factory.getFlyWeight("多云", 15);
		weather7 = factory.getFlyWeight("多云", 15);
		weather8 = factory.getFlyWeight("多云", 15);
		weather1.printWeather();
		weather2.printWeather();
		weather3.printWeather();
		weather4.printWeather();
		weather5.printWeather();
		weather6.printWeather();
		weather7.printWeather();
		weather8.printWeather();
		System.out.println("实际对象个数" + factory.getFlyweightSize());
	}
}