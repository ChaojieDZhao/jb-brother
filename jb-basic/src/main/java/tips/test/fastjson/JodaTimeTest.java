package tips.test.fastjson;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTimeTest
{
	public static void main(String[] args)
	{
		System.out.println(new DateTime(new Date()).toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
