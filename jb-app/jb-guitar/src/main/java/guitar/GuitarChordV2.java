package guitar;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Random;

public class GuitarChordV2
{

	public static final HashSet<String> CHORD_XIAO_QING_GE = Sets.newHashSet("C add9", "C", "C maj7");

	public static HashSet<String> HASH_SET = Sets.newHashSet();

	public static void main(String[] args)
		throws InterruptedException
	{

		HASH_SET.addAll(CHORD_XIAO_QING_GE);

		System.out.println(HASH_SET);

		String[] chord_final = HASH_SET.toArray(new String[0]);

		Random random = new Random();
		for (int i = 1; i < 101; i++)
		{
			int i1 = random.nextInt(chord_final.length);
			System.out.println(
				i + " :  " + chord_final[i1] + " " + chord_final[i1] + " " + chord_final[i1] + " " + chord_final[i1] +
					" " + chord_final[i1] + " " +
					chord_final[i1]);
			//Thread.sleep(20000);
		}
	}
}
