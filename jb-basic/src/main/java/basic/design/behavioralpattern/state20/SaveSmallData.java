package basic.design.behavioralpattern.state20;

//具体状态
public enum SaveSmallData implements ISaveData
{
	instance;

	@Override
	public void save(Object data)
	{
		System.out.println("保存到Redis:" + data);
	}
}
