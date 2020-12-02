package basic.java8.samples.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static basic.Const.RESOURCE_PATH;

/**
 * Working with java types from javascript.
 */
public class Nashorn3
{

	public static void main(String[] args)
		throws Exception
	{
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("load('" + RESOURCE_PATH + "/nashorn3.js')");
	}

}