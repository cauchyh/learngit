/*
Introduction

Cookie Clicker is a Javascript game by Orteil, 
where players click on a picture of a giant cookie. 
Clicking on the giant cookie gives them cookies. 
They can spend those cookies to buy buildings. 
Those buildings help them get even more cookies. 
Like this problem, the game is very cookie-focused. 
This problem has a similar idea, but it does not assume you have played Cookie Clicker. 
Please don't go play it now: it might be a long time before you come back.

Problem

In this problem, you start with 0 cookies. 
You gain cookies at a rate of 2 cookies per second, 
by clicking on a giant cookie. Any time you have at least C cookies, 
you can buy a cookie farm. Every time you buy a cookie farm, 
it costs you C cookies and gives you an extra F cookies per second.

Once you have X cookies that you haven't spent on farms, 
you win! Figure out how long it will take you to win if you use the best possible strategy.

Example

Suppose C=500.0, F=4.0 and X=2000.0. Here's how the best possible strategy plays out:

You start with 0 cookies, but producing 2 cookies per second.
After 250 seconds, you will have C=500 cookies 
and can buy a farm that produces F=4 cookies per second.
After buying the farm, you have 0 cookies, 
and your total cookie production is 6 cookies per second.
The next farm will cost 500 cookies, 
which you can buy after about 83.3333333 seconds.
After buying your second farm, you have 0 cookies, 
and your total cookie production is 10 cookies per second.
Another farm will cost 500 cookies, which you can buy after 50 seconds.
After buying your third farm, you have 0 cookies, 
and your total cookie production is 14 cookies per second.
Another farm would cost 500 cookies, 
but it actually makes sense not to buy it: 
instead you can just wait until you have X=2000 cookies, 
which takes about 142.8571429 seconds.
Total time: 250 + 83.3333333 + 50 + 142.8571429 = 526.1904762 seconds.
Notice that you get cookies continuously: 
so 0.1 seconds after the game starts you'll have 0.2 cookies, 
and π seconds after the game starts you'll have 2π cookies.

Input

The first line of the input gives the number of test cases, T. T lines follow. Each line contains three space-separated real-valued numbers: C, F and X, whose meanings are described earlier in the problem statement.

C, F and X will each consist of at least 1 digit followed by 1 decimal point followed by from 1 to 5 digits. There will be no leading zeroes.

Output

For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1) and y is the minimum number of seconds it takes before you can have X delicious cookies.

We recommend outputting y to 7 decimal places, but it is not required. y will be considered correct if it is close enough to the correct number: within an absolute or relative error of 10-6. See the FAQ for an explanation of what that means, and what formats of real numbers we accept.

Limits

1 ≤ T ≤ 100.

Small dataset

1 ≤ C ≤ 500.
1 ≤ F ≤ 4.
1 ≤ X ≤ 2000.
Large dataset

1 ≤ C ≤ 10000.
1 ≤ F ≤ 100.
1 ≤ X ≤ 100000.
Sample


Input 
4
30.0 1.0 2.0
30.0 2.0 100.0
30.50000 3.14159 1999.19990
500.0 4.0 2000.0

Output
Case #1: 1.0000000
Case #2: 39.1666667
Case #3: 63.9680013
Case #4: 526.1904762

Note

Cookie Clicker was created by Orteil. 
Orteil does not endorse and has no involvement with Google Code Jam.
*/
import java.io.*;
import java.util.*;

public class CookieClicker
{
	static final double originalCookie = 2;
	public static void main(String[] args)
	{
		double costOfFarm;
		double extraCookie;
		double targetCookie; 
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			int totalCase = Integer.parseInt(in.readLine());
			for (int caseNum=1; caseNum<=totalCase; caseNum++)
			{
				Queue<Double> queue = new LinkedList<Double>();
				String temp = in.readLine();
				int start = 0;
				// int end = 0;
				for (int i=0; i<temp.length(); i++)
				{
					if (temp.charAt(i) == ' ')
					{
						queue.add(Double.parseDouble(temp.substring(start, i)));
						start = i+1;
					}
				}
				queue.add(Double.parseDouble(temp.substring(start, temp.length())));
				costOfFarm = queue.remove();
				extraCookie = queue.remove();
				targetCookie = queue.remove();
				double res = minTimeToTarget(costOfFarm, extraCookie, targetCookie);
				writeOutput(res, caseNum);
			}
			in.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Cannot read the file :(");
		}
	}

	private static double minTimeToTarget(double costOfFarm, double extraCookie, double targetCookie)
	{
		double curProduction = originalCookie;
		double minTime = targetCookie/curProduction;
		double totalTime = 0;
		while (true)
		{
			minTime = targetCookie/curProduction;
			double timeWithNewFarm = costOfFarm/curProduction + targetCookie/(curProduction+extraCookie);
			if (minTime <= timeWithNewFarm)
			{
				return totalTime + targetCookie/curProduction;
			}
			else // minTime > timeWithNewFarm
			{
				totalTime += costOfFarm/curProduction;
				curProduction += extraCookie;	
			}

		}	
	}

	private static void writeOutput(double res, int caseNum)
	{
		try
		{
			// Date date = new Date();
			File output = new File("output.txt");
			if (!output.exists())
			{
				output.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(output.getAbsoluteFile(), true));
			// Case #1: 1.0000000
			out.write("Case #" + caseNum + ": " + res);
			out.newLine();
			out.close();
		}
		catch(IOException e)
		{
	         e.printStackTrace();
	    }
	}

	// private static copyToData(double costOfFarm, double extraCookie, double targetCookie, BufferedReader in)
	// {
	// 	try
	// 	{
	// 		Queue<double> queue = new LinkedList<double>();
	// 		String temp = in.readLine();
	// 		int start = 0;
	// 		// int end = 0;
	// 		for (int i=0; i<temp.length(); i++)
	// 		{
	// 			if (temp.charAt(i) == ' ')
	// 			{
	// 				queue.add(double.parsedouble(temp.substring(start, i)));
	// 				start = i+1;
	// 			}
	// 		}
	// 		queue.add(double.parsedouble(temp.substring(start, temp.length())));
	// 		costOfFarm = 
	// 	}
	// 	catch(IOException e)
	// 	{
	// 		e.printStackTrace();
	// 		// System.out.println("Cannot read the file :(");
	// 	}
	// }
}






















