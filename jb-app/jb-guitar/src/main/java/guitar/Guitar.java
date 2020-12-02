package guitar;

import java.util.Random;

public class Guitar
{
	public static final String NUMBER_OF_CORRECT_ANSWERS = "number of correct answers : ";

	public static final String INPUT_ANSWER = "input answer : ";

	public static final String AND_UR_RIGHT_CONGRATS = ", and ur right, congrats!!! ";

	public static final String WRONG_INPUT_AGAIN = "wrong, input again : ";

	public static void main(String[] args)
		throws InterruptedException
	{
		Random random = new Random();
		for (int i = 1; i < 101; i++)
		{
			int i1 = random.nextInt(7) + 1;
			System.out.println(i + " :  " + i1 + " " + i1 + " " + i1 + " " + i1 + " " + i1 + " " + i1);
			Thread.sleep(4000);
		}
	}
}
