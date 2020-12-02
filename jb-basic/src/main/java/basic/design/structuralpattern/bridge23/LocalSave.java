package basic.design.structuralpattern.bridge23;

//细化抽象
public class LocalSave extends AbstractSave
{
	public LocalSave(ISaveData saveData)
	{
		super(saveData);
	}

	@Override
	public void save(Object data)
	{
		System.out.print("本地存储：");
		saveData.save(data);
	}
}