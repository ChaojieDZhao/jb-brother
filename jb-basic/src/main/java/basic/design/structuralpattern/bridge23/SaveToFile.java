package basic.design.structuralpattern.bridge23;

//具体实现
public class SaveToFile implements ISaveData
{
	@Override
	public void save(Object data)
	{
		System.out.println(data + " 存储到文件");
	}
}