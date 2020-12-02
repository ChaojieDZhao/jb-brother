package algorithem.problem37;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an absolute path and two relative paths to a directory, can you write a method to
 * calculate
 * the final path?
 */
public class PathCalculator
{

	public static final String DIR_SEPARATOR = "/";

	private static String[] calculatePath(String[]... paths)
	{
		List<String> finalPath = new LinkedList<String>();
		for (String[] path : paths)
		{
			for (String dir : path)
			{
				if (dir.equals(".."))
				{
					if (!finalPath.isEmpty())
					{
						finalPath.remove(finalPath.size() - 1);
					}
				}
				else if (!dir.isEmpty())
				{
					finalPath.add(dir);
				}
			}
		}
		return finalPath.toArray(new String[finalPath.size()]);
	}

	/**
	 * Iterative algorithm to solve this problem. The complexity order of this algorithm is O(N+M+Ñ)
	 * where N,M and Ñ are the length of every path. We are going to transform string path to arrays
	 * and evaluate every fileDownload in a different way adding and subtracting elements from a list.
	 */
	public String calculate(String absolute, String r1, String r2)
	{
		if (absolute == null || r1 == null || r2 == null)
		{
			throw new IllegalArgumentException("You can't pass null strings as input.");
		}

		String[] absolutePath = absolute.split(DIR_SEPARATOR);
		String[] r1Path = r1.split(DIR_SEPARATOR);
		String[] r2Path = r2.split(DIR_SEPARATOR);
		String[] result = calculatePath(absolutePath, r1Path, r2Path);

		StringBuilder stringBuilder = new StringBuilder(DIR_SEPARATOR);
		for (String dir : result)
		{
			stringBuilder.append(dir);
			stringBuilder.append("/");
		}
		return stringBuilder.toString();
	}
}
