package basic.design.behavioralpattern.state20;

/**
 * @describe 状态模式（State）
 * 核心思想就是：当对象的状态改变时，同时改变其行为，很好理解！
 */
public class TestStatePattern
{
	public static void main(String args[])
	{
		String smallData = "小数据";
		String middleData = "介于小数据和大数据之间的数据";
		String bifgData = "这里就假定这是一个很大很大很大的数据";
		SaveDataController saveDataController = new SaveDataController();
		saveDataController.save(smallData);
		saveDataController.save(middleData);
		saveDataController.save(bifgData);
	}
}