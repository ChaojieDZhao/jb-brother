package basic.java8.samples.nashorn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static basic.Const.RESOURCE_PATH;

/**
 * Bind java objects to custom javascript objects.
 */
public class Nashorn5
{

	public static void main(String[] args)
		throws Exception
	{
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("load('" + RESOURCE_PATH + "/nashorn5.js')");

		Invocable invocable = (Invocable)engine;

		Product product = new Product();
		product.setName("Rubber");
		product.setPrice(1.99);
		product.setStock(1037);

		Object result = invocable.invokeFunction("getValueOfGoods", product);
		System.out.println(result);
	}

}