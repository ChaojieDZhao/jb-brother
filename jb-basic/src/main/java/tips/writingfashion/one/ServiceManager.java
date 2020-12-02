package tips.writingfashion.one;

public enum ServiceManager
{
	INSTANCE;

	public void boot()
	{
		beforeBoot("ni", "hao");
		startup();
		afterBoot();
	}

	public void shutdown()
	{
		System.out.println("终于结束了");
	}

	private void beforeBoot(String ni, String hao)
	{
		System.out.println(ni + hao);
		System.out.println("预先进行一波这个");
	}

	private void startup()
	{
		System.out.println("开始启动了");
	}

	private void afterBoot()
	{
		System.out.println("终于结束了");
	}
}
