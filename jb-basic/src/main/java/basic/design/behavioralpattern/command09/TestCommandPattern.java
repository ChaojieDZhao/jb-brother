package basic.design.behavioralpattern.command09;

interface Command
{
	void execute();
}

/**
 * 命令模式的目的就是达到命令的发出者和执行者之间解耦，
 * 实现请求和执行分开，熟悉Struts的同学应该知道，Struts其实就是一种将请求和呈现分离的技术，
 * 其中必然涉及命令模式的思想！
 * <p>
 * 调用者 client 和 执行者 Bulb 没有任何的依赖关系。
 */
public class TestCommandPattern
{
	public static void main(String[] args)
	{
		Bulb bulb = new Bulb();
		TurnOnCommand turnOnCommand = new TurnOnCommand(bulb);
		TurnOffCommand turnOffCommand = new TurnOffCommand(bulb);
		Client client = new Client();
		client.setCommand(turnOnCommand).executeCommand();
		client.setCommand(turnOffCommand).executeCommand();
	}
}

class Bulb
{
	public void turnOn()
	{
		System.out.println("turnOff bulb");
	}

	public void turnOff()
	{
		System.out.println("turnOn bulb");
	}
}

class TurnOnCommand implements Command
{
	Bulb bulb;

	TurnOnCommand(Bulb bulb)
	{
		this.bulb = bulb;
	}

	public void execute()
	{
		bulb.turnOn();
	}
}

class TurnOffCommand implements Command
{
	Bulb bulb;

	TurnOffCommand(Bulb bulb)
	{
		this.bulb = bulb;
	}

	public void execute()
	{
		bulb.turnOff();
	}
}

class Client
{
	Command command;

	public Client setCommand(Command command)
	{
		this.command = command;
		return this;
	}

	public void executeCommand()
	{
		command.execute();
	}
}