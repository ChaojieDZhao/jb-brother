package basic.proxy2kind.jdk.impl;

import basic.proxy2kind.jdk.ISubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ISubjectImpl implements ISubject
{
	private final static Logger LOGGER = LoggerFactory.getLogger(ISubjectImpl.class);

	/**
	 * 执行
	 */
	@Override
	public void execute()
		throws InterruptedException
	{
		LOGGER.info("ISubjectImpl execute");
	}

	/**
	 * 打印
	 *
	 * @param msg
	 * @throws InterruptedException
	 */
	@Override
	public void print(String msg)
		throws InterruptedException
	{
		System.out.println("print " + msg);
	}
}
