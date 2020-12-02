package basic.generic.drawing;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanvasThree<T>
{

	public static void main(String[] args)
	{
		Set<?> unknownSet = new HashSet<String>();

		Set<?> s = Collections.unmodifiableSet(unknownSet);

	}

}