package guitar;

import java.util.Random;
import java.util.Scanner;

/**
 * @author zhacj
 * @describe ≡≡≡
 * ≡≡≡
 * @email alldios@139.com
 * @date 2020-03-25 11:28
 */
public class GuitarStringSyllableLetterTransTest
{

	private static final String[] SYLLABLES_LETTER = {"E", "A", "D", "G", "B", "E"};

	private static final String[] STRING_NUM = {"6", "5", "4", "3", "2", "1"};

	private static final String[] SYLLABLES = {"mi", "la", "re", "sol", "si", "mi"};

	static Random random = new Random();

	private static volatile String question;

	private static volatile int rightCount = 0;

	public static void main(String[] args)
		throws InterruptedException
	{

		while (true)
		{
			if (random.nextBoolean() && random.nextBoolean() && random.nextBoolean())
			{
				allNums2Letter();
				rightCount++;
				System.out.println(Guitar.NUMBER_OF_CORRECT_ANSWERS + rightCount + "\n");
				Thread.sleep(1000);
				continue;
			}
			int guessNum = (int)Math.abs(STRING_NUM.length * Math.random());
			num2Letter(guessNum);
			rightCount++;
			System.out.println(Guitar.NUMBER_OF_CORRECT_ANSWERS + rightCount + "\n");
			Thread.sleep(1000);
		}
	}

	public static void allNums2Letter()
	{
		String question;
		String questionSyllables;
		if (random.nextBoolean())
		{
			System.out.println("6 5 4 3 2 1");
			question = "EADGBE";
			questionSyllables = "_ mi la re sol si mi _";
		}
		else
		{
			System.out.println("1 2 3 4 5 6");
			question = "EBGDAE";
			questionSyllables = "_ mi si sol re la mi _";
		}
		String line = scanner(Guitar.INPUT_ANSWER);
		if (line.equals(question))
		{
			System.out.print(questionSyllables + " are the syllables corresponding to each number");
			System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
		}
		else
		{
			rightCount = 0;
			while (true)
			{
				String ans = scanner(Guitar.WRONG_INPUT_AGAIN);
				if (ans.equals(question))
				{
					System.out.print(questionSyllables + " are the syllables corresponding to each number");
					System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
					break;
				}
			}
		}
	}

	public static void num2Letter(Integer guessNum)
	{
		question = STRING_NUM[guessNum] + " " + STRING_NUM[guessNum] + " " + STRING_NUM[guessNum];
		System.out.println(question);
		String line = scanner(Guitar.INPUT_ANSWER);
		if (line.equals(SYLLABLES_LETTER[guessNum]))
		{
			System.out.print("_ " + SYLLABLES[guessNum] + " _ is THE corresponding syllable");
			System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
		}
		else
		{
			rightCount = 0;
			while (true)
			{
				String ans = scanner(Guitar.WRONG_INPUT_AGAIN);
				if (ans.equals(SYLLABLES_LETTER[guessNum]))
				{
					System.out.print("_ " + SYLLABLES[guessNum] + " _ is THE corresponding syllable");
					System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
					break;
				}
			}
		}
	}

	public static String scanner(String prompt)
	{
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		System.out.println(prompt);
		if (scanner.hasNext())
		{
			String ipt = scanner.next();
			return ipt;
		}
		return null;
	}

}

/**
 * 4f       3f        2f       1f
 * _____________________________________|______|______|______|______| 1s
 * ____________________________________|______|______|______|______} 2s
 * ___________________________________|______|______|______|______| 3s
 * __________________________________|______|______|______|______| 4s
 * _________________________________|______|______|______|______| 5s
 * ________________________________|______|______|______|______| 6s
 */