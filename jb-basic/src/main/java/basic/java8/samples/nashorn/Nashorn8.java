package basic.java8.samples.nashorn;

import basic.java8.samples.lambda.Person;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static basic.Const.RESOURCE_PATH;

public class Nashorn8
{
	public static void main(String[] args)
		throws ScriptException, NoSuchMethodException
	{
		NashornScriptEngine engine = (NashornScriptEngine)new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("load('" + RESOURCE_PATH + "/nashorn8.js')");

		engine.invokeFunction("evaluate1");                             // [object global]
		engine.invokeFunction("evaluate2");                             // [object Object]
		engine.invokeFunction("evaluate3", "Foobar");                   // Foobar
		engine.invokeFunction("evaluate3", new Person("John", "Doe"));  // [object global] <- ???????
	}

}
