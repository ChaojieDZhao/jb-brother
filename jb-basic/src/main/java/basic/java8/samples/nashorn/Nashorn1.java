package basic.java8.samples.nashorn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

import static basic.Const.RESOURCE_PATH;

/**
 * Calling javascript functions from java with nashorn.
 */
public class Nashorn1
{

	public static void main(String[] args)
		throws Exception
	{
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new FileReader(RESOURCE_PATH + "/nashorn1.js"));

		Invocable invocable = (Invocable)engine;
		Object result = invocable.invokeFunction("fun1", "Peter Parker");
		System.out.println(result);
		System.out.println(result.getClass());

		invocable.invokeFunction("fun2", new Date());
		invocable.invokeFunction("fun2", LocalDateTime.now());
		invocable.invokeFunction("fun2", new Nashorn7.Person());
	}

}