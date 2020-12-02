package guitar;

import java.util.Random;
import java.util.Scanner;

public class GuitarSyllableLetterTransTest
{

	private static final String[] SYLLABLES_LETTER = {"A", "B", "C", "D", "E", "F", "G"};

	private static final String[] SYLLABLES = {"la", "si", "do", "re", "mi", "fa", "sol"};

	private static final String[] STRING_PLAY = {"3s2f", "2s", "5s3f", "4s", "4s2f", "4s3f", "3s"};

	private static final String[] SYLLABLES_NUM = {"6", "7", "1", "2", "3", "4", "5"};

	private static volatile String question;

	private static volatile int rightCount = 0;

	public static void main(String[] args)
		throws InterruptedException
	{
		Random random = new Random();
		while (true)
		{
			int guessNum = (int)Math.abs(SYLLABLES.length * Math.random());
			boolean b = random.nextBoolean();
			if (b)
			{
				letter2Syllable(guessNum);
			}
			else
			{
				syllable2Letter(guessNum);
			}
			rightCount++;
			System.out.println(Guitar.NUMBER_OF_CORRECT_ANSWERS + rightCount + "\n");
			Thread.sleep(1000);
		}
	}

	public static void letter2Syllable(Integer guessNum)
	{

		question = SYLLABLES_LETTER[guessNum] + " " + SYLLABLES_LETTER[guessNum] + " " + SYLLABLES_LETTER[guessNum];
		System.out.println(question);
		String line = scanner(Guitar.INPUT_ANSWER);
		if (line.equals(SYLLABLES[guessNum]))
		{
			System.out.println("_ " + SYLLABLES_NUM[guessNum] + " _ is THE corresponding number");
			System.out.print("_ " + STRING_PLAY[guessNum] + " _ is THE string played");
			System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
		}
		else
		{
			rightCount = 0;
			while (true)
			{
				String ans = scanner("wrong, input again : ");
				if (ans.equals(SYLLABLES[guessNum]))
				{
					System.out.println("_ " + SYLLABLES_NUM[guessNum] + " _ is THE corresponding number");
					System.out.print("_ " + STRING_PLAY[guessNum] + " _ is THE string played");
					System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
					break;
				}
			}
		}
	}

	public static void syllable2Letter(Integer guessNum)
	{
		question = SYLLABLES[guessNum] + " " + SYLLABLES[guessNum] + " " + SYLLABLES[guessNum];
		System.out.println(question);
		String line = scanner(Guitar.INPUT_ANSWER);
		if (line.equals(SYLLABLES_LETTER[guessNum]))
		{
			System.out.println("_ " + SYLLABLES_NUM[guessNum] + " _ is THE corresponding number");
			System.out.print("_ " + STRING_PLAY[guessNum] + " _ is THE string played");
			System.out.print(Guitar.AND_UR_RIGHT_CONGRATS);
		}
		else
		{
			rightCount = 0;
			while (true)
			{
				String ans = scanner("wrong, input again : ");
				if (ans.equals(SYLLABLES_LETTER[guessNum]))
				{
					System.out.println("_ " + SYLLABLES_NUM[guessNum] + " _ is THE corresponding number");
					System.out.print("_ " + STRING_PLAY[guessNum] + " _ is THE string played");
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
