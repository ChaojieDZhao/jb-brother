package basic.proxy2kind.jdk;

public interface ISubject
{

	/**
	 * 执行
	 */
	void execute()
		throws InterruptedException;

	/**
	 * 打印
	 */
	void print(String msg)
		throws InterruptedException;
}
